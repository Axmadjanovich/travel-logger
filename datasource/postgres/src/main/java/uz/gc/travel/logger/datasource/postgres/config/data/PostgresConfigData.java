package uz.gc.travel.logger.datasource.postgres.config.data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author a.ergashev
 * Date: 11/27/2023
 * Time: 12:05 PM
 */
@ConfigurationProperties(prefix = "postgres")
public record PostgresConfigData(String host, Integer port, String db, String username, String password, String schema) {
}
