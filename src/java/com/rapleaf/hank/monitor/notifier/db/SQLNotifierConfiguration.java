package com.rapleaf.hank.monitor.notifier.db;

import org.apache.log4j.Logger;

public class SQLNotifierConfiguration implements ISQLNotifierConfiguration {
  private static Logger LOG = Logger.getLogger(SQLNotifierConfiguration.class);

  private String driver;
  private String connectionUrl;
  private String username;
  private String password;
  private String targetVersionTable;

  public SQLNotifierConfiguration(String driver, String connectionUrl, String username, String password, String targetVersionTable) {
    this.driver = driver;
    this.connectionUrl = connectionUrl;
    this.username = username;
    this.password = password;
    this.targetVersionTable = targetVersionTable;
  }

  @Override
  public String getDriver() {
    return driver;
  }

  @Override
  public String getConnectionUrl() {
    return connectionUrl;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getTargetVersionTable() {
    return targetVersionTable;
  }
}
