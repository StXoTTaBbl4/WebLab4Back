package com.weblab4.Repository;

import com.weblab4.Model.AuthEntry;
import com.weblab4.Model.HitEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthRepository extends JpaRepository<AuthEntry,String> {
    AuthEntry findByLogin(String login);

}
