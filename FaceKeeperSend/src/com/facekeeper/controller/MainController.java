package com.facekeeper.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facekeeper.dao.MessageDAO;
import com.facekeeper.dto.MessageDTO;
import com.facekeeper.util.MessageUtil;
import com.facekeeper.util.SettingsUtil;
import com.mytechnopal.DTOBase;
import com.mytechnopal.util.StringUtil;

public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	String newCode = "", message = "";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageDAO messageDao = new MessageDAO();
		MessageUtil messageUtil = new MessageUtil();
		if(messageDao.getLastCode() == ""){
			message = "<li>" + "<h1 style='color:white;'>" + SettingsUtil.OWNER_CODE + " FaceKeeper Powered by Technopal Software" + "</h1>" + "</li>";;
		}
		else if(messageDao.getMessageList().size() == 1){
			message = messageUtil.messageContent(messageDao.getLastCode());
		}
		else if(messageDao.getMessageList().size() != 1){
			if(newCode == "" || newCode.equalsIgnoreCase(messageDao.getLastCode())) {
				message = messageUtil.messageContent(messageDao.getLastCode());
				newCode = messageDao.getLastDatabaseCodeMinusOne();
			}
			else if(newCode.equalsIgnoreCase(MessageDTO.DEFAULT_CODE)) {
				message = messageUtil.messageContent(MessageDTO.DEFAULT_CODE);
				newCode = messageDao.getLastCode();
			}
			else if (Integer.parseInt(newCode) <= Integer.parseInt(messageDao.getLastDatabaseCodeMinusOne()) && !newCode.equalsIgnoreCase(MessageDTO.DEFAULT_CODE) && !newCode.equalsIgnoreCase(messageDao.getLastCode())){
				message = messageUtil.messageContent(newCode);
				newCode = messageDao.getLastCodeMinusOne(newCode);
			}
		}
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		out.print(message);
	}
}
