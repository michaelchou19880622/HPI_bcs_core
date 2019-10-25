package com.hpicorp.core.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "message_object_imagemap", catalog = "")
public class MessageObjectImageMap implements Serializable {

	private static final long serialVersionUID = -1763430741077063042L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Basic
	@Column(name = "name", nullable = false, length = 50)
	private String name;

	@Basic
	@Column(name = "msg_object", nullable = false, columnDefinition = "text")
	private String msgObject;

	@Basic
	@Column(name = "creator_id", nullable = true)
	private Long creatorId;

	@Basic
	@Column(name = "create_time", nullable = false)
	private Date createTime;

	@Basic
	@Column(name = "updater_id", nullable = true)
	private Long updaterId;

	@Basic
	@UpdateTimestamp
	@Column(name = "update_time", nullable = false)
	private Date updateTime;
	
	   

}
