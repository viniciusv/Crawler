package com.crawler.api.security;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	static Logger logger = Logger.getLogger(JWTUtil.class);
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public String generateToken(String userName) {
		return Jwts.builder()
				.setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + this.expiration))
				.signWith(SignatureAlgorithm.HS512, this.secret.getBytes())
				.compact();
	}

	public boolean tokenValido(String token) {
		Claims claims = this.getClaims(token);
		if(claims != null) {
			String userName = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if(userName != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}

	public String getUserName(String token) {
		Claims claims = this.getClaims(token);
		if(claims != null) {
			return claims.getSubject();
		}
		return null;
	}
	
		
	private Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(this.secret.getBytes()).parseClaimsJws(token).getBody();	
		}catch (Exception e) {
			return null;
		}		
	}
	
}
