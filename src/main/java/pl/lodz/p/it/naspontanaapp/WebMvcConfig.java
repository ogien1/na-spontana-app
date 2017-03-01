package pl.lodz.p.it.naspontanaapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import pl.lodz.p.it.naspontanaapp.logger.RequestLogger;

/**
 * Created by piotr on 30.11.16.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private RequestLogger requestLogger;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(requestLogger);
	}
}
