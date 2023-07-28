package cupitoo.wtwt.interceptor;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;
import java.util.Set;

@Slf4j
public class RequestLogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("[" + request.getRequestURL().toString() + "] " + request.getMethod());

        if(request.getMethod().equals("GET")) return true;

        Set<String> keySet = request.getParameterMap().keySet();
        if(keySet.isEmpty()) {
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            if(!messageBody.isEmpty()) {
                log.debug("messageBody = {}", messageBody);
            }
        } else {
            for(String key: keySet) {
                log.debug(key + ": " + request.getParameter(key));
            }
        }

        return true;
    }
}
