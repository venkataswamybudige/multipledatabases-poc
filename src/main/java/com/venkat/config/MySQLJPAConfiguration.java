//package com.venkat.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        basePackages = "com.venkat.productrepository",
//        entityManagerFactoryRef = "mysqlEntityManagerFactoryBean",
//        transactionManagerRef = "mySQLTransactionManager"
//)
//public class MySQLJPAConfiguration {
//
//    @Bean
//    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
//        return new EntityManagerFactoryBuilder(null, null, null);
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder,
//                                                                                @Qualifier("mysqlDataSource") DataSource dataSource) {
//        return entityManagerFactoryBuilder
//                .dataSource(dataSource)
//                .packages("com.venkat.productentity") // Your entity package
//                .build();
//    }
//
//
//    @Bean
//    public PlatformTransactionManager mySQLTransactionManager(@Qualifier("mysqlEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean emfb){
//        return new JpaTransactionManager(emfb.getObject());
//    }
//}


package com.venkat.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.venkat.productrepository",
        entityManagerFactoryRef = "mysqlEntityManagerFactoryBean",
        transactionManagerRef = "mySQLTransactionManager"
)
public class MySQLJPAConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactoryBean(
            @Qualifier("mysqlDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.venkat.productentity");  // Ensure this points to your entity package
        factoryBean.setPersistenceUnitName("mysqlPU");

        // Explicitly set Hibernate as the JPA provider
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager mySQLTransactionManager(
            @Qualifier("mysqlEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean emfb) {
        return new JpaTransactionManager(emfb.getObject());
    }
}

