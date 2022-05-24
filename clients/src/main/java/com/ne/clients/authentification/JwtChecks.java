package com.ne.clients.authentification;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class JwtChecks {
    private String token;
    private String refreshToken;
}
