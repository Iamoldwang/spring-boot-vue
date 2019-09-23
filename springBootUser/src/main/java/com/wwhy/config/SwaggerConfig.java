package com.wwhy.config;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("")
    private String pathMapping;


    /**
     * @return
     * @description:注册bean生成API
     * @time:2018年1月6日 下午3:07:07
     * @user:WangPan
     */
    @Bean
    public Docket productApi() {
        String tokenstr = "3AC97A924D7A2E3298B275F19EC02AF2";
        ParameterBuilder tokenParameterBuilder = new ParameterBuilder();

        tokenParameterBuilder.name("token").defaultValue(tokenstr).description("用户token")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        List<Parameter> addParameters = new ArrayList<Parameter>();
        addParameters.add(tokenParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(addParameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wwhy.controller"))
                .build()
                .apiInfo(initApiInfo());
    }

    /**
     * @return
     * @description:信息主体
     * @time:2018年1月6日 下午3:08:22
     * @user:WangPan
     */
    private ApiInfo initApiInfo() {
        return new ApiInfoBuilder().title("西二旗科技大学运动会管理系统API")
                .description(initContextInfo())
                .version("1.0")
                .contact(new Contact("wangpan", "https://blog.csdn.net/u013254183/article/details/89448758", "wangpanfigo@126.com"))
                .build();
    }

    /**
     * @return
     * @description:描述信息
     * @time:2018年1月6日 下午3:08:10
     * @user:WangPan
     */
    private String initContextInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("REST API 设计在细节上有很多自己独特的需要注意的技巧，并且对开发人员在构架设计能力上比传统 API 有着更高的要求。")
                .append("<br/>")
                .append("本文通过翔实的叙述和一系列的范例，从整体结构，到局部细节，分析和解读了为了提高易用性和高效性，REST API 设计应该注意哪些问题以及如何解决这些问题。");

        return sb.toString();
    }

}
