package com.moonlit.kingserp.admin.common.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shiro 配置
 *
 * @author Joshua
 */
@Configuration
public class ShiroConfig {

    /**
     * 一小时*24
     */
    public static Integer cacheExpire = 36000 * 24;

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;

    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     * <p>
     * Filter Chain定义说明
     * 1、一个URL可以配置多个Filter，使用逗号分隔
     * 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        // 设置 SecurityManager
        filterFactoryBean.setSecurityManager(securityManager);

        //过滤器
        Map<String, String> map = new LinkedHashMap<>();

        /**
         * anon:所有url都都可以匿名访问
         * authc: 需要认证才能进行访问
         * user:配置记住我或认证通过可以访问
         */
        map.put("/", "anon");
        //swagger开发拦截start
        map.put("/swagger-ui.html/**", "anon");
        map.put("/webjars/**", "anon");
        map.put("/v2/**", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/**/sysUser/login", "anon");
        //swagger开发拦截end
        map.put("/**", "authc");

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        filterFactoryBean.setLoginUrl("/**/sysUser/login");
        filterFactoryBean.setFilterChainDefinitionMap(map);

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new LoginFilter());
        filterFactoryBean.setFilters(filterMap);
        return filterFactoryBean;
    }

    /**
     * 使用自定義Realm
     *
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * SecurityManager容器
     * 加載Realm，並交由Shiro完成驗證
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setCacheManager(redisCacheManager());
//        securityManager.setSessionManager(mySessionManager());
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    /**
     * 散列加密
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new RetryLimitCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }

    /**
     * Redis緩存管理
     *
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager cacheManager = new RedisCacheManager();
        cacheManager.setRedisManager(redisManager());
        return cacheManager;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPassword(password);
        redisManager.setTimeout(3000);
        //redis数据存储db位置
        redisManager.setDatabase(0);
        return redisManager;
    }

    @Bean
    public MySessionManager mySessionManager() {
        MySessionManager sessionManager = new MySessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * StringRedisTemplate
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setExpire(cacheExpire);
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * Shiro生命周期处理器
     *
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor(Ordered.LOWEST_PRECEDENCE);
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     **/
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * 开启 权限注解
     * Controller才能使用@RequiresPermissions
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
