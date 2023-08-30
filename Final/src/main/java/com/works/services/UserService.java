package com.works.services;

import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;
import com.works.configs.Rest;
import com.works.entities.Admin;
import com.works.entities.Role;
import com.works.entities.User;
import com.works.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class UserService implements UserDetailsService {
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public ResponseEntity list(){
        List<User> ls = userRepository.findAll();
        Rest rest = new Rest(true,ls);
        return new ResponseEntity(rest,HttpStatus.OK);
    }

    public ResponseEntity save(User user){
        boolean status = user.getRoles() == null;
        if (status){
            Role role = new Role();
            role.setRid(2l);
            role.setName("ROLE_user");
            List<Role> ls = new ArrayList<>();
            ls.add(role);
            user.setRoles(ls);
        }
        try{
            Optional<User> optionalUser = userRepository.findByEmailEquals(user.getEmail());
            if (optionalUser.isPresent()){
                Rest rest = new Rest(false,user);
                return new ResponseEntity(rest, HttpStatus.BAD_REQUEST);
            }else{
                String newPassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(newPassword);
                userRepository.save(user);
                Rest rest = new Rest(true,user);
                return new ResponseEntity(rest, HttpStatus.OK);
            }

        }catch (Exception exception){
            Rest rest = new Rest(false,exception.getMessage());
            return new ResponseEntity(rest, HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity update(User user){
        try {
            Optional<User> optionalUser = userRepository.findByEmailEquals(user.getEmail());
            if (optionalUser.isPresent()){
                userRepository.saveAndFlush(user);
                Rest rest = new Rest(true,user);
                return new ResponseEntity(rest, HttpStatus.OK);
            }else {
                Rest rest = new Rest(false,"girilen kullanıcı yok!");
                return new ResponseEntity(rest, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            Rest rest = new Rest(false,user);
            return new ResponseEntity(rest, HttpStatus.BAD_REQUEST);
        }

    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmailEquals(username);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),parseRole(user.getRoles()));
        }
        throw new UsernameNotFoundException("Kulanıcı Yok!");
    }

    private Collection<? extends GrantedAuthority> parseRole(List<Role> roles) {
        List<GrantedAuthority> ls = new ArrayList<>();
        for (Role item : roles){
            ls.add(new SimpleGrantedAuthority(item.getName()));
        }
        return ls;
    }

    public User login(String email, String password){

        Optional<User> result = userRepository.findByEmailEquals(email);
        String encodepassword = result.get().getPassword();
        boolean match = passwordEncoder.matches(password,encodepassword);
        if (result.isPresent() && match){
            return result.get();
        }
        return null;
    }
}
