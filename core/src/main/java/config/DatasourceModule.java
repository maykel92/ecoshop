package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dagger.Module;
import dagger.Provides;
import dto.AppConfig;
import jakarta.inject.Singleton;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Module
public class DatasourceModule {

    @Provides
    @Singleton
    public AppConfig provideAppConfig() {
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("aplication.properties")) {

            if (input == null) {
                throw new RuntimeException("No se encuentra aplication.properties en el claspath");
            }

            Properties props = new Properties();
            props.load(input);

            return AppConfig.of(props);

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar aplication.properties ");
        }

    }

    @Provides
    @Singleton
    public DataSource provideDataSource(AppConfig appConfig) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(System.getenv("DB_URL"));
        config.setUsername(System.getenv("DB_USER"));
        config.setPassword(System.getenv("DB_PASS"));
        config.setDriverClassName(appConfig.driverDb());
        config.setMaximumPoolSize(appConfig.maxPoolSize());
        config.setMinimumIdle(appConfig.minPoolSize());
        config.setIdleTimeout(appConfig.datasourceTimeOut());
        config.setPoolName(appConfig.appName());

        return new HikariDataSource(config);
    }

    @Provides
    @Singleton
    public DSLContext provideDslContext(DataSource dataSource) {
        return DSL.using(dataSource, SQLDialect.MYSQL);
    }
}
