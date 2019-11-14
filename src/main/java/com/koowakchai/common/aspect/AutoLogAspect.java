package com.koowakchai.common.aspect;

import com.koowakchai.common.util.IPUtils;
import com.koowakchai.common.util.SpringContextUtils;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Aspect
@Order(1)
@Component
public class AutoLogAspect {
    /**
     * 定义一个方法，用于声明切入点表达式，方法中一般不需要添加其他代码
     * 使用@Pointcut声明切入点表达式
     * 后面的通知直接使用方法名来引用当前的切点表达式；如果是其他类使用，加上包名即可
     */
    @Pointcut("execution(public * com.koowakchai.controller.*Controller.*(..))")
    public void declearJoinPointExpression(){}

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("declearJoinPointExpression()") //该标签声明次方法是一个前置通知：在目标方法开始之前执行
    public void beforMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("!!!!! This method "+methodName+" begin. param<"+ args+">");
    }

    /**
     * 后置通知（无论方法是否发生异常都会执行,所以访问不到方法的返回值）
     * @param joinPoint
     */
    @After("declearJoinPointExpression()")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("!!! this method "+methodName+" end.");
    }

    /**
     * 返回通知（在方法正常结束执行的代码）
     * 返回通知可以访问到方法的返回值！
     * @param joinPoint
     */
    @AfterReturning(value="declearJoinPointExpression()",returning="result")
    public void afterReturnMethod(JoinPoint joinPoint,Object result){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("!!! this method "+methodName+" end.result<"+result+">");
    }

    /**
     * 异常通知（方法发生异常执行的代码）
     * 可以访问到异常对象；且可以指定在出现特定异常时执行的代码
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(value="declearJoinPointExpression()",throwing="ex")
    public void afterThrowingMethod(JoinPoint joinPoint,Exception ex){
        String methodName = joinPoint.getSignature().getName();
        System.out.println("!!! this method "+methodName+" end.ex message<"+ex+">");
    }

    /**
     * 环绕通知(需要携带类型为ProceedingJoinPoint类型的参数)
     * 环绕通知包含前置、后置、返回、异常通知；ProceedingJoinPoin 类型的参数可以决定是否执行目标方法
     * 且环绕通知必须有返回值，返回值即目标方法的返回值
     * @param point
     */
    @Around(value="declearJoinPointExpression()")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable{

        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        MethodSignature signature = (MethodSignature) point.getSignature();
        //请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        //请求的参数
        Object[] args = point.getArgs();
        //获取request
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        //获取请求的IP地址
        String ip= IPUtils.getIpAddr(request);
        //获取请求URL
        String url = request.getRequestURL().toString();
        //获取http请求方式
        String type = request.getMethod();

        System.out.println("【请求方式】"+"IP:"+ip+"    url:"+url+"    httptype:"+type);
        System.out.println("【请求方法】"+className+"."+methodName+""+getParams(signature,args));
        System.out.println("【返回结果】"+JSONObject.toJSONString(result));
        System.out.println("【请求耗时】"+time+"ms");
        return result;
    }

    /**
     * 获取请求方法参数类型、参数名称、参数值
     * @param signature
     * @param args
     * @return
     */
    public String getParams(MethodSignature signature,Object[] args){
        String paramsNames[]=signature.getParameterNames();
        Class paramsTypes[]=signature.getParameterTypes();
        String params="  (";
        for(int i=0;i<args.length;i++){
            String typeName=paramsTypes[i].getName();
            String paramName=paramsNames[i];
            String param=JSONObject.toJSONString(args[i]);
            params+=typeName+" "+paramName+ " "+param+",";
        }
        params=params.substring(0,params.length() - 1);
        return params+")";
    }

}
