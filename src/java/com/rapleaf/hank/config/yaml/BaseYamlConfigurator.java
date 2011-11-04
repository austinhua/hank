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

import com.rapleaf.hank.config.Configurator;
import com.rapleaf.hank.config.InvalidConfigurationException;
import com.rapleaf.hank.coordinator.Coordinator;
import com.rapleaf.hank.coordinator.CoordinatorFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class BaseYamlConfigurator implements Configurator {
  private static final String COORDINATOR_SECTION_KEY = "coordinator";
  private static final String COORDINATOR__FACTORY_KEY = "factory";
  private static final String COORDINATOR__OPTIONS_KEY = "options";

  protected Map<String, Object> config;
  private String contentSource;

  protected BaseYamlConfigurator() {}

  protected BaseYamlConfigurator(String configPath) throws FileNotFoundException, InvalidConfigurationException {
    loadFromFile(configPath);
  }

  public void loadFromFile(String path) throws InvalidConfigurationException, FileNotFoundException {
    try {
      contentSource = path;
      config = (Map<String, Object>) new Yaml().load(new BufferedInputStream(new FileInputStream(path)));
    } catch (Exception e) {
      throw new RuntimeException("Invalid configuration in " + path, e);
    }
    validate();
  }

  public void loadFromYaml(String yaml) throws InvalidConfigurationException {
    try {
      contentSource = yaml;
      config = (Map<String, Object>) new Yaml().load(yaml);
    } catch (Exception e) {
      throw new RuntimeException("Invalid configuration: " + yaml, e);
    }
    validate();
  }

  protected void validate() throws InvalidConfigurationException {
    checkNonEmptyConfiguration();
    getRequiredString(COORDINATOR_SECTION_KEY, COORDINATOR__FACTORY_KEY);
    getRequiredSection(COORDINATOR_SECTION_KEY, COORDINATOR__OPTIONS_KEY);
  }

  @Override
  public Coordinator createCoordinator() {
    try {
      validate();
    } catch (InvalidConfigurationException e) {
      throw new RuntimeException("Configuration is invalid!", e);
    }
    String factoryClassName = getString(COORDINATOR_SECTION_KEY, COORDINATOR__FACTORY_KEY);
    Class<CoordinatorFactory> factoryClass;
    try {
      factoryClass = (Class<CoordinatorFactory>) Class.forName(factoryClassName);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Could not load coordinator factory class " + factoryClassName + "!", e);
    }
    CoordinatorFactory factory;
    try {
      factory = factoryClass.newInstance();
    } catch (Exception e) {
      throw new RuntimeException("Could not get an instance of " + factoryClass.getName() + "!", e);
    }
    return factory.getCoordinator(getSection(COORDINATOR_SECTION_KEY, COORDINATOR__OPTIONS_KEY));
  }

  // Validation functions

  protected void checkNonEmptyConfiguration() throws InvalidConfigurationException {
    if (config == null) {
      throw new InvalidConfigurationException("Configuration is empty!");
    }
  }

  protected Object getRequiredOption(String... optionPath) throws InvalidConfigurationException {
    Map<String, Object> currentSection = config;
    String path = "";
    int i = 0;
    for (; i < optionPath.length - 1; ++i) {
      if (currentSection.get(optionPath[i]) == null) {
        throw new InvalidConfigurationException("Section '" + optionPath[i]
            + "' is required in configuration section '" + path + "' of configuration '" + contentSource + "'");
      }
      path = path + ":" + optionPath[i];
      currentSection = (Map<String, Object>) currentSection.get(optionPath[i]);
    }
    if (currentSection.get(optionPath[i]) == null) {
      throw new InvalidConfigurationException("Option '" + optionPath[i]
          + "' is required in configuration section '" + path + "' of configuration '" + contentSource + "'");
    }
    return currentSection.get(optionPath[i]);
  }

  protected Map<String, Object> getRequiredSection(String... optionPath) throws InvalidConfigurationException {
    Object option = getRequiredOption(optionPath);
    if (!(option instanceof Map)) {
      throw new InvalidConfigurationException("Option '" + Arrays.toString(optionPath) + "' must be of type Map in configuration '" + contentSource + "'");
    }
    return (Map<String, Object>) option;
  }

  protected Map<String, Object> getSection(String... optionPath) {
    try {
      return getRequiredSection(optionPath);
    } catch (InvalidConfigurationException e) {
      throw new RuntimeException(e);
    }
  }

  protected String getRequiredString(String... optionPath) throws InvalidConfigurationException {
    Object option = getRequiredOption(optionPath);
    if (!(option instanceof String)) {
      throw new InvalidConfigurationException("Option '" + Arrays.toString(optionPath) + "' must be of type String in configuration '" + contentSource + "'");
    }
    return (String) option;
  }

  protected String getString(String... optionPath) {
    try {
      return getRequiredString(optionPath);
    } catch (InvalidConfigurationException e) {
      throw new RuntimeException(e);
    }
  }

  protected Integer getRequiredInteger(String... optionPath) throws InvalidConfigurationException {
    Object option = getRequiredOption(optionPath);
    if (!(option instanceof Integer)) {
      throw new InvalidConfigurationException("Option '" + Arrays.toString(optionPath) + "' must be of type Integer in configuration '" + contentSource + "'");
    }
    return (Integer) option;
  }

  protected Integer getInteger(String... optionPath) {
    try {
      return getRequiredInteger(optionPath);
    } catch (InvalidConfigurationException e) {
      throw new RuntimeException(e);
    }
  }

  protected List<String> getRequiredStringList(String... optionPath) throws InvalidConfigurationException {
    Object option = getRequiredOption(optionPath);
    if (!(option instanceof List)) {
      throw new InvalidConfigurationException("Option '" + Arrays.toString(optionPath) + "' must be of type List of strings in configuration '" + contentSource + "'");
    }
    try {
      return (List<String>) option;
    } catch (ClassCastException e) {
      throw new InvalidConfigurationException("Option '" + Arrays.toString(optionPath) + "' must be of type List of strings in configuration '" + contentSource + "'");
    }
  }

  protected List<String> getStringList(String... optionPath) {
    try {
      return getRequiredStringList(optionPath);
    } catch (InvalidConfigurationException e) {
      throw new RuntimeException(e);
    }
  }
}
