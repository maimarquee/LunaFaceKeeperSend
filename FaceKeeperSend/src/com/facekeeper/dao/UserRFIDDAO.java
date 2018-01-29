package com.facekeeper.dao;

import java.sql.ResultSet;
import java.util.List;

import com.facekeeper.dto.UserRFIDDTO;
import com.mytechnopal.DAOBase;
import com.mytechnopal.DTOBase;

public class UserRFIDDAO extends DAOBase {
	private static final long serialVersionUID = 1L;
	
	private String qryUserRFIDList = "USER_RFID_LIST";
	private String qryUserRFIDByRFId = "USER_RFID_BY_RFID";
	
	@Override
	public void executeAdd(DTOBase arg0) {
		// TODO Auto-generated method stub

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
	
	public List<DTOBase> getUserRFIDList() {
		return getDTOList(qryUserRFIDList);
	}

	public UserRFIDDTO getUserRFIDByRFID(String rfid) {
		return (UserRFIDDTO) getDTO(qryUserRFIDByRFId, rfid);
	}
	
	@Override
	protected DTOBase rsToObj(ResultSet resultSet) {
		UserRFIDDTO userRFID = new UserRFIDDTO();
		userRFID.setRfid((String) getDBVal(resultSet, "rfid"));
		userRFID.setUserCode((String) getDBVal(resultSet, "user_code"));
		userRFID.setLastName((String) getDBVal(resultSet, "last_name"));
		userRFID.setFirstName((String) getDBVal(resultSet, "first_name"));
		userRFID.setMiddleName((String) getDBVal(resultSet, "middle_name"));
		userRFID.setPrefixName((String) getDBVal(resultSet, "prefix_name"));
		userRFID.setSuffixName((String) getDBVal(resultSet, "suffix_name"));
		userRFID.setOtherTitle((String) getDBVal(resultSet, "other_title"));
		userRFID.setGender((String) getDBVal(resultSet, "gender"));
		userRFID.setCpNumber((String) getDBVal(resultSet, "cp_number"));
		userRFID.setUserGroupCodes((String) getDBVal(resultSet, "user_group_codes"));
		userRFID.setProgramCodes((String) getDBVal(resultSet, "program_group_codes"));
		userRFID.setContactPerson((String) getDBVal(resultSet, "Contact_person"));
		userRFID.setContactRelation((String) getDBVal(resultSet, "Contact_relation"));
		userRFID.setContactAddress((String) getDBVal(resultSet, "Contact_address"));
		userRFID.setContactCPNumber((String) getDBVal(resultSet, "Contact_cp_number"));
		userRFID.setContactLandlineNumber((String) getDBVal(resultSet, "Contact_landline_number"));
		userRFID.setContactEmailAddress((String) getDBVal(resultSet, "contact_email_address"));
		userRFID.setContactFacebookId((String) getDBVal(resultSet, "facebook_id"));
		userRFID.setProfilePict((String) getDBVal(resultSet, "profile_pict"));
		return userRFID;
	}

}
