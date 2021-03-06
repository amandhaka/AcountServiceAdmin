package com.example.accounts.service.impl;

import com.example.accounts.dto.AdminRequestDto;
import com.example.accounts.dto.AdminResponseDto;
import com.example.accounts.entity.Admin;
import com.example.accounts.repository.AdminRepository;
import com.example.accounts.service.AdminService;
import com.example.accounts.util.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRespository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin=adminRespository.findByUsername(username);
        return new User(admin.getUsername(), admin.getPassword(), new ArrayList<>());
    }

    @Override
    public AdminResponseDto insertDataIntoAdmin(AdminRequestDto requestDto) throws Exception {
        Admin admin = new Admin();
        BeanUtils.copyProperties(requestDto,admin);
        String encodedPassword = encodePassword(requestDto.getPassword());
        admin.setPassword(encodedPassword);
        Admin savedAdmin = adminRespository.save(admin);
        AdminResponseDto adminResponseDto = new AdminResponseDto();
        BeanUtils.copyProperties(savedAdmin,adminResponseDto);
        return adminResponseDto;
    }

    private String encodePassword (String passwordToHash){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
