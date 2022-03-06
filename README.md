# pet-clinic

[![<build>](https://circleci.com/gh/yonatankarp/pet-clinic.svg?style=svg)](https://circleci.com/gh/yonatankarp/pet-clinic)



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
