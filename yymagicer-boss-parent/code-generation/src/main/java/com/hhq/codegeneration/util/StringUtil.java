package com.hhq.codegeneration.util;

public class StringUtil {
	private StringUtil(){}
	
	/**
	 * Description: 首字母小写
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	/** 
	* 首字母大写 
	*  
	* @param
	* @return 
	*/  
	public static String firstCharacterToUpper(String s) {  
	   return s.substring(0, 1).toUpperCase() + s.substring(1);  
	}  
	/** 
	* 替换字符串并让它的下一个字母为大写 
	* @param srcStr 
	* @param org 
	* @param ob 
	* @return 
	*/  
	public static String replaceUnderlineAndfirstToUpper(String srcStr,String org,String ob)  
	{  
	   String newString = "";  
	   int first=0;  
	   while(srcStr.indexOf(org)!=-1)  
	   {  
	    first=srcStr.indexOf(org);  
	    if(first!=srcStr.length())  
	    {  
	     newString=newString+srcStr.substring(0,first)+ob;  
	     srcStr=srcStr.substring(first+org.length(),srcStr.length());  
	     srcStr=firstCharacterToUpper(srcStr);  
	    }  
	   }  
	   newString=newString+srcStr;  
	   return newString;  
	} 
}
