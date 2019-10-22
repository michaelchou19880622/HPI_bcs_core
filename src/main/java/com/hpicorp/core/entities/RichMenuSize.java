package com.hpicorp.core.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "richmenu")
@Entity
@Table(name = "richmenu_size")
public class RichMenuSize implements Serializable {

	private static final long serialVersionUID = -4551534910336358179L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "richmenu_id", insertable = false, updatable = false)
	private Long richMenuId;
	
	@Column(name = "width")
	private Integer width;
	
	@Column(name = "height")
	private Integer height;
	
	@OneToOne(targetEntity = RichMenu.class)
	@JoinColumn(name = "richmenu_id")
	@JsonIgnore
	private RichMenu richmenu;
	
}
