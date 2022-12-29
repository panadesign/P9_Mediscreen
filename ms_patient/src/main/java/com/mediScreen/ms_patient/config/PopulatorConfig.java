package com.mediScreen.ms_patient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

import javax.annotation.Resource;

@Configuration
public class PopulatorConfig {

        @Bean
        public Jackson2RepositoryPopulatorFactoryBean getRespositoryPopulator() {
            Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
            factory.setResources(new ClassPathResource[]{new ClassPathResource("patient-data.json")});
            return factory;
        }

}
