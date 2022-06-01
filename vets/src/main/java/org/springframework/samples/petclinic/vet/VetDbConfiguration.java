package org.springframework.samples.petclinic.vet;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "org.springframework.samples.petclinic.vet"
)
public class VetDbConfiguration {
    /*
        @Autowired
        private Environment env;

        @Primary
        @Bean
        @ConfigurationProperties(prefix = "spring.vet.datasource")
        public DataSource mySqlDataSource(){
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(env.getProperty("spring.booking.datasource.driver-class-name"));
            dataSource.setSchema(env.getProperty("spring.vet.datasource.schema"));
            dataSource.
            return dataSource;
        }

     */
    @Bean(name = "vetDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.vet.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "vetEntityManager")
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                            @Qualifier("vetDataSource") DataSource dataSource)  {
        return builder
                .dataSource(mysqlDataSource())
                .properties(hibernateProperties())
                .packages("com.javaskool.model.booking")
                .persistenceUnit("booking_PU")
                .build();
    }

    @Primary
    @Bean(name = "bookingTransactionManager")
    public PlatformTransactionManager mysqlTransactionManager(@Qualifier("bookingEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }



}
