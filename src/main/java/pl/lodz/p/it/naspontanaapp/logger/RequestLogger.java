package pl.lodz.p.it.naspontanaapp.logger;

import java.io.IOException;

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        try {
//            logger.info("request url: {}, request body: {}", request.getRequestURL().toString(), getBody(request));
//        } catch (IOException e) {
//            logger.info("request url: {}", request.getRequestURL().toString());
//        }

        return super.preHandle(request, response, handler);
    }

//    private String getBody(HttpServletRequest request) throws IOException {
//        String body = null;
//        StringBuilder stringBuilder = new StringBuilder();
//        BufferedReader bufferedReader = null;
//
//        try {
//            InputStream inputStream = request.getInputStream();
//            if (inputStream != null) {
//                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                char[] charBuffer = new char[128];
//                int bytesRead = -1;
//                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
//                    stringBuilder.append(charBuffer, 0, bytesRead);
//                }
//            } else {
//                stringBuilder.append("");
//            }
//        } catch (IOException ex) {
//            throw ex;
//        } finally {
//            if (bufferedReader != null) {
//                try {
//                    bufferedReader.close();
//                } catch (IOException ex) {
//                    throw ex;
//                }
//            }
//        }
//
//        body = stringBuilder.toString();
//        return body;
//    }

    private String getBody(HttpServletRequest request) throws IOException {
        return request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
    }
}
