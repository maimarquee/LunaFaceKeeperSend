package com.facekeeper.dto;

import com.mytechnopal.DTOBase;

public class MessageDTO extends DTOBase {
	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_CODE = "0001"; //if you add character to default code you alter getLastCodeMinusOne() method in MainController.java

	private String content;

	public MessageDTO() {
		super();
		this.content = "";
	}

	public MessageDTO getMessage(){
		MessageDTO message = new MessageDTO();
		message.setId(super.getId());
		message.setCode(super.getCode());
		message.setContent(this.content);
		return message;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}