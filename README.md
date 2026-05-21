# Bootiful Kotlin

## Basics of Kotlin

- Introduction to Kotlin fundamentals
- Why Kotlin works well with Spring
- Kotlin language features for modern backend development

---

## Generate a Project on Start.spring.io

Use https://start.spring.io with Kotlin support enabled.

### Dependencies

- Kotlin
- Spring Boot DevTools
- Spring Data JDBC
- JTE
- PostgreSQL
- Ollama
- Spring Web
- OpenTelemetry
- Docker Compose Support
- Spring Security
- WebAuthn

---

## Build Setup

### Maven Plugin

Add Kotlin Maven plugin support.

### Prancer

- Configure and use Prancer for development workflows.

### Virtual Threads

- Enable and experiment with Java virtual threads.

---

## Configure AI Support

Configure the Ollama model:

```properties
spring.ai.ollama.chat.model=gemma4:26b
```

---

## Spring Data JDBC

### Create a Repository for Dogs

Use Kotlin data classes with Spring Data JDBC.

Example ideas:

- `Dog` data class
- `CrudRepository<Dog, Long>`

---

## Bean Configuration with `BeanRegistrarDsl()`

Use Kotlin DSL configuration.

### Register a `ChatClient`

- Configure the AI chat client.

### Register an `ApplicationRunner`

- Inject the `ChatClient`
- Run prompts at startup

Example responsibilities:

- Test AI connectivity
- Print responses to the console

---

## Using Exposed

- Introduce JetBrains Exposed
- Compare it with Spring Data JDBC

---

## AI Endpoint

### Create a Simple `@Controller`

Add an `/ask` endpoint that uses AI.

Example flow:

- Accept a prompt
- Send prompt to `ChatClient`
- Return AI response

---

## Add Skills Support to `ChatClient`

- Configure skills/tool support
- Demonstrate extensibility with AI workflows

---

## JTE Views

### Add a `/dogs` Endpoint

Render dogs using JTE templates.

### Configuration

```properties
gg.jte.template-suffix=.kte
```

### Template Setup

- Copy `dogs.kte` into:

```text
src/main/jte
```

- Make sure the package declaration is correct.

---

# Security

## Add JDBC Users

- Configure JDBC-backed authentication

---

## Extract a `Dogs` Interface

Separate persistence concerns behind an abstraction.

Example:

- `Dogs` interface
- Repository implementation

---

## Implement Using Spring Data JDBC

- Build the first implementation with Spring Data JDBC.

---

## Use `Dogs` in the Controller

- Inject the interface instead of concrete implementations.

---

## Switch to Exposed

Add dependency:

```xml
<dependency>
    <groupId>org.jetbrains.exposed</groupId>
    <artifactId>exposed-spring-boot4-starter</artifactId>
    <version>1.3.0</version>
</dependency>
```

---

## Add WebAuthn

- Configure passwordless authentication
- Demonstrate passkey login support

---

## Add OTT

- Configure One-Time Token authentication support

---

## `SecurityContextHolder` and JSpecify

Demonstrate non-null references enabled by JSpecify.dev.

Example:

```kotlin
registerBean {
    router {
        GET("/") {
            val name = SecurityContextHolder.getContext().authentication!!.name
            ServerResponse.ok().body(mapOf("message" to name))
        }
    }
}
```

---

## Create a `/` Endpoint

- Show the authenticated principal
- Return the authenticated username

---

## Run the Application

Open:

```text
http://localhost:3000
```

---

# Recommended Talk

Make sure to see Sébastien Deleuze’s talk:

*Idiomatic Kotlin*

at KotlinConf tomorrow.

Speaker page:

https://kotlinconf.com/speakers/ce11156d-9a41-4f12-b236-3bdac0ee3a2a/
