package com.allasassis.rabbitmqapp.repository;

import com.allasassis.rabbitmqapp.entity.Proposal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProposalRepository extends CrudRepository<Proposal, Long> {

    List<Proposal> findAllByIntegratedIsFalse();
}
