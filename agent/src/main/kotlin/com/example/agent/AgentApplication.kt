package com.example.agent

import org.springframework.ai.chat.client.ChatClient
import org.springframework.beans.factory.BeanRegistrarDsl
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import
import org.springframework.data.annotation.Id
import org.springframework.data.repository.ListCrudRepository
import org.springframework.http.MediaType
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.provisioning.JdbcUserDetailsManager
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.function.ServerResponse
import org.springframework.web.servlet.function.router
import javax.sql.DataSource

@Import(MyBeanRegistrar::class)
@SpringBootApplication
class AgentApplication

fun main(args: Array<String>) {
    runApplication<AgentApplication>(*args)
}

data class Dog(@Id val id: Int, val name: String, val description: String)

interface DogRepository : ListCrudRepository<Dog, Int>


class MyBeanRegistrar : BeanRegistrarDsl({

    registerBean {
        JdbcUserDetailsManager(bean<DataSource>())
    }
    registerBean {
        Customizer<HttpSecurity> {
            it //
                .webAuthn { x ->
                    x.rpId("localhost").rpName("localhost").allowedOrigins("http://localhost:8080")
                } //
                .oneTimeTokenLogin { ott ->
                    ott.tokenGenerationSuccessHandler { request, response, token ->
                        response.writer.println("you've got console mail!")
                        response.contentType = MediaType.TEXT_PLAIN_VALUE
                        println("please go to http://localhost:8080/login/ott?token=${token.tokenValue}")
                    }
                }

        }
    }
    registerBean {
        ApplicationRunner {
            val client = bean<ChatClient>()
            println(
                """
               Hello from Spring Boot! ${
                    client
                        .prompt()
                        .user("tell me a joke")
                        .call()
                        .content()
                }
            """
            )
        }
    }
    registerBean {
        bean<ChatClient.Builder>().build()
    }
    registerBean {
        router {
            GET("/") {
                val name = SecurityContextHolder
                    .getContext()
                    .authentication!!
                    .name
                ServerResponse.ok().body(mapOf("message" to name))
            }
        }
    }
})

@Controller
@ResponseBody
class AssistantController(
    private val cc: ChatClient,
    private val dogs: DogRepository
) {

    @GetMapping("/dogs")
    fun dogs() = ModelAndView(
        "dogs",
        mapOf("dogs" to dogs.findAll())
    )

    @GetMapping("/ask")
    fun ask(@RequestParam question: String) = cc
        .prompt()
        .user(question)
        .call()
        .content()
}