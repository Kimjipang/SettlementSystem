package com.example.settlement.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.example.settlement.advertisement.repository.write",
                "com.example.settlement.user.repository.write",
                "com.example.settlement.video.repository.write"
        },
        entityManagerFactoryRef = "writeEntityManagerFactory",
        transactionManagerRef = "writeTransactionManager"
)
@EntityScan(
        basePackages = {
                "com.example.settlement.advertisement.entity",
                "com.example.settlement.user.entity",
                "com.example.settlement.video.entity"
        }
)
public class WriteEntityManagerConfig {

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean writeEntityManagerFactory(@Qualifier("writeDataSource") DataSource writeDataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(writeDataSource);
        em.setPackagesToScan(
                "com.example.settlement.advertisement.entity",
                "com.example.settlement.user.entity",
                "com.example.settlement.video.entity"
        );
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "none");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public JpaTransactionManager writeTransactionManager(
            @Qualifier("writeEntityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
