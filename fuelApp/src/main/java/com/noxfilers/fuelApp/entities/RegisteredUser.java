package com.noxfilers.fuelApp.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "registered_user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisteredUser implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "display_name", length = 35, nullable = false)
    private String displayName;


    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "preferred_bunk_type")
    private BunkProvider preferredBunkType;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "mobile",length = 15 , nullable = false,unique = true)
    private String mobile;

    @Column(name = "title",length = 3)
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "common_details", referencedColumnName = "id")
    private CommonDetails commonDetails;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return this.mobile;
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
