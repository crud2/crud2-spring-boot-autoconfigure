package org.crud2.spring.boot.autoconfigure;

import org.crud2.CRUD2BeanFactory;
import org.crud2.autoengine.AutoEngine;
import org.crud2.autoengine.config.ModuleDefineFactory;
import org.crud2.autoengine.sql.SqlTextParameterResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;

@ConditionalOnProperty(
        prefix = "crud2.autoengine.scan",
        name = "defines")
public class Crud2AutoEngineAutoConfiguration {
    @Value("${crud2.autoengine.scan.defines}")
    private Resource[] defines;

    @Autowired
    private ModuleDefineFactory moduleDefineFactory;
    @Autowired
    private CRUD2BeanFactory crud2BeanFactory;
    @Autowired
    private SqlTextParameterResolver sqlTextParameterResolver;
    @Autowired
    private AutoEngine autoEngine;

    @PostConstruct
    private void init() {
        moduleDefineFactory.setModuleConfigs(defines);
        moduleDefineFactory.regModules();
        autoEngine.init(crud2BeanFactory, moduleDefineFactory, sqlTextParameterResolver);
    }
}
