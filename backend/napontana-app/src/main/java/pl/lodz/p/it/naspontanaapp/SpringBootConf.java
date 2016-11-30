package pl.lodz.p.it.naspontanaapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ComponentScan
@EnableSwagger2
@EnableAutoConfiguration
public class SpringBootConf extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootConf.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Value("${cors.allowedOrigins}")
            private String allowedOrigins;

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(allowedOrigins);
            }
        };
    }

    @Bean
    public Module bindJodaJacksonModule() {
        return new JodaModule();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

//    @Bean
//    public Filter loggingFilter(){
//        AbstractRequestLoggingFilter f = new AbstractRequestLoggingFilter() {
//
//            @Override
//            protected void beforeRequest(HttpServletRequest request, String message) {
//                System.out.println("beforeRequest: " +message);
//            }
//
//            @Override
//            protected void afterRequest(HttpServletRequest request, String message) {
//                System.out.println("afterRequest: " + message);
//            }
//        };
//        f.setIncludeClientInfo(true);
//        f.setIncludePayload(true);
//        f.setIncludeQueryString(true);
//
//        f.setBeforeMessagePrefix("BEFORE REQUEST  [");
//        f.setAfterMessagePrefix("AFTER REQUEST    [");
//        f.setAfterMessageSuffix("]\n");
//        return f;
//    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConf.class, args);
    }

}
