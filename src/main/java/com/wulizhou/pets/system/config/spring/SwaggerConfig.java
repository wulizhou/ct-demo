package com.wulizhou.pets.system.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2配置<br/>
 * 生产环境下，应关闭，可去掉EnableSwagger2注解
 * @author wulizhou
 */

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        ParameterBuilder token = new ParameterBuilder();
        Parameter headerToken = token.name("token").description("token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();           // header中的token参数非必填，传空也可以

        List<Parameter> pars = new ArrayList<>();
        pars.add(headerToken);

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(pars)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wulizhou.pets.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("pets系统API接口")
                .description("黄景华、吴立周@版权所有")
                .version("0.0.1")
                .build();
    }


}
