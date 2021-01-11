package com.cyj.piano_backend.config;

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
 * @project cloudplatform
 * @package com.css.dj.yw.config
 * @file SwaggerConfig.java 创建时间:2019年7月1日下午1:37:19
 * @title 标题（要求能简洁地表达出类的功能和职责）
 * @description 描述（简要描述类的职责、实现方式、使用注意事项等）
 * @copyright Copyright (c) 2019 中国软件与技术服务股份有限公司
 * @company 中国软件与技术服务股份有限公司
 * @module 模块: 模块名称
 * @author 史雪涛
 * @reviewer 审核人
 * @version 1.0
 * @history 修订历史（历次修订内容、修订人、修订时间等）
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/**
	 * @name 中文名称
	 * @description 相关说明
	 * @time 创建时间:2019年3月8日下午5:36:10
	 * @return Docket
	 * @author 史雪涛
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cyj.piano_backend"))
				.paths(PathSelectors.any())
				.build();
	}

	/**
	 * @name 中文名称
	 * @description 相关说明
	 * @time 创建时间:2019年3月8日下午5:36:19
	 * @return ApiInfo
	 * @author 史雪涛
	 * @history 修订历史（历次修订内容、修订人、修订时间等）
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("小程序后台" + "APIs")
				.description("API接口")
				.version("1.0")
				.build();
	}
}
