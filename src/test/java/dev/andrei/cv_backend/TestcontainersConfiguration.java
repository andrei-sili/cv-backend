package dev.andrei.cv_backend;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 * Test-only configuration that starts a throwaway PostgreSQL container.
 * {@code @ServiceConnection} wires Spring Boot's datasource to it automatically,
 * so tests run against a real PostgreSQL without needing a locally running database.
 */
@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

    @Bean
    @ServiceConnection
    PostgreSQLContainer postgresContainer() {
        return new PostgreSQLContainer("postgres:17");
    }
}