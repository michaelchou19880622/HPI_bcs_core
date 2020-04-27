package com.hpicorp.core.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "BCS_SYSTEM_CONFIG")
public class BcsSystemConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CONFIG_ID", columnDefinition="nvarchar(50)")
	private String configId;

	@Column(name = "VALUE", columnDefinition="nvarchar(500)")
	private String value;

	@Column(name = "DESCRIPTION", columnDefinition="nvarchar(50)")
	private String description;
	
	@Column(name = "MODIFY_TIME")
	private Date modifyTime;

	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
