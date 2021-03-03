package com.example.accounts.controller;

import com.example.accounts.dto.AdminAuthenticationRequest;
import com.example.accounts.dto.AdminAuthenticationResponse;
import com.example.accounts.dto.AdminRequestDto;
import com.example.accounts.dto.AdminResponseDto;
import com.example.accounts.service.AdminService;
import com.example.accounts.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AdminService adminService;

    @PostMapping("/login-as-admin")
    public AdminAuthenticationResponse generateToken(@RequestBody AdminAuthenticationRequest adminAuthenticationRequest) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(adminAuthenticationRequest.getUsername(),adminAuthenticationRequest.getPassword()));
        } catch (BadCredentialsException ex){
            System.out.println("LoggedInAsAdmin");
            throw new BadCredentialsException("Invalid Credentials");
        }
        AdminAuthenticationResponse adminAuthenticationResponse = new AdminAuthenticationResponse();
        adminAuthenticationResponse.setJwt(jwtUtil.generateToken(adminAuthenticationRequest.getUsername()));
        return adminAuthenticationResponse;
    }

    @PostMapping("/register-as-admin")
    public AdminResponseDto adminResponseDto (@RequestBody AdminRequestDto requestDto){
        return adminService.insertDataIntoAdmin(requestDto);
    }
}