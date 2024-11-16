package src.be.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import src.be.Interceptors.ApiInterCeptors;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    ApiInterCeptors apiInterCeptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterCeptors).addPathPatterns("/api/**");
    }
}
