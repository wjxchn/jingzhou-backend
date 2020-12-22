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


        if (isCheck) {
            registry.addInterceptor(loginInterceptor).
                    addPathPatterns("/api/view/user/login").
                    addPathPatterns("/user/showuserinfo").
                    addPathPatterns("/user/changeuserinfo/password").
                    addPathPatterns("/user/changeuserinfo/email").
                    addPathPatterns("community/generatemessage").
                    addPathPatterns("communitu/follow").
                    addPathPatterns("community/receivemessage").
                    addPathPatterns("/govern/claimportal").
                    addPathPatterns("/govern/changeportal/{changetype}");

        }

    }

}
