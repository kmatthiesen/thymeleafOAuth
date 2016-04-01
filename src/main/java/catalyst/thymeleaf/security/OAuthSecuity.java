package catalyst.thymeleaf.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class OAuthSecuity extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
						
		auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/webjars/**", "/images/**", "/css/**", "/js/**", "/logout").permitAll()
			.and()
				.authorizeRequests().anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login").permitAll().failureUrl("/login-fail").permitAll()
			.and()
				.logout().logoutUrl("/logout").permitAll().logoutSuccessHandler(successHandler());
//				
	}			
	
	/**
	 * Creates a new ActiveDirectoryLdapAuthenticationProvider which configures the connection to the ldap server 
	 * based on the parameters pass upon creation.
	 * 
	 * @return The ActiveDirectoryLdapAuthenticationProvider that was created.
	 */
	@Bean
	public ActiveDirectoryLdapAuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
		
	    ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider("catalystsolves.com", "ldap://catalystsolves.com:389/");
	    provider.setConvertSubErrorCodesToExceptions(true);

	    return provider;
	}
	
	@Bean
	public CustomLogoutSuccessHandler successHandler() {
		
		return new CustomLogoutSuccessHandler();
	}
}
