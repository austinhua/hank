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
package com.rapleaf.hank.config.yaml;

import com.rapleaf.hank.config.InvalidConfigurationException;
import com.rapleaf.hank.config.PartitionServerConfigurator;

import java.io.IOException;
import java.util.*;

public class YamlPartitionServerConfigurator extends BaseYamlConfigurator implements PartitionServerConfigurator {

  private static final String PARTITION_SERVER_SECTION_KEY = "partition_server";
  private static final String LOCAL_DATA_DIRS_KEY = "local_data_dirs";
  private static final String SERVICE_PORT_KEY = "service_port";
  private static final String RING_GROUP_NAME_KEY = "ring_group_name";
  private static final String PARTITION_SERVER_DAEMON_SECTION_KEY = "partition_server_daemon";
  private static final String NUM_CONCURRENT_CONNECTIONS_KEY = "num_worker_threads";
  private static final String UPDATE_DAEMON_SECTION_KEY = "update_daemon";
  private static final String NUM_CONCURRENT_UPDATES_KEY = "num_concurrent_updates";
  private static final String NUM_CONCURRENT_GET_BULK_TASKS = "num_concurrent_get_bulk_tasks";
  private static final String GET_BULK_TASK_SIZE = "get_bulk_task_size";

  public YamlPartitionServerConfigurator(String path) throws IOException,
      InvalidConfigurationException {
    super(path);
  }

  @Override
  protected void validate() throws InvalidConfigurationException {
    super.validate();

    getRequiredSection(PARTITION_SERVER_SECTION_KEY);
    getRequiredStringList(PARTITION_SERVER_SECTION_KEY, LOCAL_DATA_DIRS_KEY);
    getRequiredInteger(PARTITION_SERVER_SECTION_KEY, SERVICE_PORT_KEY);
    getRequiredString(PARTITION_SERVER_SECTION_KEY, RING_GROUP_NAME_KEY);

    getRequiredSection(PARTITION_SERVER_SECTION_KEY, PARTITION_SERVER_DAEMON_SECTION_KEY);
    getRequiredInteger(PARTITION_SERVER_SECTION_KEY,
        PARTITION_SERVER_DAEMON_SECTION_KEY,
        NUM_CONCURRENT_CONNECTIONS_KEY);
    getRequiredInteger(PARTITION_SERVER_SECTION_KEY,
        PARTITION_SERVER_DAEMON_SECTION_KEY,
        NUM_CONCURRENT_GET_BULK_TASKS);
    getRequiredInteger(PARTITION_SERVER_SECTION_KEY, PARTITION_SERVER_DAEMON_SECTION_KEY, GET_BULK_TASK_SIZE);
    getRequiredSection(PARTITION_SERVER_SECTION_KEY, UPDATE_DAEMON_SECTION_KEY);
    getRequiredInteger(PARTITION_SERVER_SECTION_KEY, UPDATE_DAEMON_SECTION_KEY, NUM_CONCURRENT_UPDATES_KEY);
  }

  @Override
  public Set<String> getLocalDataDirectories() {
    return new HashSet<String>(getStringList(PARTITION_SERVER_SECTION_KEY, LOCAL_DATA_DIRS_KEY));
  }

  @Override
  public int getServicePort() {
    return getInteger(PARTITION_SERVER_SECTION_KEY, SERVICE_PORT_KEY);
  }

  @Override
  public String getRingGroupName() {
    return getString(PARTITION_SERVER_SECTION_KEY, RING_GROUP_NAME_KEY);
  }

  @Override
  public int getNumConcurrentConnections() {
    return getInteger(PARTITION_SERVER_SECTION_KEY,
        PARTITION_SERVER_DAEMON_SECTION_KEY,
        NUM_CONCURRENT_CONNECTIONS_KEY);
  }

  @Override
  public int getNumConcurrentGetBulkTasks() {
    return getInteger(PARTITION_SERVER_SECTION_KEY,
        PARTITION_SERVER_DAEMON_SECTION_KEY,
        NUM_CONCURRENT_GET_BULK_TASKS);
  }

  @Override
  public int getGetBulkTaskSize() {
    return getInteger(PARTITION_SERVER_SECTION_KEY, PARTITION_SERVER_DAEMON_SECTION_KEY, GET_BULK_TASK_SIZE);
  }

  @Override
  public int getNumConcurrentUpdates() {
    return getInteger(PARTITION_SERVER_SECTION_KEY, UPDATE_DAEMON_SECTION_KEY, NUM_CONCURRENT_UPDATES_KEY);
  }
}
