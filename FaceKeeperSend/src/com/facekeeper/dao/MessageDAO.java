package com.facekeeper.dao;

import java.sql.ResultSet;
import java.util.List;

import com.facekeeper.dto.MessageDTO;
import com.mytechnopal.DAOBase;
import com.mytechnopal.DTOBase;
import com.mytechnopal.util.StringUtil;

public class MessageDAO extends DAOBase {

	private static final long serialVersionUID = 1L;
	
	private final String qryMessageList = "MESSAGE_LIST";
	private final String qryMessageListByCode = "MESSAGE_LIST_BYCODE";
	private final String qryMessageLastCode = "MESSAGE_LAST_CODE";

	@Override
	public void executeAdd(DTOBase arg0) {
	}

	@Override
	public void executeAddList(List<DTOBase> arg0) {
	}

	@Override
	public void executeDelete(DTOBase arg0) {
	}

	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
	}

	@Override
	public void executeUpdate(DTOBase arg0) {
	}

	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
	}

	public String getLastCode() {
		MessageDTO message = getMessageLastCode();
		String code = "";
		if(message != null) {
			code = StringUtil.getPadded(String.valueOf(Integer.parseInt(StringUtil.getRight(message.getCode(), 4))), 4, "0", true);
		}
		return code;
	}
	
	public String getLastDatabaseCodeMinusOne() {
		MessageDTO messageLastCode = getMessageLastCode();
		String code = "";
		if(messageLastCode.getCode() != ""){
			code = StringUtil.getPadded(String.valueOf(Integer.parseInt(StringUtil.getRight(messageLastCode.getCode(), 4)) - 1), 4, "0", true);
		}
		return code;
	}

	public String getLastCodeMinusOne(String newCode) {
		String code = "";
		if(newCode != "" || newCode.equalsIgnoreCase(getLastDatabaseCodeMinusOne())){
			code = StringUtil.getPadded(String.valueOf(Integer.parseInt(StringUtil.getRight(newCode, 4)) - 1), 4, "0", true);
		}
		return code;
	}

	public MessageDTO getMessageLastCode() {
		return(MessageDTO)getDTO(qryMessageLastCode);
	}

	public List<DTOBase> getMessageListByCode(String code) {
		return getDTOList(qryMessageListByCode, code);
	}

	public List<DTOBase> getMessageList() {
		return getDTOList(qryMessageList);
	}

	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		MessageDTO message = new MessageDTO();
		message.setCode((String)getDBVal(resultSet, "code"));
		message.setContent((String)getDBVal(resultSet, "content"));
		return message;
	}
}
