package com.hpicorp.core.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hpicorp.core.entities.RichMenu;

public interface RichMenuRepository extends JpaRepository<RichMenu, Long> {
	
	@Query(value = "select * from richmenu_new where level = '1' and start_date > now()", nativeQuery = true)
	public List<RichMenu> findRichMenuOnSchedule();
	
	@Query(value = "select * from richmenu_new where group_id = :groupId", nativeQuery = true)
	public List<RichMenu> findOldRichMenu(@Param("groupId") Long groupId);
	
	// 因為圖文選單 Default 在生效後只能保存一個，必須將舊的查詢出來
	public List<RichMenu> findByGroupIdAndStartDateLessThan(Long groupId, Date now);
	
	@Query(value = "select * from richmenu_new where level = '1' ", nativeQuery = true)
	public List<RichMenu> findByLevel();

	public Optional<RichMenu> findByGroupId(Long string);
	
	public List<RichMenu> findByChatBarText(String chatBarText);

}
