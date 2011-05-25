package com.rapleaf.hank.coordinator.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs.Ids;

import com.rapleaf.hank.ZkTestCase;
import com.rapleaf.hank.coordinator.DomainVersion;

public class TestZkDomainVersion extends ZkTestCase {
  public void testCreate() throws Exception {
    DomainVersion dv = ZkDomainVersion.create(getZk(), getRoot(), 1);
    assertEquals(1, dv.getVersionNumber());
    assertNull(dv.getClosedAt());
    assertFalse(dv.isClosed());
  }

  public void testLoad() throws Exception {
    ZkDomainVersion.create(getZk(), getRoot(), 1);
    DomainVersion dv = new ZkDomainVersion(getZk(), getRoot() + "version_" + 1);
    assertEquals(1, dv.getVersionNumber());
    assertNull(dv.getClosedAt());
    assertFalse(dv.isClosed());
  }

  public void testVersioning() {
    fail();
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    getZk().create(getRoot() + "/versions", null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
  }
}
