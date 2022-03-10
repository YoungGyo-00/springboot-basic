package com.example.interceptor.interceptor;

import com.example.interceptor.annotation.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    // 1. 컨트롤러 실행 직전에 동작 true->동작
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                        .query(request.getQueryString())
                        .build()
                        .toUri();

        log.info("request url : {}", url);
        log.info("handler : {}", handler);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        // Auth 권한을 가진 요청에 대해서 세션 쿠키
        if(hasAnnotation) {
            // 권한 체크
            String query = uri.getQuery();
            if(query.equals("name=steve")){
                return true;
            }
            throw new AuthException();
        }

        return true;
    }

    private boolean checkAnnotation(Object handler, Class clazz){
        // resource가 정적 파일이면 true
        if (handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // Auth annotation이 있을 때는 true
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            return true;
        }

        return false;
    }
}
