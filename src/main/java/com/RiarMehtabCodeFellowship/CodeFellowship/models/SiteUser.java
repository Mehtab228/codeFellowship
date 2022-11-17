package com.RiarMehtabCodeFellowship.CodeFellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class SiteUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String username;
    private String password;
    private String firstName;

    @OneToMany(mappedBy = "siteUser")
    List<Post> postList;

    @ManyToMany
    @JoinTable(
            name = "followers_to_followees",
            joinColumns = {@JoinColumn(name = "userWhoIsFollowing")},
            inverseJoinColumns = {@JoinColumn(name = "FollowedUser")}
    )
    Set<SiteUser> usersIFollow = new HashSet<>();

    @ManyToMany(mappedBy = "usersIFollow")
    Set<SiteUser> usersWhoFollowMe = new HashSet<>();


    public SiteUser(String username, String password, String firstName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
    }

    protected SiteUser() {
    }

    public Long getId() {
        return id;
    }

    public Set<SiteUser> getUsersIFollow() {
        return usersIFollow;
    }

    public Set<SiteUser> getUsersWhoFollowMe() {
        return usersWhoFollowMe;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public List<Post> getPostList() {
        return postList;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
