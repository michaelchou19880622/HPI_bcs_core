package com.hpicorp.core.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "richmenu")
@Entity
@Table(name = "richmenu_areas")
public class RichMenuAreas implements Serializable {
	
	private static final long serialVersionUID = 1161781035099618446L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "richmenu_id", insertable = false, updatable = false)
	private Long richmenuId;
	
	@Column(name = "x")
	private Integer x;
	
	@Column(name = "y")
	private Integer y;
	
	@Column(name = "width")
	private Integer width;
	
	@Column(name = "height")
	private Integer height;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "richmenuAreas", targetEntity = Bounds.class)
	private Bounds bounds;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "richmenuAreas", targetEntity = Action.class)
	private Action action;
	
	@ManyToOne(targetEntity = RichMenu.class)
	@JoinColumn(name = "richmenu_id")
	@JsonIgnore
	private RichMenu richmenu;

}
