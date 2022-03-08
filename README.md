# pet-clinic

[![<build>](https://circleci.com/gh/yonatankarp/pet-clinic.svg?style=svg)](https://circleci.com/gh/yonatankarp/pet-clinic)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=yonatankarp_pet-clinic&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=yonatankarp_pet-clinic)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=yonatankarp_pet-clinic&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=yonatankarp_pet-clinic)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=yonatankarp_pet-clinic&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=yonatankarp_pet-clinic)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=yonatankarp_pet-clinic&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=yonatankarp_pet-clinic)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=yonatankarp_pet-clinic&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=yonatankarp_pet-clinic)



Custom implementation of the classic [Spring Boot Pet Clinic](https://projects.spring.io/spring-com.yonatankarp.petclinic.petclinic/)
solution.

## Profiles

This application can be executed in multiple modes:

 - In memory Map storage (profile: `map`)
 - JPA Hibernate storage (profile: `jpa`)

The execution will be selected by the active profile of the application.

To run the application with Map implementation add the following to the
`application.properties` file:

```properties
spring.profiles.active=<PROFILE>
```

For example:

```properties
spring.profiles.active=jpa
```

## Compiling the CSS

The CSS of this project is compiled from the folder `src/main/rersoures/static/resources/scss`
sources, combined with the [Bootstrap](https://getbootstrap.com/) library. If
you make changes to the `scss`, or upgrade Bootstrap, you will need to re-compile
the project using Maven , i.e. `./mvnw package`.
