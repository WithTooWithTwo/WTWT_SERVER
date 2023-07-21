package cupitoo.wtwt.config;

import cupitoo.wtwt.annotation.LoginMemberArgumentResolver;
import cupitoo.wtwt.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1) //인터셉터 체인에서 몇번째로?
                .addPathPatterns("/**") //인터셉터 적용경로
                .excludePathPatterns( //예외 경로
                        "/", "/users", "/login", "/error", "/image/**"
                );
    }
}
