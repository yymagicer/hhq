package com.hhq.gateway.util;

import java.lang.reflect.Field;

/**
 * 系统工具类
 */
public class SystemUtil {

    /**
     * 把文件加载到系统环境中
     * @param s
     */
    public static void addDirToPath(String s){
        try {
            //获取系统path变量对象
            Field field=ClassLoader.class.getDeclaredField("sys_paths");
            //设置此变量对象可访问
            field.setAccessible(true);
            //获取此变量对象的值
            String[] path=(String[])field.get(null);
            //创建字符串数组，在原来的数组长度上增加一个，用于存放增加的目录
            String[] tem=new String[path.length+1];
            //将原来的path变量复制到tem中
            System.arraycopy(path,0,tem,0,path.length);
            //将增加的目录存入新的变量数组中
            tem[path.length]=s;
            //将增加目录后的数组赋给path变量对象
            field.set(null,tem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
