package com.hpicorp.core.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.hpicorp.core.common.MapExtension;
import com.hpicorp.core.common.OptionalExtension;
import com.hpicorp.core.entities.LineUser;
import com.hpicorp.core.enums.LineUserBindStatus;
import com.hpicorp.core.enums.LineUserStatus;
import com.hpicorp.core.exception.DaoException;
import com.hpicorp.core.repository.LineUserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserDao {
	
	@Autowired
	private LineUserRepository lineUserRepository;
	
	public Map<String, Object> getIdAndUidMapBy(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return null;
		}
		List<Map<String, Object>> listmap = this.lineUserRepository.findIdAndUidByIds(ids);
		return MapExtension.keyValueProcess(listmap);
	}
	
	public String getUidById(Long id) {
		return this.lineUserRepository.findUidById(id);
	}
	
	public List<String> getUidByIds(List<BigInteger> ids) {
		List<Long> userIds = new ArrayList<>(ids.size());
		for (BigInteger i : ids) {
			userIds.add(i.longValue());
		}
		return this.lineUserRepository.findUidByIdIn(userIds);
	}
	
	public List<String> getByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return new ArrayList<>();
		}
		return this.lineUserRepository.findUidByIdIn(ids);
	}
	
	public List<LineUser> getAllByIds(List<Long> ids) {
		if (ids == null || ids.isEmpty()) {
			return null;
		}
		return this.lineUserRepository.findByIdIn(ids);
	}
	public Long getIdByUid(String uuid) {
		Pageable pageable = PageRequest.of(0, 1);
		List<Long> result = this.lineUserRepository.findIdByUid(uuid, pageable);
		if (result == null || result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}
	
	public LineUser addUserByUidIfNotExist(String uid) {
		LineUser user = this.getByUid(uid);
		log.info("『 Keyword Flow 』UID => {}, 用戶 => {}", uid, user);
		if (user == null) {
			user = new LineUser();
			user.setLineUid(uid);
			user.setLinked(LineUserBindStatus.UNBINDED.getValues());
			user.setStatus(LineUserStatus.NORMALLY.getValue());
			user.setCreateTime(new Date());
			user = this.lineUserRepository.save(user);
			log.info("『 Keyword Flow 』保存用戶，UID => {}, 用戶 => {}", uid, user);
		}
		return user;
	}
	
	public void addUserByUid(String uid) {
		try {
			this.setLineUserByUid(uid, LineUserStatus.NORMALLY);
		} catch (Exception e) {
			log.warn("Add user error {}", e.getMessage());
		}
	}
	
	public void setLineUserByUid(String uid, LineUserStatus status) {
		LineUser user = this.getByUid(uid);
		if (user == null) {
			user = new LineUser();
			user.setLineUid(uid);
			user.setStatus(status.getValue());
			this.lineUserRepository.save(user);
			return;
		}
		user.setStatus(status.getValue());
		this.lineUserRepository.save(user);
	}
	
	public LineUser getById(Long id) throws DaoException {
		LineUser entity= OptionalExtension.get(this.lineUserRepository.findById(id));
		if (entity == null) {
			throw DaoException.message(String.format("Can't find User by id [ %d ]", id));
		}
		return entity;
	}
	
	public LineUser getByUid(String uid) {
		Optional<LineUser> lineUserOptional = this.lineUserRepository.findByLineUid(uid);
		if (lineUserOptional.isPresent()) {
			return lineUserOptional.get();
		}
		return null;
	}
	
	public void setUserStatusByUid(String uid, LineUserStatus status) {
		this.setLineUserByUid(uid, status);
	}
	
	public String getStatusByUid(String uid) {
		return this.lineUserRepository.findStatusByUid(uid);
	}
	
}
