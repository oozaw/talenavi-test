package wahyu_ady.talenavi_test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration {

   @Bean
   public WebMvcConfigurer corsConfigurer() {
      return new WebMvcConfigurer() {
         @Override
         public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**") // Apply to all endpoints
               .allowedOrigins("*") // Allow all origins
               .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific methods
               .allowedHeaders("*") // Allow all headers
               .allowCredentials(false) // Allow credentials (cookies, authorization headers)
               .maxAge(3600); // Cache pre-flight response for 3600 seconds
         }
      };
   }
}
