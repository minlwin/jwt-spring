package com.jdc.spring.jwt.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import com.jdc.spring.jwt.JwtSettings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtTokenProvider {

	private JwtSettings settings;
	
	private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public JwtTokenProvider(@NonNull JwtSettings settings) {
		super();
		this.settings = settings;
	}

	public Authentication parse(HttpServletRequest request) {
		
		String token = request.getHeader(tokenName());
		
		if(null != token) {
			JwtParser parser = Jwts.parserBuilder()
					.requireIssuer(settings.issuer())						
					.setSigningKey(key)
					.build();
			
			Jws<Claims> jws = parser.parseClaimsJws(token);
			
			String userName = jws.getBody().getSubject();
			@SuppressWarnings("unchecked")
			List<String> roles =  (List<String>) jws.getBody().get("rol");
			
			return new UsernamePasswordAuthenticationToken(userName, null, AuthorityUtils.createAuthorityList(roles.toArray(new String[roles.size()])));
			
		}
		
		return null;
	}

	public String generate(Authentication auth) {
		
		// Expiration
		Calendar expiration = Calendar.getInstance();
		expiration.add(Calendar.MINUTE, settings.lifeTimeInMinutes());
		
		// Roles
		Object [] authorities = auth.getAuthorities().stream()
				.map(a -> a.getAuthority())
				.collect(Collectors.toList()).toArray();
		
		return Jwts.builder()
				.setIssuer(settings.issuer())
				.setIssuedAt(new Date())
				.setExpiration(expiration.getTime())
				.setSubject(auth.getName())
				.claim("rol", authorities)
				.signWith(key)
				.compact();
	}
	
	public JwtSettings getSettings() {
		return settings;
	}
	
	public String tokenName() {
		return settings.tokenName();
	}

}
