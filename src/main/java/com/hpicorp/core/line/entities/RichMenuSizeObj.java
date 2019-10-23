package com.hpicorp.core.line.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RichMenuSizeObj {

	public static final RichMenuSizeObj FULL = new RichMenuSizeObj(2500, 1686);
	public static final RichMenuSizeObj HALF = new RichMenuSizeObj(2500, 843);

    private Integer width;

    private Integer height;
}
