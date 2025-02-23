//package com.venkat.config;
//
//import jakarta.persistence.EntityManagerFactory;
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
//        basePackages = "com.venkat.orderrepository",
//        entityManagerFactoryRef = "postgresEntityManagerFactoryBean",
//        transactionManagerRef = "postgresTransactionManager"
//)
//public class PostgresJPAConfiguration {
//
//    @Bean
//    LocalContainerEntityManagerFactoryBean postgresEntityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder,@Qualifier("postgresDataSource") DataSource dataSource){
//        return  entityManagerFactoryBuilder.
//                dataSource(dataSource).
//                packages("com.venkat.orderentity").
//                build();
//
//    }
//
//    @Bean
//    public JpaTransactionManager postgresTransactionManager(@Qualifier("postgresEntityManagerFactoryBean") EntityManagerFactory postgresEntityManagerFactoryBean) {
//        return new JpaTransactionManager(postgresEntityManagerFactoryBean);
//    }
//}


package com.venkat.config;

import jakarta.persistence.EntityManagerFactory;
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
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.venkat.orderrepository",
        entityManagerFactoryRef = "postgresEntityManagerFactoryBean",
        transactionManagerRef = "postgresTransactionManager"
)
public class PostgresJPAConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactoryBean(@Qualifier("postgresDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.venkat.orderentity");  // Ensure this points to your entity package
        factoryBean.setPersistenceUnitName("postgresPU");

        // Explicitly set Hibernate as the JPA provider
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager postgresTransactionManager(@Qualifier("postgresEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean emfb) {
        return new JpaTransactionManager(emfb.getObject());
    }
}
