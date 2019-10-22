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
@ToString(exclude = "richmenuAreas")
@Entity
@Table(name = "action")
public class Action implements Serializable {
	
	private static final long serialVersionUID = -238540646096844739L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "richmenu_areas_id", insertable = false, updatable = false)
	private Long richmenuAreasId;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "label")
	private String label;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "uri")
	private String uri;
	
	@Column(name = "data")
	private String data;
	
	@Column(name = "key_id")
	private String key;
	
	@Column(name = "level")
	private String level;
	
	@Column(name = "show_up")
	private Boolean showUp;
	
	@Column(name = "temp_type")
	private String tempType;
	
	@OneToOne(targetEntity = RichMenuAreas.class)
	@JoinColumn(name = "richmenu_areas_id")
	@JsonIgnore
	private RichMenuAreas richmenuAreas;

}
