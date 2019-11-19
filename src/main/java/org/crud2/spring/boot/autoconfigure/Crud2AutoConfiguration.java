package org.crud2.spring.boot.autoconfigure;

import org.crud2.CRUD;
import org.crud2.CRUD2BeanFactory;
import org.crud2.Initializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

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
    private CRUD crud;

    @Autowired
    private Initializer initializer;

    @PostConstruct
    public void init() {
        crud.setBeanFactory(crud2BeanFactory);
        initializer.initialize();
    }

}
