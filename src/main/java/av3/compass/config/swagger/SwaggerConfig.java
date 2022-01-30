package av3.compass.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import av3.compass.modelo.User;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration

public class SwaggerConfig {
	
	@Bean
	public Docket compassApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("av3.compass"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.ignoredParameterTypes(User.class)
				.globalOperationParameters(
						Arrays.asList(
								new ParameterBuilder()
								.name("Authorization")
								.description("Token JWT")
								.modelRef(new ModelRef("string"))
								.parameterType("header")
								.required(false)
								.build()));
	}


}


