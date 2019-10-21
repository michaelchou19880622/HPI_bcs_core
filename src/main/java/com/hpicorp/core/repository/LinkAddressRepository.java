package com.hpicorp.core.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.hpicorp.core.entities.LinkAddress;

@Repository
public interface LinkAddressRepository extends PagingAndSortingRepository<LinkAddress, Long> {

}
