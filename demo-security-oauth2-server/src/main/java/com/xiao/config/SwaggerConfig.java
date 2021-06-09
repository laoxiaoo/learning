
package com.xiao.config;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableOpenApi
//@EnableKnife4j
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        //返回文档摘要信息
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                //在Operation注解下都扫描进入接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(Operation.class))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters())
                .globalResponses(HttpMethod.GET, getGlobalResponseMessage())
                .globalResponses(HttpMethod.POST, getGlobalResponseMessage());
    }

    /**
     * 生成接口信息，包括标题、联系人等
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("认证中心接口文档")
                .description("认证中心接口文档")
                .contact(new Contact("mlone", "https://malonexiao.gitee.io/xiaoxiao/#/", "xxx@foxmail.com"))
                .version("1.0")
                .build();
    }

    /**
     * 封装全局通用参数
     * @return
     */
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("Authorization")
                .description("认证码")
                .required(true)
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;
    }

    /**
     * 通用相应信息
     * @return
     */
    private List<Response> getGlobalResponseMessage() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("404").description("未找到资源").build());
        return responseList;
    }
}

