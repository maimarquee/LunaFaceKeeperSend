package com.facekeeper.dto;

import com.mytechnopal.DTOBase;

public class UserRFIDDTO extends DTOBase{
	private static final long serialVersionUID = 1L;	
		
	private String rfid;
	private String userCode;
	private String lastName;
	private String firstName;
	private String middleName;
	private String prefixName;
	private String suffixName;
	private String otherTitle;
	private String gender;
	private String cpNumber;
	private String userGroupCodes;
	private String programCodes;
	
	private String contactPerson;
	private String contactRelation;
	private String contactAddress;
	private String contactCPNumber;
	private String contactLandlineNumber;
	private String contactEmailAddress;
	private String contactFacebookId;
	private String profilePict;

	public UserRFIDDTO() {
		super();
		rfid = "";
		userCode = "";
		lastName = "";
		firstName = "";
		middleName = "";
		prefixName = "";
		suffixName = "";
		otherTitle = "";
		gender = "";
		cpNumber = "";
		userGroupCodes = "";
		programCodes = "";
		contactPerson = "";
		contactRelation = "";
		contactAddress = "";
		contactCPNumber = "";
		contactLandlineNumber = "";
		contactEmailAddress = "";
		contactFacebookId = "";
		profilePict = "";
	}

	public UserRFIDDTO getUserRFID() {
		UserRFIDDTO userRFID = new UserRFIDDTO();
		userRFID.setId(super.getId());
		userRFID.setRfid(this.rfid);
		userRFID.setUserCode(this.userCode);
		userRFID.setLastName(this.lastName);
		userRFID.setFirstName(this.firstName);
		userRFID.setMiddleName(this.middleName);
		userRFID.setPrefixName(this.prefixName);
		userRFID.setSuffixName(this.suffixName);
		userRFID.setOtherTitle(this.otherTitle);
		userRFID.setGender(this.gender);
		userRFID.setCpNumber(this.cpNumber);
		userRFID.setUserGroupCodes(this.userGroupCodes);
		userRFID.setProgramCodes(this.programCodes);
		userRFID.setContactPerson(this.contactPerson);
		userRFID.setContactRelation(this.contactRelation);
		userRFID.setContactAddress(this.contactAddress);
		userRFID.setContactCPNumber(this.contactCPNumber);
		userRFID.setContactLandlineNumber(this.contactLandlineNumber);
		userRFID.setContactEmailAddress(this.contactEmailAddress);
		userRFID.setContactFacebookId(this.contactFacebookId);
		userRFID.setProfilePict(this.profilePict);
		return userRFID;
	}

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String rfid) {
		this.rfid = rfid;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPrefixName() {
		return prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getSuffixName() {
		return suffixName;
	}

	public void setSuffixName(String suffixName) {
		this.suffixName = suffixName;
	}

	public String getOtherTitle() {
		return otherTitle;
	}

	public void setOtherTitle(String otherTitle) {
		this.otherTitle = otherTitle;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCpNumber() {
		return cpNumber;
	}

	public void setCpNumber(String cpNumber) {
		this.cpNumber = cpNumber;
	}

	public String getUserGroupCodes() {
		return userGroupCodes;
	}

	public void setUserGroupCodes(String userGroupCodes) {
		this.userGroupCodes = userGroupCodes;
	}

	public String getProgramCodes() {
		return programCodes;
	}

	public void setProgramCodes(String programCodes) {
		this.programCodes = programCodes;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getContactRelation() {
		return contactRelation;
	}

	public void setContactRelation(String contactRelation) {
		this.contactRelation = contactRelation;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactCPNumber() {
		return contactCPNumber;
	}

	public void setContactCPNumber(String contactCPNumber) {
		this.contactCPNumber = contactCPNumber;
	}

	public String getContactLandlineNumber() {
		return contactLandlineNumber;
	}

	public void setContactLandlineNumber(String contactLandlineNumber) {
		this.contactLandlineNumber = contactLandlineNumber;
	}

	public String getContactEmailAddress() {
		return contactEmailAddress;
	}

	public void setContactEmailAddress(String contactEmailAddress) {
		this.contactEmailAddress = contactEmailAddress;
	}

	public String getContactFacebookId() {
		return contactFacebookId;
	}

	public void setContactFacebookId(String contactFacebookId) {
		this.contactFacebookId = contactFacebookId;
	}

	public String getProfilePict() {
		return profilePict;
	}

	public void setProfilePict(String profilePict) {
		this.profilePict = profilePict;
	}
}
