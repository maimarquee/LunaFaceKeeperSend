package com.facekeeper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.facekeeper.dto.FaceLogDTO;
import com.facekeeper.util.SettingsUtil;
import com.mytechnopal.DAOBase;
import com.mytechnopal.DAOConnector;
import com.mytechnopal.DTOBase;
import com.mytechnopal.Message;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.StringUtil;

public class FaceLogDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryFaceLogAdd = "FACE_LOG_ADD";
	private String qryFaceLogLatestByRFID = "FACE_LOG_LATEST_BY_RFID";
	private String qryFaceLogListByTimeLogFromAndTimeLogTo = "FACE_LOG_LIST_BY_TIMELOGFROMANDTIMELOGTO";

	@Override
	public void executeAdd(DTOBase obj) {
		Connection conn = daoConnector.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		FaceLogDTO faceLog = (FaceLogDTO) obj;
		faceLog.setCode(DateTimeUtil.getDateTimeToStr(faceLog.getTimeLog(), "yyyyMMddkkss" + StringUtil.getRight(faceLog.getUserRFID().getRfid(), 4)));
		add(conn, prepStmntList, faceLog);
		if(StringUtil.isValidCPNumber(faceLog.getUserRFID().getContactCPNumber())) {
			SMSOutboxDAO smsOutboxDAO = new SMSOutboxDAO();
			if(SettingsUtil.OWNER_CODE.equalsIgnoreCase(SettingsUtil.OWNER_CODE_FK)) { //Demo
				smsOutboxDAO.daoConnector.serverLocation = DAOConnector.SERVER_LOCATION_INTERNET;
				smsOutboxDAO.executeAdd(faceLog.getSmsOutbox());
			}
			else {
				faceLog.getSmsOutbox().setAddedBy("S001");
				faceLog.getSmsOutbox().setAddedTimestamp(faceLog.getTimeLog());
				faceLog.getSmsOutbox().setBaseDataOnInsert();
				smsOutboxDAO.add(conn, prepStmntList, faceLog.getSmsOutbox());
			}
		}
		result.put(Message.SESSION_MESSAGE, executeIUD(conn, prepStmntList));
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		FaceLogDTO faceLog = (FaceLogDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qryFaceLogAdd));
			prepStmnt.setString(1, faceLog.getCode());
			prepStmnt.setString(2, faceLog.getUserRFID().getUserCode());
			prepStmnt.setString(3, faceLog.getUserRFID().getRfid());
			prepStmnt.setTimestamp(4, faceLog.getTimeLog());
			prepStmnt.setString(5, faceLog.getPict());
			prepStmnt.setBoolean(6, faceLog.isIn());
			prepStmnt.setString(7, SettingsUtil.FACEKEEPER_LOCATION);
			prepStmnt.setBoolean(8, faceLog.isProcess());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}

	@Override
	public void executeAddList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeDelete(DTOBase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeDeleteList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeUpdate(DTOBase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub

	}

	public FaceLogDTO getFaceLogLatestByRFID(String rfid) {
		return (FaceLogDTO) getDTO(qryFaceLogLatestByRFID, rfid);
	}
	
	public List<DTOBase> getFaceLogListByTimeLogFromAndTimeLogTo(Date timeLogFrom, Date timeLogTo) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(timeLogFrom);
		paramList.add(timeLogTo);
		return getDTOList(qryFaceLogListByTimeLogFromAndTimeLogTo, paramList);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		FaceLogDTO faceLog = new FaceLogDTO();
		faceLog.setCode((String) getDBVal(resultSet, "code"));
		faceLog.getUserRFID().setCode((String) getDBVal(resultSet, "code"));
		faceLog.getUserRFID().setUserCode((String) getDBVal(resultSet, "user_code"));
		faceLog.getUserRFID().setRfid((String) getDBVal(resultSet, "rfid"));
		faceLog.setTimeLog((Timestamp) getDBVal(resultSet, "time_log"));
		faceLog.setPict((String) getDBVal(resultSet, "pict"));
		faceLog.setIn((Boolean) getDBVal(resultSet, "is_in"));
		faceLog.setLocation((String) getDBVal(resultSet, "location"));
		faceLog.setProcess((Boolean) getDBVal(resultSet, "is_process"));
		return faceLog;
	}

}
