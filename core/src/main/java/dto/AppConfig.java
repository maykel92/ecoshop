package dto;

import java.util.Properties;

public record AppConfig(String driverDb,
                        String appName,
                        boolean virtualThread,
                        int serverPort,
                        int minPoolSize,
                        int maxPoolSize,
                        int datasourceTimeOut) {
    public static AppConfig of(Properties props) {
        return new AppConfig(
                props.getProperty("datasource.driver"),
                props.getProperty("app.name"),
                Boolean.parseBoolean(props.getProperty("app.server.virtualThreds")),
                Integer.parseInt(props.getProperty("app.server.port")),
                Integer.parseInt(props.getProperty("datasource.pool.size.min")),
                Integer.parseInt(props.getProperty("datasource.pool.size.max")),
                Integer.parseInt(props.getProperty("datasource.timeout"))
        );
    }
}
