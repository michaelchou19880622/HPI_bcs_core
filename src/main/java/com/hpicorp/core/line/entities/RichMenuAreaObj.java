package com.hpicorp.core.line.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RichMenuAreaObj {

	private RichMenuBoundsObj bounds;
	
	private ActionObj action;
}
