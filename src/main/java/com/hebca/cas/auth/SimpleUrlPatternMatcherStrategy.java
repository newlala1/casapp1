package com.hebca.cas.auth;

import org.jasig.cas.client.authentication.UrlPatternMatcherStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * 机能概要:过滤掉一些不需要授权登录的URL
 */
@Component
public class SimpleUrlPatternMatcherStrategy implements UrlPatternMatcherStrategy {

    /**
     * 机能概要: 判断是否匹配这个字符串
     *
     * @param url 用户请求的连接
     * @return true : 不拦截
     * false :必须得登录了
     */
    @Override
    public boolean matches(String url) {
        final String authority=URI.create(url).getPath();
        if(url.contains("/logout")){
            return true;
        }

        List<String> list = Arrays.asList(new String[]{
                "/","/index","favicon.ico","/js/**","/css/**","/img/**","/fonts/**"}
                );
        String name = url.substring(url.lastIndexOf("/"));
        if (name.indexOf("?") != -1) {
            name = name.substring(0, name.indexOf("?"));
        }
        final String visitUrl=name;

        boolean result1=list.stream().anyMatch(x->{
            AntPathMatcher antPathMatcher=new AntPathMatcher();
            boolean r=antPathMatcher.match(x, authority);
            return r;
        });
        if (!result1) {
            System.out.println("拦截URL：" + url);
        }
        return result1;
    }

    /**
     * 正则表达式的规则，这个地方可以是web传递过来的
     */
    @Override
    public void setPattern(String pattern) {

    }
}
