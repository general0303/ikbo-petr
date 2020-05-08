package ru.mirea.serverikbo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/author").setViewName("author");
        registry.addViewController("/book").setViewName("book");
        registry.addViewController("/author/{author_id}").setViewName("/author/{author_id}");
    }
}
