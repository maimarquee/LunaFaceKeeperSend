package com.facekeeper.dto;

import java.sql.Timestamp;
import com.mytechnopal.DTOBase;
import com.mytechnopal.util.DateTimeUtil;

public class FaceLogDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_FACELOG = "SESSION_FACELOG";
	public static final String SESSION_FACELOG_LIST = "SESSION_FACELOG_LIST";
	public static final String SESSION_FACELOG_PHOTO_STRING = "SESSION_FACELOG_PHOTO_STRING";
	
	private UserRFIDDTO userRFID;
	private Timestamp timeLog;
	private String pict;
	private boolean isIn;
	private String location;
	private boolean isProcess;
	private SMSOutboxDTO smsOutbox;
	
	
	public FaceLogDTO() {
		super();
		this.userRFID = new UserRFIDDTO();
		this.timeLog = DateTimeUtil.getCurrentTimestamp();
		this.pict = "";
		this.isIn = false;
		this.location = "";
		this.isProcess = false;
		this.smsOutbox = new SMSOutboxDTO();
	}

	public FaceLogDTO getFaceLog() {
		FaceLogDTO faceLog = new FaceLogDTO();
		faceLog.setId(super.getId());
		faceLog.setCode(super.getCode());
		faceLog.setUserRFID(this.userRFID);
		faceLog.setTimeLog(this.timeLog);
		faceLog.setPict(this.pict);
		faceLog.setIn(this.isIn);
		faceLog.setLocation(this.location);
		faceLog.setProcess(this.isProcess);
		faceLog.setSmsOutbox(this.smsOutbox);
		return faceLog;
	}
	
	public Timestamp getTimeLog() {
		return timeLog;
	}

	public void setTimeLog(Timestamp timeLog) {
		this.timeLog = timeLog;
	}

	public boolean isIn() {
		return isIn;
	}

	public void setIn(boolean isIn) {
		this.isIn = isIn;
	}

	public String getPict() {
		return pict;
	}

	public void setPict(String pict) {
		this.pict = pict;
	}

	public UserRFIDDTO getUserRFID() {
		return userRFID;
	}

	public void setUserRFID(UserRFIDDTO userRFID) {
		this.userRFID = userRFID;
	}

	public boolean isProcess() {
		return isProcess;
	}

	public void setProcess(boolean isProcess) {
		this.isProcess = isProcess;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public SMSOutboxDTO getSmsOutbox() {
		return smsOutbox;
	}

	public void setSmsOutbox(SMSOutboxDTO smsOutbox) {
		this.smsOutbox = smsOutbox;
	}
}
