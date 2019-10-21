package com.hpicorp.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hpicorp.core.entities.LinkAddressList;

@Repository
public interface LinkAddressListRepository extends JpaRepository<LinkAddressList, Long> {

	@Modifying
	@Transactional
	@Query(value = "delete from linkaddresslist where link_id = :linkId ", nativeQuery = true)
	public void deleteByLinkId(@Param("linkId") long linkId);

}
