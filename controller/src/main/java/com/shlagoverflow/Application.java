package com.shlagoverflow;

import com.shlagoverflow.api.util.DataParser;
import com.shlagoverflow.api.util.LuceneUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableAutoConfiguration
@EnableWebMvc
@SpringBootApplication
@ComponentScan(basePackages = "com.shlagoverflow")
public class Application {

    private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        LOGGER.info("Indexing data...");
        final LuceneUtil lucene = LuceneUtil.getInstance();
        DataParser.getInstance().getQuestions().forEach(lucene::indexTitle);
        LOGGER.info(String.format("%s %d %s", "Indexed ", DataParser.getInstance().getQuestions().size(), " entries with Lucene."));


        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("*").allowedOrigins("http://localhost:4200");
            }
        };
    }
}
