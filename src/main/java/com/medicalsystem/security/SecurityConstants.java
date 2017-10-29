package com.medicalsystem.security;

import io.jsonwebtoken.SignatureAlgorithm;

public class SecurityConstants {
    public static final String SECRET = "SuperSecretString";
    public static final long EXPIRATION_TIME = 10 * 24 * 60 * 60 * 1000;
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String REGISTER_URL = "/api/auth/register";
    public static final String LOGIN_URL = "/api/auth/login";
}