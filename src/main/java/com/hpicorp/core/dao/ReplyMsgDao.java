package com.hpicorp.core.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.hpicorp.core.entities.AutoreplyMessageList;
import com.hpicorp.core.enums.LineUserBindStatus;
import com.hpicorp.core.exception.DaoException;
import com.hpicorp.core.repository.AutoreplyMessageListRepository;
import com.hpicorp.core.repository.AutoreplyRepository;
import com.linecorp.bot.model.message.Message;

@Repository
public class ReplyMsgDao {

	@Autowired
	private MsgDao msgDao;

	@Autowired
	private AutoreplyRepository autoreplyRepository;

	@Autowired
	private AutoreplyMessageListRepository autoreplyMessageListRepository;

	/**
	 * 關鍵字流程，查詢出該關鍵字 ID 與 回復狀態（全部、未綁定、已綁定）
	 * @param keyword 關鍵字
	 * @return
	 */
	public Map<String, Object> findIdAndStatus(String keyword) {
		 return autoreplyRepository.findIdAndStatus(keyword, new Date());
	}

	public List<Message> getMsgsByTextAndType(String text) throws DaoException {
		Pageable pageable = PageRequest.of(0, 5);
		Page<AutoreplyMessageList> msgPage = this.autoreplyMessageListRepository.getListByKeyWord(text, new Date(), pageable);
		List<AutoreplyMessageList> msgList = msgPage.getContent();
		return this.getResultsByMsgList(msgList);
	}

	/**
	 * 取得關鍵字要回覆的訊息
	 * @param text 關鍵字
	 * @param status 用戶的綁定狀態
	 * @return
	 * @throws DaoException
	 */
	public List<AutoreplyMessageList> getMsgsByTextAndTypeAndUserStatus(String text, LineUserBindStatus status) throws DaoException {
		List<String> allowStatus = Arrays.asList(LineUserBindStatus.ALL.toString(), status.toString());
		Pageable pageable = PageRequest.of(0, 5);
		Page<AutoreplyMessageList> msgPage = this.autoreplyMessageListRepository.getListByKeyWordAndDateAndStatus(text, new Date(), allowStatus, pageable);
		return msgPage.getContent();
	}

	public List<Message> getResultsByMsgList(List<AutoreplyMessageList> msgList) throws DaoException {
		List<Message> results = new ArrayList<>();
		for (AutoreplyMessageList m : msgList) {
			Long messageID = m.getMessageId();
			String messageType = m.getMessageType();
			Message message = this.msgDao.getMessageByTypeAndID(messageType, messageID);
			if (message != null) {
				results.add(message);
			}
		}
		return results;
	}

}
