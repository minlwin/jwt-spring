package com.jdc.spring.jwt;

/**
 * <h3>JWT Setting</h3>
 * 
 *  <p>
 *  JwtSetting is one of the base API for JWT-Spring Library. It can define how to generate JWT Token.
 *  API has default Values for this interface. But if you want to customize this setting, you have to create 
 *  a bean to override default setting.
 *  </p>
 * 
 * @author minlwin
 * @since 2020/08/21
 *
 */
public interface JwtSettings {

	/**
	 * To Define Issuer for JWT Token
	 * 
	 * @return Issuer
	 */
	String issuer();
	
	/**
	 * To Define Token Name
	 * 
	 * @return Token Name
	 */
	String tokenName();
	
	/**
	 * To Define Expiration for generated Token
	 * 
	 * @return Life time or Token in Minutes
	 */
	int lifeTimeInMinutes();
}
