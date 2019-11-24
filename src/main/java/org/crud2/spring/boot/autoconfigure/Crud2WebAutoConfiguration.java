package org.crud2.spring.boot.autoconfigure;

import org.crud2.autoengine.web.DefaultRequestSqlParameterGetter;
import org.crud2.autoengine.web.RequestSqlParameterGetter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ConditionalOnWebApplication
@ComponentScan("org.crud2.autoengine.web")
public class Crud2WebAutoConfiguration {
    @Bean
    public RequestSqlParameterGetter requestSqlParameterGetter() {
        return new DefaultRequestSqlParameterGetter();
    }
}
