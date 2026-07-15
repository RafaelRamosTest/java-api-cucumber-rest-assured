package com.runner;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.FEATURES_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
// 1. Aponta para a raiz da pasta resources onde o Cucumber vai começar a escanear
@SelectClasspathResource("features")
// 2. Garante o caminho correto das features caso o passo acima falhe
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/test/resources/features")
// 3. Aponta para o pacote EXATO onde estão as suas classes de Steps
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.steps")
// 4. Gera o relatório na pasta target
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-reports/cucumber.html")
public class TestRunner {
    // Permanece vazia
}
