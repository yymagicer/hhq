package com.hhq.aiapi.common;


import com.baidu.aip.face.AipFace;
import com.baidu.aip.imageclassify.AipImageClassify;
import com.baidu.aip.ocr.AipOcr;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.cn;

/**
 * 加载模块对象
 * @author
 *
 */
public class BDFactory {
	private static AipFace aipFace;
	private static AipOcr aipOcr;
	private static AipImageClassify aipImageClassify;
	private static AipAdded aipAdded;
	public static AipFace getAipFace(){
		if(aipFace==null){
			synchronized (AipFace.class) {
				if(aipFace==null){
					aipFace = new AipFace(AIConstant.BD_FACE_APPID, AIConstant.BD_FACE_APPKEY, AIConstant.BD_FACE_SECRETKEY);
				}
			}
		}
		return aipFace;
	}
	public static AipOcr getAipOcr(){
		if(aipOcr==null){
			synchronized (AipFace.class) {
				if(aipOcr==null){
					aipOcr = new AipOcr(AIConstant.BD_FACE_APPID, AIConstant.BD_FACE_APPKEY, AIConstant.BD_FACE_SECRETKEY);
				}
			}
		}
		return aipOcr;
	}
	public static AipImageClassify getAipImageClassify(){
		if(aipImageClassify==null){
			synchronized (AipFace.class) {
				if(aipImageClassify==null){
					aipImageClassify = new AipImageClassify(AIConstant.BD_FACE_APPID, AIConstant.BD_FACE_APPKEY, AIConstant.BD_FACE_SECRETKEY);
					// 可选：设置网络连接参数
					aipImageClassify.setConnectionTimeoutInMillis(2000);
					aipImageClassify.setSocketTimeoutInMillis(60000);
				}
			}
		}
		return aipImageClassify;
	}
}
