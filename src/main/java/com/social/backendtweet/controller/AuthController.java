package com.social.backendtweet.controller;

import com.social.backendtweet.config.JWTProvider;
import com.social.backendtweet.exception.UserException;
import com.social.backendtweet.model.User;
import com.social.backendtweet.reposity.UserRepository;
import com.social.backendtweet.response.AuthResponse;
import com.social.backendtweet.service.Imp.CustomUserDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService customUserDetailsService;

    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JWTProvider jwtProvider,
                          AuthenticationManager authenticationManager,
                          CustomUserDetailService customUserDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();
        String username = user.getUsername();
        String birthDate = user.getBirthDate();

        User isEmailExist = userRepository.findByUsernameOrEmail(username, email);
        if (isEmailExist != null) {
            throw new UserException("Email or username already exists");
        }

        User createUser = new User();
        createUser.setEmail(email);
        createUser.setPassword(passwordEncoder.encode(password));
        createUser.setUsername(username);
        createUser.setBirthDate(birthDate);
        createUser.setVerification(null);

        User savedUser = userRepository.save(createUser);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Lấy quyền hạn (authorities) từ đối tượng Authentication
        String authorities = authentication.getAuthorities().toString();

        // Tạo token với email và quyền hạn
        String token = jwtProvider.generateToken(savedUser.getEmail(), authorities);

        AuthResponse authResponse = new AuthResponse(token, savedUser.getUsername(), savedUser.getEmail());

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody User user) throws UserException {
        String email = user.getEmail();
        String password = user.getPassword();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Retrieve the authenticated user details
        org.springframework.security.core.userdetails.User authenticatedUser =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        // Generate token with email and authorities
        String token = jwtProvider.generateToken(authenticatedUser.getUsername(), authenticatedUser.getAuthorities().toString());

        AuthResponse authResponse = new AuthResponse(token, authenticatedUser.getUsername(), email);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
