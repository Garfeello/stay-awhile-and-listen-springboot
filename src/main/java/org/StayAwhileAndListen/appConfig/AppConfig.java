package org.StayAwhileAndListen.appConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("org.StayAwhileAndListen")
public class AppConfig {

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("http://localhost:3000", "http://192.168.1.27:3000").allowedHeaders("Access-Control-Allow-Origin");
//    }

}
//@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.27:3000"})