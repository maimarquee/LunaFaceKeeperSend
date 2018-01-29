package com.facekeeper.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.facekeeper.dto.FaceLogDTO;
import com.mytechnopal.DTOBase;

public class FaceLogUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	//Get all face_log record list by user code
	public static List<DTOBase> getFaceLogListByUserCode(List<DTOBase> faceLogList, String userCode) {
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase faceLogObj: faceLogList) {
			FaceLogDTO faceLog = (FaceLogDTO) faceLogObj;
			if(faceLog.getUserRFID().getUserCode().equalsIgnoreCase(userCode)) {
				resultList.add(faceLog);
			}
		}
		return resultList;
	}
	
	//Get all face_log record list by in or out
	public static List<DTOBase> getFaceLogListByEntry(List<DTOBase> faceLogList, boolean isIn) {
		List<DTOBase> resultList = new ArrayList<DTOBase>();
		for(DTOBase faceLogObj: faceLogList) {
			FaceLogDTO faceLog = (FaceLogDTO) faceLogObj;
			if(faceLog.isIn() == isIn) {
				resultList.add(faceLog);
			}
		}
		return resultList;
	}
}
