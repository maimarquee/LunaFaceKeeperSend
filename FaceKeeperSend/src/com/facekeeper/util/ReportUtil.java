package com.facekeeper.util;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.facekeeper.dao.FaceLogDAO;
import com.facekeeper.dao.UserRFIDDAO;
import com.facekeeper.dto.FaceLogDTO;
import com.facekeeper.dto.UserRFIDDTO;
import com.mytechnopal.DTOBase;
import com.mytechnopal.Email;
import com.mytechnopal.util.DateTimeUtil;
import com.mytechnopal.util.EmailUtil;
import com.mytechnopal.util.StringUtil;

public class ReportUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	//public static void email(Date timeLogFrom, Date timeLogTo){
	public static void main(String[] param) {
		StringBuffer strBuff = new StringBuffer();
		Email email = new Email();
		List<DTOBase> userRFIDList = new UserRFIDDAO().getUserRFIDList();
		Date timeLogFrom = DateTimeUtil.getStrToDateTime(param[0], "yyyy-MM-dd");
		Date timeLogTo = DateTimeUtil.getStrToDateTime(param[1], "yyyy-MM-dd");
		try {
			email.setReceiverTo(new InternetAddress(param[2]));
			email.setReceiverBCC(new InternetAddress(param[3]));
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<DTOBase> faceLogList = new FaceLogDAO().getFaceLogListByTimeLogFromAndTimeLogTo(timeLogFrom, timeLogTo);
		strBuff.append("<html>");
		strBuff.append("	<head>");
		strBuff.append("		<style>");
		strBuff.append("			table {");
		strBuff.append("    			font-family: arial, sans-serif;");
		strBuff.append("    			border-collapse: collapse;");
		strBuff.append("    			width: 100%;");
		strBuff.append("			}");
		strBuff.append("			td, th {");
		strBuff.append("    			border: 1px solid #f5f5f5;");
		strBuff.append("    			text-align: left;");
		strBuff.append("    			padding: 8px;");
		strBuff.append("			}");
		strBuff.append("		</style>");
		strBuff.append("	</head>");
		strBuff.append("<body>");
		strBuff.append("	<table border='1px solid black'>");
		int numberOfColumns = 3;
		int columnCtr = 1;
		for(DTOBase userRFIDObj: userRFIDList) {
			UserRFIDDTO userRFID = (UserRFIDDTO) userRFIDObj;
			List<DTOBase> faceLogListByUser = FaceLogUtil.getFaceLogListByUserCode(faceLogList, userRFID.getUserCode());
			List<DTOBase> faceLogListIn = FaceLogUtil.getFaceLogListByEntry(faceLogListByUser, true);
			List<DTOBase> faceLogListOut = FaceLogUtil.getFaceLogListByEntry(faceLogListByUser, false);		
			int numberOfColumnsByUser = faceLogListIn.size() > faceLogListOut.size() ? faceLogListIn.size() : faceLogListOut.size();
			if(columnCtr == 1) {
				strBuff.append("<tr>");
			}
			strBuff.append("		<td>");
			strBuff.append("			<table>");
			strBuff.append("				<tr>");
			strBuff.append("					<td>");
			strBuff.append(							StringUtil.getFullName(userRFID.getLastName(), userRFID.getFirstName(), userRFID.getMiddleName(), userRFID.getSuffixName(), false, true) + " | " + userRFID.getUserCode());
			strBuff.append("					</td>");
			strBuff.append("				</tr>");
			strBuff.append("				<tr>");
			strBuff.append("					<td>");
			if(numberOfColumnsByUser == 0) {
				strBuff.append("					<font color='red'>ABSENT</font>");
			}
			else {
				strBuff.append("					<table>");
				strBuff.append("						<tr>");
				strBuff.append("							<td style='background: green'>");
				strBuff.append("								<b>IN</b>");
				strBuff.append("							</td>");
				for(int i=1; i<=numberOfColumnsByUser; i++) {
					String strTime = "";
					if(faceLogListIn.size() >= 1 && faceLogListIn.size() >= i) {
						FaceLogDTO faceLog = (FaceLogDTO) faceLogListIn.get(i-1);
						strTime = DateTimeUtil.getDateTimeToStr(faceLog.getTimeLog(), "hh:mm a");
					}
					strBuff.append("						<td style='background: green'>");
					strBuff.append(								strTime);
					strBuff.append("						</td>");
				}
				strBuff.append("						</tr>");
				strBuff.append("						<tr>");
				strBuff.append("							<td style='background: blue'>");
				strBuff.append("								<b>OUT</b>");
				strBuff.append("							</td>");
				for(int i=1; i<=numberOfColumnsByUser; i++) {
					String strTime = "";
					if(faceLogListOut.size() >= 1 && faceLogListOut.size() >= i) {
						FaceLogDTO faceLog = (FaceLogDTO) faceLogListOut.get(i-1);
						strTime = DateTimeUtil.getDateTimeToStr(faceLog.getTimeLog(), "hh:mm a");
					}
					strBuff.append("						<td style='background: blue'>");
					strBuff.append(								strTime);
					strBuff.append("						</td>");
				}
				strBuff.append("						<tr>");
				strBuff.append("					</table>");
			}
			strBuff.append("					</td>");
			strBuff.append("				</tr>");
			strBuff.append("			</table>");
			strBuff.append("		</td>");
			columnCtr++;
			if(columnCtr > numberOfColumns) {
				columnCtr = 1;
				strBuff.append("</tr>");
			}
		}
		strBuff.append("	</table>");
		strBuff.append("</body>");
		strBuff.append("</html>");
		
		Properties properties = new Properties();  
		properties.put("mail.smtp.host", SettingsUtil.FACEKEEPER_MAIL_SMTP_HOST);  
		properties.put("mail.smtp.socketFactory.port", SettingsUtil.FACEKEEPER_MAIL_SMTP_SOCKETFACTORY_PORT);  
		properties.put("mail.smtp.socketFactory.class", SettingsUtil.FACEKEEPER_MAIL_SMTP_SOCKETFACTORY_CLASS);  
		properties.put("mail.smtp.auth", SettingsUtil.FACEKEEPER_MAIL_SMTP_AUTH);  
		properties.put("mail.smtp.port", SettingsUtil.FACEKEEPER_MAIL_SMTP_PORT);  
		
		email.setSubject("FaceKeeper Report as of : " + DateTimeUtil.getDateTimeToStr(DateTimeUtil.getCurrentTimestamp(), "MMM dd, yyyy hh:mm a") );
		email.setProperties(properties);
		email.setSender("technopalfacekeeper@gmail.com");
		email.setSenderPassword("technopalsoftware");
		
		String[][] messageArr = new String[1][2]; //one row, two columns
		messageArr[0][0] = email.MESSAGE_CONTENT_TYPE_HTML;
		messageArr[0][1] = strBuff.toString();
		email.setMessageArr(messageArr);
		System.out.println("Is email sent successfully: " + EmailUtil.sendEMail(email));
	}
}
