package com.allasassis.rabbitmqapp.repository;

import com.allasassis.rabbitmqapp.entity.Proposal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProposalRepository extends CrudRepository<Proposal, Long> {

    List<Proposal> findAllByIntegratedIsFalse();

    @Transactional
    @Modifying
    @Query(value = "UPDATE proposals SET approved = :approved, observation = :observation WHERE id = :id", nativeQuery = true)
    void updateProposal(Long id, boolean approved, String observation);
}
