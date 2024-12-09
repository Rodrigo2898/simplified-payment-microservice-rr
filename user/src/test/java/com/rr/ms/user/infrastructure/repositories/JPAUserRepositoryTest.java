package com.rr.ms.user.infrastructure.repositories;

import com.rr.ms.user.domain.UserRepository;
import com.rr.ms.user.domain.UserRepositoryTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan

@DataJpaTest
class JPAUserRepositoryTest extends UserRepositoryTest {

    @Autowired
    private JPAUserRepository userRepository;

    @Container
    private static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16.6")
            .withDatabaseName("user_service")
            .withUsername("userr")
            .withPassword("password");

    @BeforeAll
    void setUp() {

        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
        System.setProperty("spring.datasource.driver-class-name", postgreSQLContainer.getDriverClassName());
    }

    @Override
    public UserRepository userRepository() {
        return userRepository;
    }
}
