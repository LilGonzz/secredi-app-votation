package com.LilGonzz.sicredappvotation.repositories;

import com.LilGonzz.sicredappvotation.model.SessionVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionVoteRepository extends JpaRepository<SessionVote, Integer> {
}
