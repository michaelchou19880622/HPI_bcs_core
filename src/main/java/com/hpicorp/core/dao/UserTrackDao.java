package com.hpicorp.core.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hpicorp.core.entities.LineUserTrack;
import com.hpicorp.core.enums.LineUserBindStatus;
import com.hpicorp.core.enums.LineUserTrackSource;
import com.hpicorp.core.repository.LineUserTrackRepository;

@Component
public class UserTrackDao {

	private static final Logger logger = LoggerFactory.getLogger(UserTrackDao.class);

	@Autowired
	private LineUserTrackRepository lineUserTrackRepository;

	public List<Map<String, Object>> getByDate(Date from, Date to, String source) {
		List<String> sources = Arrays.asList(source);
		return this.getByDate(from, to, sources);
	}

	public List<Map<String, Object>> getByDate(Date from, Date to, List<String> sources) {
		return this.lineUserTrackRepository.countBySourceAndCreateTimeBetween(from, to, sources);
	}

	public Integer getCountBySourceAndDate(LineUserTrackSource source, Date from, Date to) {
		return this.lineUserTrackRepository.countBySourceAndCreateTimeBetween(source.toString(), from, to);
	}

	public void add(Long userId, LineUserBindStatus status) {
		try {
			LineUserTrackSource source = LineUserTrackSource.fromString(status.toString());
			this.add(userId, source);
		} catch (Exception e) {
			logger.error("track Error", e);
		}
	}

	public void add(Long userId, LineUserTrackSource source) {
		LineUserTrack entity = new LineUserTrack();
		entity.setUserId(userId);
		entity.setSource(source.toString());
		this.lineUserTrackRepository.save(entity);
	}

}
