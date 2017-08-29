package com.company.project.aspect;

import com.company.common.exception.GlobalException;
import com.company.common.model.StatusCode;
import com.company.project.jwt.AuthTokenDetails;
import com.company.project.jwt.JsonWebTokenUtility;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Description:${Description}
 * @Author : Mr.Cheng
 * @Date:2017/8/6
 */
@Aspect
@Component
@Order(2)
public class AuthAspect {

    @Value("${jwt.unAuhUrl:''}")
    private String unAuthUrl;

    @Value("${jwt.auth.switch:false}")
    private boolean flag;

    @Autowired
    private JsonWebTokenUtility jsonWebTokenUtility;

    private List<Pattern> listUnAuthUrlRegex=null;

    private void init(){
       String [] listUnAuthUrl=unAuthUrl.split(",");
        listUnAuthUrlRegex=new ArrayList<>();
       for(String regex:listUnAuthUrl){
           regex=regex.replaceAll("/+","/").replaceAll("\\*",".*");
           listUnAuthUrlRegex.add(Pattern.compile(regex));
       }
    }

    @Pointcut("execution(public * com.company.project.web..*.*(..))")
    public void auth(){}
    @Before("auth()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        if(flag) {
            if(listUnAuthUrlRegex==null){
                init();
            }
            // 获取HttpServletRequest
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            if(!pattern(request)){
                jsonWebTokenUtility.init();
                String token = request.getHeader("Authorization");
                //验证token
                if(token==null || "".equals(token)){
                    throw new GlobalException("需要token", StatusCode.Fail_Code);
                }
                AuthTokenDetails authTokenDetails=jsonWebTokenUtility.parseAndValidate(token);
                //TODO  authTokenDetails 对这个进行处理保存
                jsonWebTokenUtility=null;
                authTokenDetails=null;
            }

        }
    }

    /**
     * 匹配方法
     * @param request
     * @return
     */
    private boolean pattern(HttpServletRequest request){
        String url=request.getRequestURI();
        String contextPath=request.getContextPath();
        url.replace(contextPath,"").replaceAll("/+","/");
        for(Pattern regex: listUnAuthUrlRegex){
            if(regex.matcher(url).matches()){
                return true;
            }
        }
        return false;
    }
}
