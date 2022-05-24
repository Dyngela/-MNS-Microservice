package com.ne.authentification.controllers;

import com.ne.authentification.models.Role;
import com.ne.authentification.models.User;
import com.ne.authentification.request.AddRoleToUserRequest;
import com.ne.clients.authentification.JwtChecks;
import com.ne.authentification.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/authentification")
public class UserResource {


    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addRoleToUser")
    public ResponseEntity<Role> saveRole(@RequestBody AddRoleToUserRequest request) {
        userService.addRoleToUser(request.getUsername(), request.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getRoles")
    public ResponseEntity<String> getRoles(@RequestHeader (name="Authorization") String token) {
        String formatedToken = token.substring("Bearer ".length());
        return ResponseEntity.ok().body(formatedToken);
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestBody JwtChecks checks) {
        return ResponseEntity.ok(userService.getRoleAccordingToJWT(checks));
    }

}
