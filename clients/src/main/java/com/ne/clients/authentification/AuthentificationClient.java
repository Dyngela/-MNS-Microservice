package com.ne.clients.authentification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "authentification")
public interface AuthentificationClient {

    @PostMapping(path = "api/authentification/validate")
    ResponseEntity<Boolean> validateToken(@RequestBody JwtChecks token);
}
