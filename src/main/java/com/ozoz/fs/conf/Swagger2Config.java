package com.ozoz.fs.conf;

import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * fusion-platform.
 *
 * @author : Matrix [xhyrzldf@gmail.com]
 * 19-1-10 .
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .produces(Sets.newHashSet("application/json"))
                .consumes(Sets.newHashSet("application/json"))
                .protocols(Sets.newHashSet("http","https"))
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .useDefaultResponseMessages(true)
//                .globalResponseMessage(RequestMethod.GET, getResMsg())
                .select()
                // 指定controller存放的目录路径
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
//                .globalOperationParameters(setHeaderToken())
                ;
    }
//    private List<Parameter> setHeaderToken() {
//        ParameterBuilder tokenPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<>();
//        tokenPar.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        pars.add(tokenPar.build());
//        return pars;
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("FS接口文档")
                // 文档描述
                .description("LFS接口文档与测试页面")
                .termsOfServiceUrl("tykj")
                //.termsOfServiceUrl("http://localhost:8080/")
                .version("v1")
                //.contact(new Contact("fusion-group", "http://192.168.1.155:8080/fusion-group/fusion-platform", "fusion-group@example.com"))
                .build();
    }

//    private ArrayList<ResponseMessage> getResMsg() {
//        return newArrayList(new ResponseMessageBuilder()
//                        .code(500)
//                        .message("服务器内部发生了某种错误")
//                        .responseModel(new ModelRef("Error"))
//                        .build(),
//                new ResponseMessageBuilder()
//                        .code(404)
//                        .message("用户发出的请求针对的是不存在的记录，服务器没有进行操作")
//                        .responseModel(new ModelRef("Exception"))
//                        .build(),
//                new ResponseMessageBuilder()
//                        .code(406)
//                        .message("用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）")
//                        .responseModel(new ModelRef("Exception"))
//                        .build(),
//                new ResponseMessageBuilder()
//                        .code(501)
//                        .message("不支持的HTTP请求，请检查HTTP TYPE与资源路径是否正确")
//                        .responseModel(new ModelRef("Exception"))
//                        .build());
//    }

}
