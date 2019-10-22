package com.hpicorp.core.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "richmenu_list")
public class RichMenuList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "custom_id")
	private Long customeId;
	
	@Column(name = "richmenu_id")
	private String richMenuId;
	
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "modify_time")
	private Date modifyTime;
	
}
