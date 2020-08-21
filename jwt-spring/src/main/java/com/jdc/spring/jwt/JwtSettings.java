package com.jdc.spring.jwt;

public interface JwtSettings {

	String issuer();
	String tokenName();
	int lifeTimeInMinutes();
}
