package ru.kata.spring.boot_security.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String role;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

    public Role() {}

    public Role(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role.replaceAll("ROLE_", "") +"; ";
    }

    @Override
    public String getAuthority() {
        return getRole();
    }

    /*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return role.equals(role1.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role);
    }*/
}
