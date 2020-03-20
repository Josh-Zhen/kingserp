package com.moonlit.kingserp.common.swagger2;

import com.moonlit.kingserp.common.annotation.LoginUser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置
 *
 * @author Joshua
 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression(value = "${test.enabled:true}")
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(LoginUser.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.moonlit.kingserp"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Kings ERP APIs")
                .description("Kings ERP")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    /**
     * 设置可跨域
     *
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                //开放所有的域 ： allowedOrigins("*")
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
