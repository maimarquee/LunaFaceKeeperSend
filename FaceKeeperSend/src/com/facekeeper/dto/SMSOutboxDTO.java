package com.facekeeper.dto;

import com.mytechnopal.DTOBase;

public class SMSOutboxDTO extends DTOBase {
	private static final long serialVersionUID = 1L;
	
	private String smsInboxCode;
	private String cpNumber;
	private String message;
	private int priority;
	private int groupNum;
	private boolean isSent;
	
	public SMSOutboxDTO() {
		super();
		this.smsInboxCode = "";
		this.cpNumber = "";
		this.message = "";
		this.priority = 9;
		this.groupNum = 1;
		isSent = false;
	}
	
	public SMSOutboxDTO getSMSOutbox() {
		SMSOutboxDTO smsOutbox = new SMSOutboxDTO();
		smsOutbox.setId(super.getId());
		smsOutbox.setSmsInboxCode(this.smsInboxCode);
		smsOutbox.setCpNumber(this.cpNumber);
		smsOutbox.setMessage(this.message);
		smsOutbox.setPriority(this.priority);
		smsOutbox.setGroupNum(this.groupNum);
		smsOutbox.setSent(this.isSent);
		return smsOutbox;
	}
	
	public String getSmsInboxCode() {
		return smsInboxCode;
	}
	
	public void setSmsInboxCode(String smsInboxCode) {
		this.smsInboxCode = smsInboxCode;
	}
	
	public String getCpNumber() {
		return cpNumber;
	}
	
	public void setCpNumber(String cpNumber) {
		this.cpNumber = cpNumber;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getGroupNum() {
		return groupNum;
	}

	public void setGroupNum(int groupNum) {
		this.groupNum = groupNum;
	}

	public boolean isSent() {
		return isSent;
	}

	public void setSent(boolean isSent) {
		this.isSent = isSent;
	}
}
