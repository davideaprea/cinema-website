package com.epicode.Spring.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epicode.Spring.security.entity.ERole;
import com.epicode.Spring.security.entity.Role;
import com.epicode.Spring.security.entity.User;
import com.epicode.Spring.security.exception.MyAPIException;
import com.epicode.Spring.security.payload.LoginDto;
import com.epicode.Spring.security.payload.RegisterDto;
import com.epicode.Spring.security.payload.RegisterResponse;
import com.epicode.Spring.security.repository.RoleRepository;
import com.epicode.Spring.security.repository.UserRepository;
import com.epicode.Spring.security.security.JwtTokenProvider;

import jakarta.persistence.EntityNotFoundException;



@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;
    @Autowired private EmailService emailService;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    public User getUserByEmailOrPassword(String credential) {
    	if(!userRepository.existsByEmail(credential) && userRepository.existsByUsername(credential))
    		throw new EntityNotFoundException("Couldn't find the user.");
    	return userRepository.findByUsernameOrEmail(credential, credential).get();
    }
    
    @Override
    public String login(LoginDto loginDto) {
        
    	Authentication authentication = authenticationManager.authenticate(
        		new UsernamePasswordAuthenticationToken(
        				loginDto.getUsername(), loginDto.getPassword()
        		)
        );
    	
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public RegisterResponse register(RegisterDto registerDto) {

        // add check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new MyAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        
        if(registerDto.getRoles() != null) {
	        registerDto.getRoles().forEach(role -> {
	        	Role userRole = roleRepository.findByRoleName(getRole(role)).get();
	        	roles.add(userRole);
	        });
        } else {
        	Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER).get();
        	roles.add(userRole);
        }
        
        user.setRoles(roles);
        System.out.println(user);
        userRepository.save(user);
        
        String userEmail=registerDto.getEmail();
        String emailVerificationToken=jwtTokenProvider.generateEmailVerificationToken(userEmail);
        emailService.sendEmailConfirmation(userEmail, emailVerificationToken);

        return new RegisterResponse(
				registerDto.getName(), 
				registerDto.getUsername(), 
				registerDto.getEmail(), 
				"User registered successfully!.");
    }
    
    public HttpStatus verifyEmailVerificationToken(String token) {
    	if(!jwtTokenProvider.validateToken(token))
    		throw new DataIntegrityViolationException("Verification token expired.");
    	
    	String email=jwtTokenProvider.getEmail(token);
    	User user=userRepository.findByEmail(email).get();
    	user.setVerified(true);
    	userRepository.save(user);
    	return HttpStatus.OK;
    }
    
    public ERole getRole(String role) {
    	if(role.equals("ADMIN")) return ERole.ROLE_ADMIN;
    	else if(role.equals("MODERATOR")) return ERole.ROLE_MODERATOR;
    	else return ERole.ROLE_USER;
    }
    
}
