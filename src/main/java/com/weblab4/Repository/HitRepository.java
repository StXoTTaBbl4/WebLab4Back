package com.weblab4.Repository;

import com.weblab4.Model.HitEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HitRepository extends JpaRepository<HitEntry, Integer> {
    List<HitEntry> findByLogin(String login);

    void deleteHitEntriesByLogin(String login);
}
