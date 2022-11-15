package com.RiarMehtabCodeFellowship.CodeFellowship.repository;

import com.RiarMehtabCodeFellowship.CodeFellowship.models.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteUserRepository extends JpaRepository<SiteUser, Long> {
    public SiteUser findByUsername(String username);
}
