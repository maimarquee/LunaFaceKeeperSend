package com.facekeeper.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.facekeeper.dto.SMSOutboxDTO;
import com.mytechnopal.DAOBase;
import com.mytechnopal.DTOBase;
import com.mytechnopal.Message;

public class SMSOutboxDAO extends DAOBase {
	private static final long serialVersionUID = 1L;

	private String qrySMSOutboxAdd = "SMS_OUTBOX_ADD";
	private String qrySMSOutboxUpdate = "SMS_OUTBOX_UPDATE";
	private String qrySMSOutboxDelete = "SMS_OUTBOX_DELETE";
	private String qrySMSOutboxListByIsSentGroupNum = "SMS_OUTBOX_LIST_BY_ISSENTGROUPNUM";
	
	@Override
	public void executeAdd(DTOBase obj) {
		Connection conn = daoConnector.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		SMSOutboxDTO smsOutbox = (SMSOutboxDTO) obj;
		add(conn, prepStmntList, smsOutbox);
		result.put(Message.SESSION_MESSAGE, executeIUD(conn, prepStmntList));
	}

	protected void add(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		SMSOutboxDTO smsOutbox = (SMSOutboxDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qrySMSOutboxAdd));
			prepStmnt.setString(1, smsOutbox.getSmsInboxCode());
			prepStmnt.setString(2, smsOutbox.getCpNumber());
			prepStmnt.setString(3, smsOutbox.getMessage());
			prepStmnt.setInt(4, smsOutbox.getPriority());
			prepStmnt.setInt(5, smsOutbox.getGroupNum());
			prepStmnt.setBoolean(6, smsOutbox.isSent());
			prepStmnt.setString(7, smsOutbox.getAddedBy());
			prepStmnt.setTimestamp(8, smsOutbox.getAddedTimestamp());
			prepStmnt.setString(9, smsOutbox.getUpdatedBy());
			prepStmnt.setTimestamp(10, smsOutbox.getUpdatedTimestamp());	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public void executeAddList(List<DTOBase> smsList) {
		
	}
	
	
	@Override
	public void executeDelete(DTOBase arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void executeUpdate(DTOBase obj) {
		Connection conn = daoConnector.getConnection();
		List<PreparedStatement> prepStmntList = new ArrayList<PreparedStatement>();
		SMSOutboxDTO smsOutbox = (SMSOutboxDTO) obj;
		smsOutbox.setBaseDataOnUpdate();
		update(conn, prepStmntList, smsOutbox);
		result.put(Message.SESSION_MESSAGE, executeIUD(conn, prepStmntList));
	}
	
	protected void update(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		SMSOutboxDTO smsOutbox = (SMSOutboxDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qrySMSOutboxUpdate));
			prepStmnt.setBoolean(1, smsOutbox.isSent());
			prepStmnt.setString(2, smsOutbox.getUpdatedBy());
			prepStmnt.setTimestamp(3, smsOutbox.getUpdatedTimestamp());	
			prepStmnt.setInt(4, smsOutbox.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	public List<DTOBase> getSMSOutboxListByIsSentGroupNum(boolean isSent, int groupNum) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(isSent);
		paramList.add(groupNum);
		return getDTOList(qrySMSOutboxListByIsSentGroupNum, paramList);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		SMSOutboxDTO smsOutbox = new SMSOutboxDTO();
		smsOutbox.setId((Integer) getDBVal(resultSet, "id"));
		smsOutbox.setSmsInboxCode((String) getDBVal(resultSet, "sms_inbox_code"));
		smsOutbox.setCpNumber((String) getDBVal(resultSet, "cp_number"));
		smsOutbox.setMessage((String) getDBVal(resultSet, "message"));
		smsOutbox.setPriority((Integer) getDBVal(resultSet, "priority"));
		smsOutbox.setGroupNum((Integer) getDBVal(resultSet, "group_num"));
		smsOutbox.setSent((Boolean) getDBVal(resultSet, "is_sent"));
		return smsOutbox;
	}

	@Override
	public void executeDeleteList(List<DTOBase> list) {
		// TODO Auto-generated method stub
		
	}

	protected void delete(Connection conn, List<PreparedStatement> prepStmntList, Object obj) {
		SMSOutboxDTO smsOutbox = (SMSOutboxDTO) obj;
		PreparedStatement prepStmnt = null;
		try {
			prepStmnt = conn.prepareStatement(getQueryStatement(qrySMSOutboxDelete));
			prepStmnt.setInt(1, smsOutbox.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		prepStmntList.add(prepStmnt);
	}
	
	@Override
	public void executeUpdateList(List<DTOBase> arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] a) {
		List<DTOBase> smsOutboxList = new SMSOutboxDAO().getSMSOutboxListByIsSentGroupNum(false, 1);
		for(DTOBase smsOutboxDTO: smsOutboxList) {
			SMSOutboxDTO smsOutbox = (SMSOutboxDTO) smsOutboxDTO;
			System.out.println(smsOutbox.getCode() + " " + smsOutbox.isSent());
		}
	}
	
}
