package com.ems.project.controller;

import com.ems.project.dto.ReqRes;
import com.ems.project.entity.User;
import com.ems.project.service.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final UserManagementService usersManagementService;

    public UserController(UserManagementService usersManagementService) {
        this.usersManagementService = usersManagementService;
    }



    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> regeister(@RequestBody ReqRes reg){
        return ResponseEntity.ok(usersManagementService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }


    @GetMapping("/admin/get-all-users")
    public ResponseEntity<Map<String, List<User>>> getAllUsers(){ // Return a Map
        List<User> userList = usersManagementService.getAllUsers(); // Calls the service method
        Map<String, List<User>> response = new HashMap<>(); // Creates a Map
        response.put("ourUsersList", userList); // Puts the list in the map
        return ResponseEntity.ok(response); // Returns the Map // Return the Map
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Long id, @RequestBody User user){
        return ResponseEntity.ok(usersManagementService.updateUser(id, user));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Long id){
        return ResponseEntity.ok(usersManagementService.deleteUser(id));
    }

}
