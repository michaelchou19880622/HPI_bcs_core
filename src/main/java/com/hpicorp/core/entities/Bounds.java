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
@Table(name = "bounds")
public class Bounds implements Serializable {

	private static final long serialVersionUID = 4006975089372656133L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "richmenu_areas_id", insertable = false, updatable = false)
	private Long richmenuAreasId;
	
	@Column(name = "x")
	private Integer x;
	
	@Column(name = "y")
	private Integer y;
	
	@Column(name = "width")
	private Integer width;
	
	@Column(name = "height")
	private Integer height;
	
	@OneToOne(targetEntity = RichMenuAreas.class)
	@JoinColumn(name = "richmenu_areas_id")
	@JsonIgnore
	private RichMenuAreas richmenuAreas;
	
}
