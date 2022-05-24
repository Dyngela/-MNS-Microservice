package com.ne.authentification.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_Role", unique = true, nullable = false)
    private Long id;
    @Column(unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "User_has_roles",
            joinColumns = @JoinColumn( name = "id_Role" ),
            inverseJoinColumns = @JoinColumn( name = "id_User" ) )
    private List<User> users;
}
