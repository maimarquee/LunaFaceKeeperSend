package com.facekeeper.util;

import com.mytechnopal.util.BaseSettingsUtil;
import com.mytechnopal.util.StringUtil;

public class SettingsUtil extends BaseSettingsUtil {
	private static final long serialVersionUID = 1L;

	//current settings path
	public static final String SETTINGS_PROPERTY_PATH = BaseSettingsUtil.PROJECT_BASE_PACKAGE + ".property.Settings";
	
	public static final String BUILD_SERIAL_NUM = StringUtil.getResourceBundleValue("BUILD_SERIAL_NUM", SETTINGS_PROPERTY_PATH);
	
	public static final String OWNER_CODE = StringUtil.getResourceBundleValue("OWNER_CODE", SETTINGS_PROPERTY_PATH);
	public static final String OWNER_NAME = StringUtil.getResourceBundleValue("OWNER_NAME", SETTINGS_PROPERTY_PATH);
	public static final String OWNER_NAME_SHORTCUT = StringUtil.getResourceBundleValue("OWNER_NAME_SHORTCUT", SETTINGS_PROPERTY_PATH);
	public static final String OWNER_ADDRESS = StringUtil.getResourceBundleValue("OWNER_ADDRESS", SETTINGS_PROPERTY_PATH);
	public static final String OWNER_CONTACT_NUMBER = StringUtil.getResourceBundleValue("OWNER_CONTACT_NUMBER", SETTINGS_PROPERTY_PATH);
	public static final String OWNER_WEBSITE = StringUtil.getResourceBundleValue("OWNER_WEBSITE", SETTINGS_PROPERTY_PATH);
	public static final String OWNER_LOGO = StringUtil.getResourceBundleValue("OWNER_LOGO", SETTINGS_PROPERTY_PATH);
	public static final String OWNER_PRIMARY_COLOR = StringUtil.getResourceBundleValue("OWNER_PRIMARY_COLOR", SETTINGS_PROPERTY_PATH);
	public static final String[] OWNER_SMS_GATEWAY_LIST = StringUtil.getResourceBundleValue("OWNER_SMS_GATEWAY_LIST", SETTINGS_PROPERTY_PATH).split("~");
	
	public static final String FACEKEEPER_MAIL_SMTP_HOST = StringUtil.getResourceBundleValue("FACEKEEPER_MAIL_SMTP_HOST", SETTINGS_PROPERTY_PATH);
	public static final String FACEKEEPER_MAIL_SMTP_SOCKETFACTORY_PORT = StringUtil.getResourceBundleValue("FACEKEEPER_MAIL_SMTP_SOCKETFACTORY_PORT", SETTINGS_PROPERTY_PATH);
	public static final String FACEKEEPER_MAIL_SMTP_SOCKETFACTORY_CLASS = StringUtil.getResourceBundleValue("FACEKEEPER_MAIL_SMTP_SOCKETFACTORY_CLASS", SETTINGS_PROPERTY_PATH);
	public static final String FACEKEEPER_MAIL_SMTP_AUTH = StringUtil.getResourceBundleValue("FACEKEEPER_MAIL_SMTP_AUTH", SETTINGS_PROPERTY_PATH);
	public static final String FACEKEEPER_MAIL_SMTP_PORT = StringUtil.getResourceBundleValue("FACEKEEPER_MAIL_SMTP_PORT", SETTINGS_PROPERTY_PATH);
	
	public static final String FACEKEEPER_LOCATION = StringUtil.getResourceBundleValue("FACEKEEPER_LOCATION", SETTINGS_PROPERTY_PATH);
	public static final String FACEKEEPER_GUEST_RFID = StringUtil.getResourceBundleValue("FACEKEEPER_GUEST_RFID", SETTINGS_PROPERTY_PATH);
}
