package com.facekeeper.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.facekeeper.dao.FaceLogDAO;
import com.facekeeper.dao.UserRFIDDAO;
import com.facekeeper.dto.FaceLogDTO;
import com.facekeeper.dto.UserRFIDDTO;
import com.facekeeper.util.SettingsUtil;
import com.mytechnopal.DAOConnector;
import com.mytechnopal.Message;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;
/**
 * Servlet implementation class ActionServlet
 */
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errMsg = "";
		FaceLogDAO faceLogDAO = new FaceLogDAO();
		faceLogDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_FACEKEEPER1;
		
		String rfid = request.getParameter("rfid");
		FaceLogDTO faceLog = getFaceLog(getUserRFID(rfid));
		FaceLogDTO faceLogLatest = faceLogDAO.getFaceLogLatestByRFID(rfid);
		if(faceLogLatest == null) { //first time to log in
			faceLog.setIn(true);
			faceLog.setPict(request.getParameter("pict").replace(" ", "+"));
		}
		else { //already been logged
			if(DateTimeUtil.addTime(faceLogLatest.getTimeLog(), 1).getTime() - faceLog.getTimeLog().getTime() <= 0) { //normal tap interval
				if(faceLogLatest.isIn()) { //from in to out
					faceLog.setIn(false);
				}
				else { // from out to in
					faceLog.setIn(true);
					faceLog.setPict(request.getParameter("pict").replace(" ", "+"));
				}
			}
			else { //double tapped - tap within a minute
				String timeInOutNext = "TIME IN";
				if(faceLogLatest.isIn()) {
					timeInOutNext = "TIME OUT";
				}
				errMsg =  "Double Tapped! " + StringUtil.getFullName(faceLog.getUserRFID().getLastName(), faceLog.getUserRFID().getFirstName(), faceLog.getUserRFID().getMiddleName(), faceLog.getUserRFID().getSuffixName(), true, true) + " please wait for " + String.valueOf(60 - ((faceLog.getTimeLog().getTime() - faceLogLatest.getTimeLog().getTime())/1000)) + " seconds to " +  timeInOutNext + ".";
			}
		}
		
		int groupNum = getNextGroupNum(Integer.parseInt(request.getParameter("groupNum")));
		if(StringUtil.isEmpty(errMsg)) {
			errMsg = addFaceLogAndSendSMS(faceLogDAO, faceLog, groupNum);
		}
		else {
			groupNum = Integer.parseInt(request.getParameter("groupNum"));
		}
		sendResponse(response, faceLog, faceLogLatest, groupNum, errMsg);
	}
	
	private void sendResponse(HttpServletResponse response, FaceLogDTO faceLog, FaceLogDTO faceLogLatest, int groupNum, String errMsg) {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("errMsg", errMsg);
			jsonObj.put("timeInPict", faceLog.isIn()?faceLog.getPict():faceLogLatest.getPict());
			jsonObj.put("firstName", faceLog.getUserRFID().getFirstName());
			jsonObj.put("lastName", faceLog.getUserRFID().getLastName());
			jsonObj.put("profilePict", faceLog.getUserRFID().getProfilePict());
			jsonObj.put("timestamp", DateTimeUtil.getDateTimeToStr(faceLog.getTimeLog(), "MM-dd-yyyy kk:mm:ss"));
			jsonObj.put("timeLog", DateTimeUtil.getDateTimeToStr(faceLog.getTimeLog(), "kk:mm:ss"));
			jsonObj.put("isIn", faceLog.isIn());
			jsonObj.put("nextGroupNum", groupNum);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			response.getWriter().print(jsonObj);
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	private String addFaceLogAndSendSMS(FaceLogDAO faceLogDAO, FaceLogDTO faceLog, int groupNum) {
		String errMsg = "";
		String timeInOut = "TIME OUT";
		if(faceLog.isIn()) {
			timeInOut = "TIME IN";
		}

		faceLog.getSmsOutbox().setGroupNum(groupNum);
		faceLog.getSmsOutbox().setSmsInboxCode(faceLog.getLocation());
		faceLog.getSmsOutbox().setCpNumber(faceLog.getUserRFID().getContactCPNumber());
		if(SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_FK)) { //Demo
			faceLog.getSmsOutbox().setMessage("FaceKeeper Demo Report \nName: " + StringUtil.getFullName(faceLog.getUserRFID().getLastName(), faceLog.getUserRFID().getFirstName(), faceLog.getUserRFID().getMiddleName(), faceLog.getUserRFID().getSuffixName(), true, true) + "\n" + timeInOut + ": " + DateTimeUtil.getDateTimeToStr(faceLog.getTimeLog(), "MM-dd-yyyy kk:mm:ss") + "\nLOCATION: " + faceLog.getLocation() + ". -no reply needed");
		}
		else {
			faceLog.getSmsOutbox().setMessage(SettingsUtil.OWNER_NAME_SHORTCUT + " FaceKeeper Report \nName: " + StringUtil.getFullName(faceLog.getUserRFID().getLastName(), faceLog.getUserRFID().getFirstName(), faceLog.getUserRFID().getMiddleName(), faceLog.getUserRFID().getSuffixName(), true, true) + "\n" + timeInOut + ": " + DateTimeUtil.getDateTimeToStr(faceLog.getTimeLog(), "MM-dd-yyyy kk:mm:ss") + "\nLOCATION: " + SettingsUtil.FACEKEEPER_LOCATION + ". -no reply needed");
		}
		faceLogDAO.executeAdd(faceLog);
		Message message = (Message) faceLogDAO.getResult().get(Message.SESSION_MESSAGE);
		if(!message.isMessageTypeSuccess()) {
			errMsg = "Please try again > " + faceLog.getUserRFID().getFirstName(); 
		}
		return errMsg;
	}
	
	private FaceLogDTO getFaceLog(UserRFIDDTO userRFID) {
		FaceLogDTO faceLog = new FaceLogDTO();
		faceLog.setUserRFID(userRFID);
		faceLog.setLocation(SettingsUtil.FACEKEEPER_LOCATION.length()>16?StringUtil.getLeft(SettingsUtil.FACEKEEPER_LOCATION, 16):SettingsUtil.FACEKEEPER_LOCATION);
		return faceLog;
	}
	
	private UserRFIDDTO getUserRFID(String rfid) {
		UserRFIDDAO userRFIDDAO = new UserRFIDDAO();
		userRFIDDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_FACEKEEPER1;
		UserRFIDDTO userRFID = userRFIDDAO.getUserRFIDByRFID(rfid);
		
		if(userRFID == null) { //Guest
			userRFID = userRFIDDAO.getUserRFIDByRFID(SettingsUtil.FACEKEEPER_GUEST_RFID);
			userRFID.setRfid(rfid);
			if(SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_FK)) { //Demo
				userRFID.setContactCPNumber("0" + rfid);
			}
		}
		return userRFID;
	}

	private int getNextGroupNum(int groupNum) {
		int nextGroupNum = groupNum + 1;
		if(groupNum == SettingsUtil.OWNER_SMS_GATEWAY_LIST.length) {
			nextGroupNum = 1;
		}
		return nextGroupNum;
	}
}
