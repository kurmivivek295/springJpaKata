package pl.java.scalatech.collection.map.collection;

import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import pl.java.scalatech.config.hikari.HikariCPConfiguration;

@EntityScan(basePackages = "pl.java.scalatech.domain.mapkey.basic_collection")
@EnableJpaRepositories(basePackages = "pl.java.scalatech.repository.map.collection")
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, PersistenceExceptionTranslationAutoConfiguration.class,
    PropertyPlaceholderAutoConfiguration.class,HikariCPConfiguration.class })
@Profile("mapCollection")
@Configuration
public class BookStoreMapCollectionConfig {

}
