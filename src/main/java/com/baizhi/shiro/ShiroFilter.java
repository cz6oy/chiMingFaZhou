package com.baizhi.shiro;


import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroFilter {
    /*@Autowired
    private SecurityManager securityManager;*/
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, String> map = new HashMap<>();
        map.put("/boot/**","anon");
        map.put("/code/**","anon");
        map.put("/upload/**","anon");
        map.put("/admin/**","anon");
        map.put("/img/**","anon");
        map.put("/jqgrid/**","anon");
        map.put("/kindeditor/**","anon");
        map.put("/login/**","anon");
        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        shiroFilterFactoryBean.setLoginUrl("/login/login.jsp");
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        return shiroFilterFactoryBean;
    }
    @Bean
    public DefaultWebSecurityManager securityManager(MyRealmWeb myRealmWeb){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        MemoryConstrainedCacheManager cache = new MemoryConstrainedCacheManager();
        securityManager.setCacheManager(cache);
        securityManager.setRealm(myRealmWeb);
        return securityManager;
    }
    @Bean
    public MyRealmWeb myRealmWeb(){
        MyRealmWeb myRealmWeb = new MyRealmWeb();
        return myRealmWeb;
    }
}
