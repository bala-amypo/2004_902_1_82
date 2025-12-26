package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
@Configuration
public class SecurityConfig {

    public SecurityConfig() {
        
    }
}

repository




3)	EmployeeProfileRepository.java
package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EmployeeProfile;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Long> {

    Optional<EmployeeProfile> findByEmployeeId(String employeeId);
}
4)	ProductivityMetricRecordRepository.java
package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ProductivityMetricRecord;

@Repository
public interface ProductivityMetricRecordRepository
        extends JpaRepository<ProductivityMetricRecord, Long> {

    List<ProductivityMetricRecord> findByEmployeeId(Long employeeId);
}

5)	TeamSummaryRecordRepository.java
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TeamSummaryRecord;

@Repository
public interface TeamSummaryRecordRepository
        extends JpaRepository<TeamSummaryRecord, Long> {
}

6)	UserAccountRepository.java
package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    Optional<UserAccount> findByUsername(String username);
}

security
1)	CustomUserDetailsService.java
package com.example.demo.security;

import org.springframework.stereotype.Service;

/**
 * Dummy user details service for authentication simulation.
 */
@Service
public class CustomUserDetailsService {

    public CustomUserDetailsService() {}

    public boolean userExists(String username) {
        return true;
    }
}
2)	JwtAuthenticationFilter.java
package com.example.demo.security;

import org.springframework.stereotype.Component;

/**
 * JWT filter placeholder.
 * Not enforcing real authentication.
 */
@Component
public class JwtAuthenticationFilter {

    public JwtAuthenticationFilter() {
        // Filter logic can be added later
    }
}

3)	JwtTokenProvider.java
package com.example.demo.security;

import org.springframework.stereotype.Component;

/**
 * JWT utility class (simplified).
 */
@Component
public class JwtTokenProvider {

    public String generateToken(String username) {
        return "dummy-jwt-token-for-" + username;
    }

    public boolean validateToken(String token) {
        return token != null && token.startsWith("dummy");
    }
}

service:
   impl:
1)	AnomalyFlagServiceImpl.java
package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.AnomalyFlagRecord;
import com.example.demo.repository.AnomalyFlagRecordRepository;
import com.example.demo.service.AnomalyFlagService;

@Service
public class AnomalyFlagServiceImpl implements AnomalyFlagService {

    private final AnomalyFlagRecordRepository repository;

    public AnomalyFlagServiceImpl(AnomalyFlagRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public AnomalyFlagRecord flagAnomaly(AnomalyFlagRecord record) {
        return repository.save(record);
    }

    @Override
    public List<AnomalyFlagRecord> getAllFlags() {
        return repository.findAll();
    }
}

2)	AnomalyRuleServiceImpl.java
package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.AnomalyRule;
import com.example.demo.repository.AnomalyRuleRepository;
import com.example.demo.service.AnomalyRuleService;

@Service
public class AnomalyRuleServiceImpl implements AnomalyRuleService {

    private final AnomalyRuleRepository repository;

    public AnomalyRuleServiceImpl(AnomalyRuleRepository repository) {
        this.repository = repository;
    }

    @Override
    public AnomalyRule createRule(AnomalyRule rule) {
        return repository.save(rule);
    }

    @Override
    public List<AnomalyRule> getActiveRules() {
        return repository.findByActiveTrue();
    }
}

3)	EmployeeProfileServiceImpl.java
package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.EmployeeProfile;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.service.EmployeeProfileService;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository repository;

    public EmployeeProfileServiceImpl(EmployeeProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public EmployeeProfile createEmployee(EmployeeProfile employee) {
        return repository.save(employee);
    }

    @Override
    public EmployeeProfile getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Optional<EmployeeProfile> findByEmployeeId(String employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @Override
    public EmployeeProfile updateEmployeeStatus(Long id, boolean active) {
        EmployeeProfile emp = getEmployeeById(id);
        emp.setActive(active);
        return repository.save(emp);
    }
}

4)	ProductivityMetricServiceImpl.java
package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.ProductivityMetricRecord;
import com.example.demo.repository.ProductivityMetricRecordRepository;
import com.example.demo.service.ProductivityMetricService;

@Service
public class ProductivityMetricServiceImpl implements ProductivityMetricService {

    private final ProductivityMetricRecordRepository repository;

    public ProductivityMetricServiceImpl(ProductivityMetricRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductivityMetricRecord recordMetric(ProductivityMetricRecord record) {
        return repository.save(record);
    }

    @Override
    public Optional<ProductivityMetricRecord> getMetricById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<ProductivityMetricRecord> getAllMetrics() {
        return repository.findAll();
    }
}


5)	TeamSummaryServiceImpl.java
package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.TeamSummaryRecord;
import com.example.demo.service.TeamSummaryService;

@Service
public class TeamSummaryServiceImpl implements TeamSummaryService {

    @Override
    public TeamSummaryRecord generateSummary(String teamName) {
        TeamSummaryRecord record = new TeamSummaryRecord();
        record.setTeamName(teamName);
        return record;
    }
}

6)	UserAccountServiceImpl.java
package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.UserAccount;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.service.UserAccountService;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository repository;

    public UserAccountServiceImpl(UserAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAccount register(UserAccount user) {
        return repository.save(user);
    }

    @Override
    public Optional<UserAccount> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
Service:
1)	AnomalyFlagService.java
package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AnomalyFlagRecord;

public interface AnomalyFlagService {

    AnomalyFlagRecord flagAnomaly(AnomalyFlagRecord record);

    List<AnomalyFlagRecord> getAllFlags();
}

2)	AnomalyRuleService.java
package com.example.demo.service;

import java.util.List;

import com.example.demo.model.AnomalyRule;

public interface AnomalyRuleService {

    AnomalyRule createRule(AnomalyRule rule);

    List<AnomalyRule> getActiveRules();
}

3)	EmployeeProfileService.java
package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.EmployeeProfile;

public interface EmployeeProfileService {

    EmployeeProfile createEmployee(EmployeeProfile employee);

    EmployeeProfile getEmployeeById(Long id);

    Optional<EmployeeProfile> findByEmployeeId(String employeeId);

    EmployeeProfile updateEmployeeStatus(Long id, boolean active);
}

4)	ProductivityMetricService.java
package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.ProductivityMetricRecord;

public interface ProductivityMetricService {

    ProductivityMetricRecord recordMetric(ProductivityMetricRecord record);

    Optional<ProductivityMetricRecord> getMetricById(Long id);

    List<ProductivityMetricRecord> getAllMetrics();
}

5)	TeamSummaryService.java
package com.example.demo.service;

import com.example.demo.model.TeamSummaryRecord;

public interface TeamSummaryService {

    TeamSummaryRecord generateSummary(String teamName);
}


6)	UserAccountService.java
package com.example.demo.service;

import java.util.Optional;

import com.example.demo.model.UserAccount;

public interface UserAccountService {

    UserAccount register(UserAccount user);

    Optional<UserAccount> findByUsername(String username);
}

Util:
1)	DemoApplication.java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * DemoApplication
 * ----------------
 * This is the main entry point of the Spring Boot application.
 *
 * Responsibilities:
 * - Bootstraps the Spring context
 * - Enables component scanning for com.example.demo
 * - Starts embedded server (Tomcat)
 *
 * This class is REQUIRED for:
 * - @SpringBootTest
 * - Application startup
 * - Integration testing
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

resources:

application.properties:
spring.application.name=demo
# Don't change the port
server.port = 9001
# for https
server.forward-headers-strategy=framework 


spring.datasource.url=jdbc:mysql://localhost:3306/productivity_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=Amypo
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

pom.xml:
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.3.4</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>demo</name>
    <description>Demo project for Spring Boot</description>
    <url/>
    <licenses>
        <license/>
    </licenses>
    <developers>
        <developer/>
    </developers>
    <scm>
        <connection/>
        <developerConnection/>
        <tag/>
        <url/>
    </scm>
    <properties>
        <java.version>17</java.version>
    </properties>
    <dependencies>
    <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.3.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
    <version>3.5.6</version>
</dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.3.0</version>
        </dependency>
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>7.8.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
                <dependencies>
                    <dependency>
                        <groupId>org.apache.maven.surefire</groupId>
                        <artifactId>surefire-testng</artifactId>
                        <version>3.2.5</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>

</project>


