package br.com.quadrosepaineis.quadrosepaineisapikotlin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@Configuration
class SwaggerConfig {

    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.quadrosepaineis"))
                .paths(PathSelectors.any())
                .build()
    }

    private fun apiInfo():ApiInfo {
        return ApiInfo("REST API da Quadros e Painéis",
                "A quadros e painéis é uma empresa familiar que produz quadros em geral usando elementos naturais encontrados na nosso região da Paraíba",
                "V 0.0.1",
                "Terms of service",
                Contact("Kawe Ramon", "https://kaweramon.github.io", "kawe.ufpbsi@gmail.com"),
                "License of API", "API license URL", emptyList())
    }

}