# Bootiful Kotlin

basics of kotlin 
start dot spring io 
	- Kotlin!
	- (DEVTOOLS!, data jdbc, jte, postgresql, ollama, web, open telemetry, docker compose, security, webauthn)
maven plugin 
prancer 
virtual threads
configure ai model using ollama (`spring.ai.ollama.chat.model=gemma4:26b`)
data jdbc repository for dogs using data classes 
configure things with `BeanRegistrarDsl()`
	- `ChatClient`
	- `ApplicationRunner` that injects the `ChatClient`
exposed 
simple @Controller with `/ask` endpoint to use AI 
add skills support to `ChatClient`
add controller endppint to return dogs from `/dogs` endpoint using JTE view. 
	- add `gg.jte.template-suffix=.kte` 
	- then copy the dogs.kte to `src/main/jte`; make sure the package is correct!
security 
	- add JDBC users
	- extract out `Dogs` interface
	- build implementation using Spring Data JDBC
	- use `Dogs` in the controller
	- switch to Exposed
	- add `org.jetbrains.exposed:exposed-spring-boot4-starter:1.3.0`
	-  	
	- add webauthn
	- add ott  
	- `SecurityContextHolder`.... show its got non null references thanks to JSpecify.dev
- create a controller for `/` showing the authenticated principal
	```   	
	registerBean {
		router {
		    GET("/") {
		        val name = SecurityContextHolder.getContext().authentication!!.name
		        ServerResponse.ok().body(mapOf("message" to name))
		    }
		}
	}        
	```
- goto localhost:3000 
