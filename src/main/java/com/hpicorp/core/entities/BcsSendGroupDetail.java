package com.hpicorp.core.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity(name = "BCS_SEND_GROUP_DETAIL")
@Table(name = "BCS_SEND_GROUP_DETAIL", indexes = { @Index(name = "INDEX_0", columnList = "GROUP_ID") })
public class BcsSendGroupDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "GROUP_DETAIL_ID")
	private Long groupDetailId;
	
	@JoinColumn(name = "GROUP_ID", nullable = false)
	@ManyToOne(targetEntity = BcsSendGroup.class, fetch = FetchType.EAGER)
	@JsonIgnore
	private BcsSendGroup sendGroup;

	@Column(name = "QUERY_FIELD", columnDefinition="nvarchar(50)")
	private String queryField;

	@Column(name = "QUERY_OP", columnDefinition="nvarchar(250)")
	private String queryOp;

	@Column(name = "QUERY_VALUE", columnDefinition="nvarchar(50)")
	private String queryValue;
	
	@Column(name = "GROUP_TYPE", columnDefinition="nvarchar(32)")
	private String groupType;

	public Long getGroupDetailId() {
		return groupDetailId;
	}

	public void setGroupDetailId(Long groupDetailId) {
		this.groupDetailId = groupDetailId;
	}

	public BcsSendGroup getSendGroup() {
		return sendGroup;
	}

	public void setSendGroup(BcsSendGroup sendGroup) {
		this.sendGroup = sendGroup;
	}

	public String getQueryField() {
		return queryField;
	}

	public void setQueryField(String queryField) {
		this.queryField = queryField;
	}

	public String getQueryOp() {
		return queryOp;
	}

	public void setQueryOp(String queryOp) {
		this.queryOp = queryOp;
	}

	public String getQueryValue() {
		return queryValue;
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
}
