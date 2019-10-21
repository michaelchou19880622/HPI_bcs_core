package com.hpicorp.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomLinkAddressTrack {

	private String title;

	private String url;
	
	private Long linkAddressListId;
	
	private Integer tot;
	
	private Integer cnt;

}
