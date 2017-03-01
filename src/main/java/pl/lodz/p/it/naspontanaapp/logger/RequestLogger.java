package pl.lodz.p.it.naspontanaapp.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by piotr on 30.11.16.
 */
@Component
public class RequestLogger extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return super.preHandle(request, response, handler);
	}
}
