package vn.bkdn.cntt.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {

    private final int id;
    private final String username;
    private String password;
    private final String fullname;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(int id,
          String username,
          String fullname,
          Collection<? extends GrantedAuthority> authorities
    ) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.authorities = authorities;
    }
    
    public int getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public String getFullname() {
        return fullname;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
