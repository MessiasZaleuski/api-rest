package com.gft.desafio_api.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
		.apis(RequestHandlerSelectors.basePackage("com.gft.desafio_api"))
		.paths(PathSelectors.any())
		.build()
		.apiInfo(apiInfo())
		.tags(new Tag("Clientes", "Gerenciar os clientes"), new Tag("Fornecedor", "Gerenciar os fornecedores" )
				,new Tag("Produtos", "Gerenciar os produtos" ), new Tag("Vendas", "Gerenciar as vendas" ));
				
	}
	
	//MODIFICANDO COMENTÁRIOS DA PÁGINA WEB
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("DESAFIO API GFT")
				.description("API aberta para clientes")
				.version("1")
				.build();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui.html")
		.addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**")
		.addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
		
}
