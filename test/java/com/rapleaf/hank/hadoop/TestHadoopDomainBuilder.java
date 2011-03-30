/**
 *  Copyright 2011 Rapleaf
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.rapleaf.hank.hadoop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileAlreadyExistsException;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.TextInputFormat;

import com.rapleaf.hank.HadoopTestCase;
import com.rapleaf.hank.coordinator.Coordinator;
import com.rapleaf.hank.coordinator.CoordinatorFactory;
import com.rapleaf.hank.coordinator.DomainConfig;
import com.rapleaf.hank.coordinator.MockCoordinator;
import com.rapleaf.hank.coordinator.MockDomainConfig;
import com.rapleaf.hank.exception.DataNotFoundException;
import com.rapleaf.hank.partitioner.Partitioner;
import com.rapleaf.hank.storage.MockStorageEngine;
import com.rapleaf.hank.storage.OutputStreamFactory;
import com.rapleaf.hank.storage.Writer;

public class TestHadoopDomainBuilder extends HadoopTestCase {

  private final String DOMAIN_A_NAME = "a";
  private final String DOMAIN_B_NAME = "b";
  private final String CONFIG_PATH = localTmpDir + "/config";

  public TestHadoopDomainBuilder() throws IOException {
    super(TestHadoopDomainBuilder.class);
  }

  private static class LocalMockWriter implements Writer {

    protected final OutputStream outputStream;

    LocalMockWriter(OutputStreamFactory streamFactory, int partNum,
        int versionNumber, boolean base) throws IOException {
      this.outputStream = streamFactory.getOutputStream(partNum, Integer.toString(versionNumber) + "." + (base ? "base" : "nobase"));
    }

    @Override
    public void write(ByteBuffer key, ByteBuffer value) throws IOException {
      this.outputStream.write(key.array(), key.position(), key.remaining());
      outputStream.write(" ".getBytes());
      outputStream.write(value.array(), value.position(), value.remaining());
      outputStream.write("\n".getBytes());
    }

    @Override
    public void close() throws IOException {
      outputStream.close();
    }
  }

  private static class IntKeyModPartitioner implements Partitioner {

    private int numPartitions;

    IntKeyModPartitioner(int numPartitions) {
      this.numPartitions = numPartitions;
    }

    @Override
    public int partition(ByteBuffer key) {
      String keyString = new String(key.array(), key.position(), key.remaining());
      Integer keyInteger = Integer.valueOf(keyString);
      return keyInteger % numPartitions;
    }
  }

  private static class LocalMockStorageEngine extends MockStorageEngine {
    @Override
    public Writer getWriter(OutputStreamFactory streamFactory, int partNum,
        int versionNumber, boolean base) throws IOException {
      return new LocalMockWriter(streamFactory, partNum, versionNumber, base);
    }

    @Override
    public ByteBuffer getComparableKey(ByteBuffer key) {
      return key;
    }
  }

  public static class LocalMockCoordinator extends MockCoordinator {

    public static class Factory implements CoordinatorFactory {
      @Override
      public Coordinator getCoordinator(Map<String, Object> options) {
        return new LocalMockCoordinator();
      }
    }

    @Override
    public DomainConfig getDomainConfig(String domainName) throws DataNotFoundException {
      return new MockDomainConfig(domainName, 2, new IntKeyModPartitioner(2), new LocalMockStorageEngine(), 0);
    }
  }

  static public String getConfiguration() {
    return "coordinator:\n  factory: com.rapleaf.hank.hadoop.TestHadoopDomainBuilder$LocalMockCoordinator$Factory\n  options:\n";
  }

  @Override
  public void setUp() throws Exception {
    super.setUp();
    // Create config
    PrintWriter pw = new PrintWriter(new FileWriter(CONFIG_PATH));
    pw.write(getConfiguration());
    pw.close();

    // Create inputs
    outputFile(fs, INPUT_DIR + "/" + DOMAIN_A_NAME, "0 v0\n1 v1\n2 v2\n3 v3\n4 v4");
    outputFile(fs, INPUT_DIR + "/" + DOMAIN_B_NAME, "4 v4\n1 v1\n2 v2\n0 v0\n3 v3");
  }

  public void testFailIfOutputExists() throws IOException {
    fs.create(new Path(OUTPUT_DIR));
    try {
      JobClient.runJob(HadoopDomainBuilder.createJobConfiguration(DOMAIN_A_NAME, INPUT_DIR + "/" + DOMAIN_A_NAME, TextInputFormat.class, TestMapper.class, CONFIG_PATH, OUTPUT_DIR));
      fail("Should fail when output exists");
    } catch (FileAlreadyExistsException e) {
    }
  }

  public void testOutput() throws IOException {
    HadoopDomainBuilder.buildHankDomain(DOMAIN_A_NAME, INPUT_DIR + "/" + DOMAIN_A_NAME, TextInputFormat.class, TestMapper.class, CONFIG_PATH, OUTPUT_DIR + "/" + DOMAIN_A_NAME);
    String p1 = getContents(fs, HDFSOutputStreamFactory.getPath(OUTPUT_DIR + "/" + DOMAIN_A_NAME, 0, "0.base"));
    String p2 = getContents(fs, HDFSOutputStreamFactory.getPath(OUTPUT_DIR + "/" + DOMAIN_A_NAME, 1, "0.base"));
    assertEquals("0 v0\n2 v2\n4 v4\n", p1);
    assertEquals("1 v1\n3 v3\n", p2);
  }

  public void testSorted() throws IOException {
    HadoopDomainBuilder.buildHankDomain(DOMAIN_B_NAME, INPUT_DIR + "/" + DOMAIN_B_NAME, TextInputFormat.class, TestMapper.class, CONFIG_PATH, OUTPUT_DIR + "/" + DOMAIN_B_NAME);
    String p1 = getContents(fs, HDFSOutputStreamFactory.getPath(OUTPUT_DIR + "/" + DOMAIN_B_NAME, 0, "0.base"));
    String p2 = getContents(fs, HDFSOutputStreamFactory.getPath(OUTPUT_DIR + "/" + DOMAIN_B_NAME, 1, "0.base"));
    assertEquals("0 v0\n2 v2\n4 v4\n", p1);
    assertEquals("1 v1\n3 v3\n", p2);
  }

  private static class TestMapper extends DomainBuilderMapper<LongWritable, Text> {

    // Converts text file lines "<key> <value>" to the corresponding object
    @Override
    protected KeyValuePair buildHankKeyValue(LongWritable key, Text value) {
      String[] splits = value.toString().split(" ");
      if (splits.length != 2) {
        throw new RuntimeException("Input text file must be lines like \"<key> <value>\"");
      }
      return new KeyValuePair(splits[0].getBytes(), splits[1].getBytes());
    }
  }
}
