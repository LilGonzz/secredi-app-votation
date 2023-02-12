package com.LilGonzz.sicredappvotation.repositories;

import com.LilGonzz.sicredappvotation.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    List<Vote> findByAssociateIdAndSessionId(Integer associateId, Integer sessionId);
    List<Vote> findByAssociateId(Integer associateId);
    List<Vote> findBySessionId(Integer sessionId);
}
