package com.hpicorp.core.dao;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hpicorp.core.common.DateTimeModel;
import com.hpicorp.core.entities.LineUser;
import com.hpicorp.core.entities.SendMessage;
import com.hpicorp.core.entities.SendMessageList;
import com.hpicorp.core.entities.SendMessageUsers;
import com.hpicorp.core.entities.SendMessageUsersPushJob;
import com.hpicorp.core.enums.SendMessageStatus;
import com.hpicorp.core.exception.DaoException;
import com.hpicorp.core.repository.LineUserRepository;
import com.hpicorp.core.repository.SendMessageListRepository;
import com.hpicorp.core.repository.SendMessageRepository;
import com.hpicorp.core.repository.SendMessageUsersPushJobRepository;
import com.hpicorp.core.repository.SendMessageUsersRepository;
import com.linecorp.bot.model.message.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SendMsgDao {
	
	@Autowired
	private SendMessageUsersPushJobRepository pushJobRepository;
	
	@Autowired
	private SendMessageUsersRepository sendMessageUsersRepository;
	
	@Autowired
	private SendMessageListRepository sendMessageListRepository;
	
	@Autowired
	private SendMessageRepository sendMessageRepository;
	
	@Autowired
	private LineUserRepository lineUserRepository;
	
	@Autowired
	private MsgDao msgDao;
	
	public <S extends SendMessage> S save(S object) {
		return this.sendMessageRepository.save(object);
	}

	public void rewriteSendMessageStatusById(long id, SendMessageStatus status) {
		this.sendMessageRepository.setStatusById(id, status.getValue());
	
	}
	
	public SendMessage getSendMessageById(Long id, SendMessageStatus status) {
		return this.sendMessageRepository.findOneByIdAndStatus(id, status.getValue());
	}
	
	public List<SendMessage> getSendListByStatus(SendMessageStatus status) {
		return this.sendMessageRepository.findAllByStatus(status.getValue());
	}
	
	public SendMessage getSendMessageById(Long id) {
		return this.sendMessageRepository.findOneById(id);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void setSendUsersStatusAndResponseCodeByIds(List<Long> ids, SendMessageStatus status, Integer responseCode) {
		ZonedDateTime time = DateTimeModel.getTime();
		this.sendMessageUsersRepository.setStatusAndResponseCodeByIds(ids, status.getValue(), responseCode, time);
	}
	public List<SendMessageUsers> getSendUsersBySendId(long id, Pageable pageable) {
		Page<SendMessageUsers> pages = this.sendMessageUsersRepository.findBySendId(id, pageable);
		return pages.getContent();
	}
	
	public List<Long> getSendUserIdBySendId(Long id, Pageable pageable) {
		return this.sendMessageUsersRepository.findUserIdBySendId(id, pageable);
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, timeout=10, isolation=Isolation.READ_COMMITTED)
	public List<SendMessageUsers> getUnSendUserId(Long id, Integer size) {
		SendMessageUsersPushJob job = this.pushJobRepository.getBySendId(id);
		if (job == null) {
			job = new SendMessageUsersPushJob(null, id, 0L);
		}
		log.info("Job get index {}", job.getIndex());
		Pageable pageable = PageRequest.of(0, size);
		List<SendMessageUsers> entities = this.sendMessageUsersRepository.findUserIdBySendIdAndStatus(job.getIndex(), id, pageable);
		if (entities != null && ! entities.isEmpty()) {
			this.sendMessageUsersRepository.setStatusAndResponseCodeById(entities.get(0).getId(), entities.get(entities.size()-1).getId(), SendMessageStatus.SUCCESS.getValue(), 200, DateTimeModel.getTime());
			job.setIndex(entities.get(entities.size()-1).getId());
			this.pushJobRepository.save(job);
			log.info("Min Id {} Max Id {}", entities.get(0).getId(), entities.get(entities.size()-1).getId());
			log.info("Job index {}", job.getIndex());
		}
		return entities;
	}
	
	public void saveAllUsers(List<SendMessageUsers> users, SendMessageStatus status) {
		List<Long> ids = new ArrayList<>();
		if (users == null || users.isEmpty()) {
			return;
		}
		for (SendMessageUsers entity : users) {
			ids.add(entity.getId());
		}
		this.setSendUsersStatusAndResponseCodeByIds(ids, status, 200);
	}
	
	
	public List<LineUser> getLineUsersByIds(List<Long> ids) {
		return this.lineUserRepository.findByIdIn(ids);
	}
	public List<String> getUidByUsers(List<LineUser> users) {
		List<String> result = new ArrayList<>();
		for (LineUser lineUser : users) {
			result.add(lineUser.getLineUid());
		}
		return result;
	}
	public List<Long> getIdBySendUserList(List<SendMessageUsers> sendUserList) {
		List<Long> result = new ArrayList<>();
		for (SendMessageUsers user : sendUserList) {
			result.add(user.getId());
		}
		return result;
	}
	public List<String> getUidsBySendUserList(List<SendMessageUsers> sendUserList) {
		List<Long> ids = new ArrayList<>();
		for (SendMessageUsers sendUser : sendUserList) {
			ids.add(sendUser.getLineUserId());
		}
		List<LineUser> users = this.getLineUsersByIds(ids);
		return this.getUidByUsers(users);
	}

	public List<Message> getMsgBySendId(long id) throws DaoException {
		Pageable pageable = PageRequest.of(0, 5);
		Page<SendMessageList> pages = this.sendMessageListRepository.getListBySendId(id, pageable);
		List<SendMessageList> list = pages.getContent();
		return this.getMsgBySendList(list);
	}
	
	private List<Message> getMsgBySendList(List<SendMessageList> list) throws DaoException {
		List<Message> messages = new ArrayList<>();
		for (SendMessageList entity : list) {
			Message message = this.msgDao.getMessageByTypeAndID(entity.getMessageType(), entity.getMessageId());
			if (message != null) {
				messages.add(message);
			}
		}
		return messages;
	}
	
}
