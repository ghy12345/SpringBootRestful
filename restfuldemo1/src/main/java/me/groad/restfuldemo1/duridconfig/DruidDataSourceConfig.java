package me.groad.restfuldemo1.duridconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @BelongsProject: moff
 * @BelongsPackage: cn.purvavideha.moff.config
 * @Author: Groad
 * @CreateTime: 2019-01-02 15:39
 * @Description:
 * @Version: 1.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidDataSourceConfig
{
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private int initialSize;
    private int minIdle;
    private int maxActive;
    private long maxWait;
    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private int maxPoolPreparedStatementPerConnectionSize;
    private String filters;
}
