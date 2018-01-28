package com.myuan.web.config;
/*
 * @author liuwei
 * @date 2018/1/20 21:36
 * shiro配置类
 */

import com.google.common.collect.Maps;
import com.myuan.web.shiro.MyShiroRealm;
import java.util.Map;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //路径拦截器
        Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/reg", "anon");
        filterChainDefinitionMap.put("/user/forget", "anon");
        filterChainDefinitionMap.put("/user/logout", "logout");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/jie/detail", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/system/404");
        return shiroFilterFactoryBean;
    }

    /**
     * <liuwei> [2018/1/20 21:50]
     * 权限范围
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

}
