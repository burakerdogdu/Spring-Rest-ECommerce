package com.works.restcontrollers;

import com.works.entities.Admin;
import com.works.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminRestController {
    final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody Admin admin){
        return adminService.save(admin);
    }
    @PostMapping("/login")
    public Admin login(@RequestBody Admin admin){
        return adminService.login(admin.getUsername(), admin.getPassword());
    }
}
