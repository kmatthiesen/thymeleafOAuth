package catalyst.thymeleaf.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;

/**
 * Configures Spring Security to authenticate users with LDAP, the areas of the application users can access,
 * and how to handle login/logout.
 * 
 * @author Kevin Matthiesen
 */
@Configuration
@EnableWebSecurity
public class OAuthSecuity extends WebSecurityConfigurerAdapter {
		
	/**
	 * Configures Spring Security to authenticate users using LDAP.
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
						
		auth.authenticationProvider(activeDirectoryLdapAuthenticationProvider());
	}

	/**
	 * Configures the areas of the application users can go to and how to handle login/logout.
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login").permitAll().failureUrl("/login-fail").permitAll()
			.and()
				.logout().logoutUrl("/logout").logoutSuccessHandler(successHandler()).logoutSuccessUrl("/login");		
	}
	
	/**
	 * Configures Spring Security to allow access to the static files regardless of if they are logged in.
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/webjars/**", "/css/**", "/js/**", "/images/**");
	}
	
	/**
	 * Creates a new ActiveDirectoryLdapAuthenticationProvider which configures the connection to the LDAP server 
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
	
	/**
	 * Creates a CustomLogoutSuccessHandler to handle logic when a user logs out successfully.
	 * 
	 * @return the new instance of the CustomLogoutSuccessHandler.
	 */
	@Bean
	public CustomLogoutSuccessHandler successHandler() {
				
		return new CustomLogoutSuccessHandler();
	}
}
