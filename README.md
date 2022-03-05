# pet-clinic

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
