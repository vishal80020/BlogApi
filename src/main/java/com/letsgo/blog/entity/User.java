package com.letsgo.blog.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table( name = "users")
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int id;

    @Column( name = "name", nullable = false, length = 100)
    private String name;

    @Column (name = "email")
    private  String email;

    @Column(name = "password")
    private  String password;

    @Column(name = "about")
    private  String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)  //mappedBy mn jo user hai wahi Post mn hai
    private List<Post> posts = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
                joinColumns = @JoinColumn(name = "user",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role",referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> simpleGrantedAuthorityList =   this.roles.stream()
                                                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                                                .collect(Collectors.toList());

        return simpleGrantedAuthorityList;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
