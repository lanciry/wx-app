package com.wx.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 读取配置文件
 * @author meiiy
 * @version Apr 4, 2016
 */
public class ResourceUtil
{
    private static final String FILE_PATH = "/application.properties";
    
    private static final Logger logger = Logger.getLogger(ResourceUtil.class);
    private static final Properties props = new Properties();
    
    private ResourceUtil(){}
    
    static
    {
    	InputStream in = new BufferedInputStream(ResourceUtil.class.getResourceAsStream(FILE_PATH));
        try {
            props.load(in);
        } catch (IOException e) {
            logger.error(e.getMessage(), e) ;
        }
    }
    public static String getProPerties(String key) {
        return props.getProperty(key);
    }
}
