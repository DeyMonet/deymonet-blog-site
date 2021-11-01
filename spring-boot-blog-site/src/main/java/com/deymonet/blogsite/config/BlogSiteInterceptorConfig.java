package com.deymonet.blogsite.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.deymonet.blogsite.entity.Topic;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;

@SuppressWarnings("deprecation")
@Configuration
public class BlogSiteInterceptorConfig extends WebMvcConfigurerAdapter{

	
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		
		ObjectMapper mapper = new ObjectMapper() {
			private static final long serialVersionUID = 1L;
			
			protected DefaultSerializerProvider _serializerProvider(SerializationConfig config) {
				return super._serializerProvider(config.withView(Topic.class));
			}
		};
		mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
		converter.setObjectMapper(mapper);
		converters.add(converter);
	}
}
