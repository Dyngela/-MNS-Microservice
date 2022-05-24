package com.ne.authentification.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_User", unique = true, nullable = false)
    private Long id;
    private String name;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "User_has_roles",
            joinColumns = @JoinColumn( name = "id_User" ),
            inverseJoinColumns = @JoinColumn( name = "id_Role" ) )
    private List<Role> roles;
}
