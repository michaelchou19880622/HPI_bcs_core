package com.hpicorp.core.line.entities;

import java.net.URI;

import lombok.Data;

@Data
public class ImagemapVideoObj {

	private URI originalContentUrl;
	
	private URI previewImageUrl;
	
	private ImagemapAreaObj area;
	
	private ImagemapExternalLinkObj externalLink;
	
}
