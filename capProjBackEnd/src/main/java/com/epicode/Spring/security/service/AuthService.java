package com.epicode.Spring.security.service;

import com.epicode.Spring.security.payload.LoginDto;
import com.epicode.Spring.security.payload.RegisterDto;
import com.epicode.Spring.security.payload.RegisterResponse;

public interface AuthService {
    
	String login(LoginDto loginDto);
    RegisterResponse register(RegisterDto registerDto);
    
}
