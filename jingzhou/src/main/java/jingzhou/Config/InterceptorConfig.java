package jingzhou.Config;


import jingzhou.Intercepter.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Value("${security.check}")
    private boolean isCheck;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //暂时需要商量
//        if (isCheck) {
//            registry.addInterceptor(loginInterceptor).
//                    addPathPatterns("/api/view/user/login").
//                    excludePathPatterns("/api/view/user/logout");
//
//        }

    }

}
