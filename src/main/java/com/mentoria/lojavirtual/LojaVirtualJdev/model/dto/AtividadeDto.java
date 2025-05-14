package com.mentoria.lojavirtual.LojaVirtualJdev.model.dto;

import java.io.Serializable;

public class AtividadeDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String code;
	private String text;
	
	
	public String getCode() {
		return code;
	}
	public String getText() {
		return text;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
