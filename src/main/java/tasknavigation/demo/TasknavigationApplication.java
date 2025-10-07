// RODAR TODA A APLICAÇÃO
// processa dados, realiza cálculos, interage com o banco 
// de dados e executa as operações
// principais que suportam a funcionalidade da aplicação.

package tasknavigation.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TasknavigationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasknavigationApplication.class, args);
    }

}

// Classe de configuração para CORS
@Configuration
class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "http://localhost:58649", "http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
	