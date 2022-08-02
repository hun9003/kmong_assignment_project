package com.parkjinhun.kmong.kmong_assignment_project.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("크몽 사전과제 프로젝트")
                .description("API 명세서")
                .build();
    }

    @Bean
    public Docket commonApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, newArrayList(
                        new ResponseBuilder().code("500").description("서버에 오류가 발생").build(),
                        new ResponseBuilder().code("404").description("잘못된 주소").build(),
                        new ResponseBuilder().code("400").description("요청에 필요한 데이터를 잘못 작성").build()
                ))
                .globalResponses(HttpMethod.POST, newArrayList(
                        new ResponseBuilder().code("500").description("서버에 오류가 발생").build(),
                        new ResponseBuilder().code("404").description("잘못된 주소").build(),
                        new ResponseBuilder().code("400").description("요청에 필요한 데이터를 잘못 작성").build()
                ))
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.parkjinhun.kmong.kmong_assignment_project.interfaces"))
                .paths(PathSelectors.ant("/api/v1/**"))
                .build();
    }
}
