package com.hpicorp.core.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "richmenu")
public class RichMenu implements Serializable {

	private static final long serialVersionUID = -2617764726202146231L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "richmenu_id")
	private String richMenuId;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "richmenu", targetEntity = RichMenuSize.class)
	private RichMenuSize size;
	
	@Column(name = "selected")
	private String selected;
	
	@Column(name = "name", columnDefinition="nvarchar(255)")
	private String name;
	
	@Column(name = "chat_bar_text", columnDefinition="nvarchar(255)")
	private String chatBarText;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "richmenu", targetEntity = RichMenuAreas.class, orphanRemoval = true)
	private List<RichMenuAreas> areas;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "custom_id")
	private Long customId;
	
	@Column(name = "group_id")
	private Long groupId;
	
	@Column(name = "key_id")
	private String key;
	
	@Column(name = "level")
	private String level;
	
	@Column(name = "create_time")
	private String createTime;
	
	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "start_time")
	private String startTime;
	
	@Column(name = "modify_time")
	private Date modifyTime;
	
}
