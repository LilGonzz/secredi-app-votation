package com.LilGonzz.sicredappvotation.repositories;

import com.LilGonzz.sicredappvotation.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Integer> {
    Optional<Associate> findByIdAndIsActive(Integer id, Boolean active);
}
