package com.works.services;

import com.works.configs.Rest;
import com.works.entities.Admin;
import com.works.entities.Role;
import com.works.repositories.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService implements UserDetailsService {
    final AdminRepository adminRepository;
    final PasswordEncoder passwordEncoder;
    public Admin login(String username, String password){

       Optional<Admin> result = adminRepository.findByUsernameEquals(username);
       String encodepassword = result.get().getPassword();
       boolean match = passwordEncoder.matches(password,encodepassword);
       if (result.isPresent() && match){
           return result.get();
       }
       return null;
    }
    public ResponseEntity save(Admin admin){
        try{
            Optional<Admin> optionalAdmin = adminRepository.findByEmailEqualsAndUsernameEquals(admin.getEmail(), admin.getUsername());
            if (optionalAdmin.isPresent()){
                Rest rest = new Rest(false,admin);
                return new ResponseEntity(rest, HttpStatus.BAD_REQUEST);
            }else{
                String newPassword = passwordEncoder.encode(admin.getPassword());
                admin.setPassword(newPassword);
                adminRepository.save(admin);
                Rest rest = new Rest(true,admin);
                return new ResponseEntity(rest, HttpStatus.OK);
            }

        }catch (Exception exception){
            Rest rest = new Rest(false,exception.getMessage());
            return new ResponseEntity(rest, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> optionalAdmin = adminRepository.findByUsernameEquals(username);
        if (optionalAdmin.isPresent()){
            Admin admin = optionalAdmin.get();
            return new User(admin.getUsername(),admin.getPassword(),parseRole(admin.getRoles()));
        }
        throw new UsernameNotFoundException("Yönetici Yok!");
    }
    private Collection<? extends GrantedAuthority> parseRole(List<Role> roles) {
        List<GrantedAuthority> ls = new ArrayList<>();
        for (Role item : roles){
            ls.add(new SimpleGrantedAuthority(item.getName()));
        }
        return ls;
    }

}
