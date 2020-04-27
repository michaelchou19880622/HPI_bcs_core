package com.hpicorp.core.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hpicorp.core.entities.RichMenu;

public interface RichMenuRepository extends JpaRepository<RichMenu, Long> {
	
	@Query(value = "select * from richmenu where level = '1' and start_date > now()", nativeQuery = true)
	public List<RichMenu> findRichMenuOnSchedule();
	
	@Query(value = "select * from richmenu where group_id = :groupId", nativeQuery = true)
	public List<RichMenu> findOldRichMenu(@Param("groupId") Long groupId);
	
	// 因為圖文選單 Default 在生效後只能保存一個，必須將舊的查詢出來
	public List<RichMenu> findByGroupIdAndStartDateLessThan(Long groupId, Date now);
	
	@Query(value = "select * from richmenu where level = '1' ", nativeQuery = true)
	public List<Object[]> findByLevel();
	
	@Query(value = "SELECT "
				+ "rchmnu.[name] as name, "
				+ "rchmnu.[id] as id, "
				+ "rchmnu.[chat_bar_text] as chatBarText, "
				+ "rchmnu.[create_time] as createTime, "
				+ "rchmnu.[custom_id] as customId, "
				+ "rchmnu.[group_id] as groupId, "
				+ "bsg.[GROUP_TITLE] AS groupTitle, "
				+ "rchmnu.[image] as image, "
				+ "rchmnu.[key_id] as keyId, "
				+ "rchmnu.[level] as level, "
				+ "rchmnu.[modify_time] as modifyTime, "
				+ "rchmnu.[richmenu_id] as richmenuId, "
				+ "rchmnu.[selected] as selected, "
				+ "rchmnu.[start_date] as startDate, "
				+ "rchmnu.[start_time] as startTime, "
				+ "rchmnuSize.[height] as height, "
				+ "rchmnuSize.[width] as width "
			+ "FROM "
				+ "richmenu rchmnu "
			+ "LEFT JOIN "
				+ "BCS_SEND_GROUP bsg ON bsg.[GROUP_ID] = rchmnu.[group_id] "
			+ "LEFT JOIN "
				+ "richmenu_size rchmnuSize ON rchmnuSize.[richmenu_id] = rchmnu.[id] "
			+ "WHERE "
				+ "rchmnu.[level] = '1'", nativeQuery = true)
	public List<Map<String, Object>> findRichmenuListByLevel();

	public Optional<RichMenu> findByGroupId(Long string);
	
	public List<RichMenu> findByChatBarText(String chatBarText);

}
