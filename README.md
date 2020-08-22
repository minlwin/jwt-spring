# jwt-spring
Library to use JWT Token base Security in Spring Boots MVC REST Application.

When we ever write Spring Base REST Application, we have to write Token Base Security all the time. We ever use JWT Token for Token Base Security. For every project we implement the same codes again and again.

This project is to help JWT Token Security in Spring Boots MVC REST Application.

## How to use?

Clone jwt-spring Project from Git Repository
```
git clone git@github.com:minlwin/jwt-spring.git
```

Install jwt-spring library to local repository.
```
cd jwt-spring/jwt-spring
mvn install
```

Add Dependency to Spring Boots Project
```
<dependency>
	<groupId>com.jdc</groupId>
	<artifactId>jwt-spring</artifactId>
	<version>${jwt-spring-version}</version>
</dependency>
```

Write Configuration by Implementing JwtConfiguration Interface
```
@Configuration
public class ApiJwtSetting implements JwtConfiguration {	
}
```

## How JWT-Spring Work


