package com.LilGonzz.sicredappvotation.repositories;

import com.LilGonzz.sicredappvotation.model.SessionVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionVoteRepository extends JpaRepository<SessionVote, Integer> {

    SessionVote findByIdAndIsActive(Integer id, Boolean isActive);
    List<SessionVote> findByIsActive(boolean value);
}
