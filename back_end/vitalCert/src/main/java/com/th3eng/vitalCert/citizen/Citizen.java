package com.th3eng.vitalCert.citizen;

import com.th3eng.vitalCert.utils.Role;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table
@Builder
public class Citizen implements UserDetails {

    @Id
    @SequenceGenerator(
            name = "citizen_sequence",
            sequenceName = "citizen_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "citizen_sequence"
    )
    private Long id;
    private String password;
    private String ssn;
    private String firstName;
    private String lastName;

    public Citizen() {

    }

    public Citizen(Long id, String password, String ssn, String firstName, String lastName, Role role) {
        this.id = id;
        this.password = password;
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Citizen(String password, String ssn, String firstName, String lastName, Role role) {
        this.password = password;
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return ssn;
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
