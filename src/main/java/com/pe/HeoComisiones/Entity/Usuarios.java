package com.pe.HeoComisiones.Entity;

import com.pe.HeoComisiones.Auditable.Auditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
public class Usuarios extends Auditable implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String username;
    private String dni;
    private String email;
    private String Password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="usuario_perfiles",
            joinColumns = @JoinColumn(name="usuario_id"),
            inverseJoinColumns = @JoinColumn(name="perfiles_id")
    )
    private Set<Perfiles> profiles;
    @ManyToOne
    private Sucursales sucursales;
    private boolean status;
    public Usuarios() {
        profiles = new HashSet<>();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return profiles;
    }

    @Override
    public String getUsername() {
        return username;
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
