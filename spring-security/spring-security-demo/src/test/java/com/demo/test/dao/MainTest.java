package com.demo.test.dao;

import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.jaasapi.JaasApiIntegrationFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

public class MainTest {
	
	Authentication authentication;
	SecurityContextHolder securityContextHolder;
	SecurityContext securityContext;
	
	AuthenticationManager authenticationManager;
	AuthenticationProvider authenticationProvider;
	
	ProviderManager providerManager;
	DaoAuthenticationProvider daoAuthenticationProvider;
	UserDetailsService userDetailsService;
	JdbcDaoImpl jdbcDaoImpl;

	GrantedAuthority grantedAuthority;
	
	ExceptionTranslationFilter exceptionTranslationFilter;
	AbstractSecurityInterceptor abstractSecurityInterceptor;
	
	ChannelProcessingFilter channelProcessingFilter;
	SecurityContextPersistenceFilter securityContextPersistenceFilter;
	ConcurrentSessionFilter concurrentSessionFilter;
	
	UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter;
	CasAuthenticationFilter casAuthenticationFilter;
	SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter;
	JaasApiIntegrationFilter jaasApiIntegrationFilter;
	RememberMeAuthenticationFilter rememberMeAuthenticationFilter;
	AnonymousAuthenticationFilter anonymousAuthenticationFilter;
	FilterSecurityInterceptor filterSecurityInterceptor;
	
	DelegatingFilterProxy delegatingFilterProxy;
	
	FilterChainProxy filterChainProxy;
	
	org.springframework.security.taglibs.authz.AuthenticationTag tag;
	
	//org.apache.catalina.session.StandardManager manager;
	
	//SpringSecurityFilterChain ads;
	
	public void test(){
		
	}
}
