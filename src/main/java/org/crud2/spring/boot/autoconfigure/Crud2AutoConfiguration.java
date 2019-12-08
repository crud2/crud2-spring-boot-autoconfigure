package org.crud2.spring.boot.autoconfigure;

import org.crud2.CRUD;
import org.crud2.CRUD2BeanFactory;
import org.crud2.autoengine.AutoEngineSessionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@ConditionalOnProperty(
        prefix = "crud2",
        name = "enable",
        havingValue = "true"
)
@ComponentScan("org.crud2")
public class Crud2AutoConfiguration {
    @Autowired
    private CRUD2BeanFactory crud2BeanFactory;
    @Autowired
    private DataSource dataSource;

    @Autowired
    private CRUD crud;

    @Value("${crud2.dialect}")
    private String dialect;

    @PostConstruct
    public void init() {
        crud.setBeanFactory(crud2BeanFactory);
        if (dialect != null && dialect.length() > 0) {
            CRUD.dialect = dialect;
        }
    }

    @ConditionalOnMissingBean(AutoEngineSessionBean.class)
    @Bean
    public AutoEngineSessionBean autoEngineSessionBean() {
        return moduleId -> dataSource;
    }


}
