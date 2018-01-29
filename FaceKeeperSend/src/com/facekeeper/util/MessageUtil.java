package com.facekeeper.util;

import java.util.ArrayList;

import com.facekeeper.dao.MessageDAO;
import com.facekeeper.dto.MessageDTO;
import com.mytechnopal.DTOBase;

public class MessageUtil {

	public String messageContent(String code){
		MessageDAO messageDao = new MessageDAO();
		String message = "";
		ArrayList<DTOBase> messageList = (ArrayList<DTOBase>) messageDao.getMessageListByCode(code);
		for(DTOBase messageObj: messageList){
			MessageDTO messageContent = (MessageDTO)messageObj;
			message = "<li>" + "<h1 style='color:white;'>" + messageContent.getContent() + "</h1>" + "</li>";
		}
		return message;
	}
}
