package com.sophos.backendcanvas.Config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final Contact DEFAULT_CONTACT = new Contact(
      "Kanvas app", "https://www.sophossolutions.com/", "soporte@sophos.com.co");
  
    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
        "Kanvas App", "Esta documentación contiene los servicios para la aplicacion kanvas", "1.0",
        "", DEFAULT_CONTACT,
        "Código cerrado", "https://opensource.org/licenses/MIT", Arrays.asList());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
        new HashSet<String>(Arrays.asList("application/json"));

    String message500 = "{"
        + "\"status\": 500,"
        + "\"message\": \"Se genero un error interno en el sistema. Por favor comuniquese con este codigo de error: 1b671ac6-fdcb-4a50-b1ff-56337add8791\""
    + "}";

    List<Response> messages = Arrays.asList(
        new ResponseBuilder().code("404").description("NotFound").build(),
        new ResponseBuilder().code("500").description(message500).build());

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
            .apis(RequestHandlerSelectors.basePackage("com.sophos.backendcanvas.Controladores"))
            .build()
            .apiInfo(DEFAULT_API_INFO)
            .produces(DEFAULT_PRODUCES_AND_CONSUMES)
            .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
            .globalResponses(HttpMethod.GET, messages)
            .globalResponses(HttpMethod.POST, messages)
            .globalResponses(HttpMethod.PUT, messages)
            .globalResponses(HttpMethod.DELETE, messages);
    }

}
