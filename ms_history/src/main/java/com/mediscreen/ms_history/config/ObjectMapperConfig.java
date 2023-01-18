package com.mediscreen.ms_history.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
public class ObjectMapperConfig {

        @Primary
        @Bean
        public ObjectMapper objectMapper() {
            return new ObjectMapper()
                    .registerModule(new JavaTimeModule())
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        }

        @Bean
        public Jackson2RepositoryPopulatorFactoryBean getRepositoryPopulator(ObjectMapper objectMapper) {
            Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
            factory.setMapper(objectMapper);
            factory.setResources(new Resource[]{new ClassPathResource("history-data.json")});
            return factory;
        }

}
