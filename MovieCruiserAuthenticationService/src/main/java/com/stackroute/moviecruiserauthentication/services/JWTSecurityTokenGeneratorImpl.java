package com.stackroute.moviecruiserauthentication.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.moviecruiserauthentication.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTSecurityTokenGeneratorImpl implements SecurityTokenGenerator {



	@Override
	public Map<String, String> generateToken(User user) {
		String jwtToken="";
		jwtToken =Jwts.builder().setSubject(user.getUserId()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		Map<String, String> tokenMap=new HashMap<String, String>();
		tokenMap.put("token",jwtToken);
		tokenMap.put("message", "User sucessfully logged in");
		return tokenMap;
	}

}
