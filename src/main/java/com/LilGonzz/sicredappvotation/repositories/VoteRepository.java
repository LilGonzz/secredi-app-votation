package com.LilGonzz.sicredappvotation.repositories;

import com.LilGonzz.sicredappvotation.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
}
