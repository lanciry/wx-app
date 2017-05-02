package com.wx.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.wx.common.cache.AccessTokenCache;
import com.wx.common.cache.JsapiTicketCache;
import com.wx.common.constant.Constant;
import com.wx.controller.weixin.model.Menu;
import com.wx.model.AccessToken;

import net.sf.json.JSONObject;

/**
 * 微信相关处理
 * @author meiiy
 * @version Apr 4, 2016
 */
public class WeixinUtil
{
	private static Logger logger = Logger.getLogger(WeixinUtil.class);
	
	// 创建菜单url
	private static final String MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token={0}";
	// 获取access_token url
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
	//网页授权url
	private static final String OAUTH2_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
	//获取用户信息
	private static final String GET_USER_INFO ="https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}&lang=zh_CN";
	//发送客服消息
	private static final String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={0}";
	//获取JS API ticket url
	private static final String JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token={0}&type=jsapi";
	//获取多媒体文件
	private static final String GET_MEDIA = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token={0}&media_id={1}";
	//发送模板消息
	private static final String SEND_TEMPLETE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token={0}";
	//关注公众号标示
	public static final String SUBSCRIBE = "1";
	//临时二维码
    private static final String QR_SCENE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={0}";
	
	/**
	 * 获取全局ACCESSTOKEN
	 * @return
	 */
    public static String getACCESSTOKEN()
    {
    	try {
    		String access_token = "";
    		AccessToken accessToken = AccessTokenCache.getCache();
    		
    		if(accessToken != null && StringUtils.isNotEmpty(accessToken.getToken())){
    			Date date = accessToken.getDate();
    			int expiresIn = accessToken.getExpiresIn();
    			
    			if(System.currentTimeMillis() > (date.getTime() + (expiresIn-5) * 1000)){//有效时间减去5秒
    				String url =  MessageFormat.format(ACCESS_TOKEN_URL, Constant.WX_APPID,Constant.WX_APP_SECRET);
    				JSONObject returnJson = httpsRequest(url,"GET",null);
    				
        			if (returnJson != null) {
        				access_token = returnJson.getString("access_token");
        				int newExpiresIn = returnJson.getInt("expires_in");
        				Date newDate = new Date();
        				
        				accessToken = new AccessToken();
        				accessToken.setDate(newDate);
        				accessToken.setToken(access_token);
        				accessToken.setExpiresIn(newExpiresIn);
        				//放入缓存
        				AccessTokenCache.putAccessToken(accessToken);
        				logger.info(">>>>>>>>>>>>>>>失效重新获取全局access_token："+access_token);
        				//更新至数据库
        				//AccessTokenCache.updateTokenToDB(accessToken.getId(),access_token,newExpiresIn,newDate);
        			}
    			} else {
    				access_token = accessToken.getToken();
    				logger.info(">>>>>>>>>>>>>>>缓存获取全局access_token："+access_token);
    			}
    		} else {
    			String url =  MessageFormat.format(ACCESS_TOKEN_URL, Constant.WX_APPID,Constant.WX_APP_SECRET);
				JSONObject returnJson = httpsRequest(url,"GET",null);
				
    			if(returnJson != null){
    				accessToken = new AccessToken();
    				
    				access_token = returnJson.getString("access_token");
    				int newExpiresIn = returnJson.getInt("expires_in");
    				Date newDate = new Date();
    				
    				accessToken.setToken(access_token);
    				accessToken.setExpiresIn(newExpiresIn);
    				accessToken.setDate(newDate);
    				//放入缓存
    				AccessTokenCache.putAccessToken(accessToken);
    				logger.info(">>>>>>>>>>>>.调用接口获取全局access_token："+access_token);
    				//保存至数据库
    				//AccessTokenCache.insertTokenToDB(access_token,newExpiresIn,newDate);
    			}
    		}
    		
    		return access_token;
		} catch (Exception e) {
			logger.error("getACCESSTOKEN（全局）出错:"+e.getMessage(),e);
		}
    	
    	return null;
	}
    
    /**
     * 创建菜单
     * @param menu
     * @return
     */
    public static int createMenu(Menu menu) 
    {
    	int result = 0;
    	
    	String url = MessageFormat.format(MENU_URL, getACCESSTOKEN());
    	
    	String jsonMenu = JSONObject.fromObject(menu).toString();
    	// 调用接口创建菜单
    	JSONObject jsonObject = httpsRequest(url, "POST", jsonMenu.toString());

    	if (null != jsonObject) {
    		if (0 != jsonObject.getInt("errcode")) {
    			result = jsonObject.getInt("errcode");
    			logger.info("创建菜单失败 errcode:{} errmsg:{}" + jsonObject.getInt("errcode") + jsonObject.getString("errmsg"));
    		}
    	}
    	return result;
    }
    
    /**
     * 授权
     * @param code
     * @return
     */
    public static JSONObject oauth2(String code)
    {
    	try {
    		String url = MessageFormat.format(OAUTH2_URL,Constant.WX_APPID, Constant.WX_APP_SECRET,code);
    		JSONObject jsonObject = httpsRequest(url, "GET", null);
    		logger.info(">>>>>>>>>>>>oauth2网页授权:"+jsonObject.toString());
    		if(jsonObject != null){
    			return jsonObject;
    		}
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>授权失败:"+e.getMessage(),e);
		}
    	return null;
	}
    
    /**
     * 获取用户信息
     * @param openid 用户唯一微信标示
     * @return
     */
    public static JSONObject getUserInfo(String openid)
    {
    	try {
    		String url = MessageFormat.format(GET_USER_INFO, getACCESSTOKEN(),openid);
    		JSONObject jsonObject = httpsRequest(url, "GET", null);
    		logger.info(">>>>>>>>>>>>getUserInfo:"+jsonObject.toString());
    		if(jsonObject != null){
    			return jsonObject;
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    		logger.error(">>>>>>>>>>>>>getUserInfo出错:"+e.getMessage());
    	}
    	return null;
    }
    
    /**
     * 发送客服消息
     * @param json发送的内容
     * @return
     */
    public static JSONObject sendMessage(String json)
    {
    	try {
    		String url = MessageFormat.format(SEND_MESSAGE_URL, getACCESSTOKEN());
    		JSONObject jsonObject = httpsRequest(url, "POST", json);
    		logger.info(">>>>>>>>>>发送客服消息返回内容："+jsonObject.toString());
    		return jsonObject;
		} catch (Exception e) {
			logger.error(">>>>>>>>>>>>>>发送客服消息出错:"+e.getMessage(),e);
		}
    	return null;
	}
    
    /**
     * 获取使用网页JS api时所需的ticket
     * @param ACCESS_TOKEN
     * @return
     */
    public static String getJsapiTicket()
    {
    	try {
    		String ticket = "";
    		AccessToken accessToken = JsapiTicketCache.getCache();
    		
    		if (accessToken!=null &&  StringUtils.isNotEmpty(accessToken.getToken())) {
    			Date date = accessToken.getDate();
    			int expiresIn = accessToken.getExpiresIn();
    			if (System.currentTimeMillis() > (date.getTime() + (expiresIn-5) * 1000)) {//有效时间减去5秒
    				String url =  MessageFormat.format(JSAPI_TICKET_URL,getACCESSTOKEN());
        			JSONObject returnJson = httpsRequest(url,"GET",null);
        			
        			if (returnJson != null) {
        				accessToken = new AccessToken();
        				accessToken.setDate(new Date());
        				ticket = returnJson.getString("ticket");
        				accessToken.setToken(ticket);
        				accessToken.setExpiresIn(returnJson.getInt("expires_in"));
        				
        				JsapiTicketCache.putTicket(accessToken);
        				logger.info(">>>>>>>>>>>>>>失效重新获取jsapi_ticket："+ticket);
        			}
    			}else{
    				ticket = accessToken.getToken();
    				logger.info(">>>>>>>>>>>>>>缓存获取jsapi_ticket："+ticket);
    			}
    		} else {
    			String url =  MessageFormat.format(JSAPI_TICKET_URL,getACCESSTOKEN());
    			JSONObject returnJson = httpsRequest(url,"GET",null);
    			
    			if (returnJson != null) {
    				accessToken = new AccessToken();
    				accessToken.setDate(new Date());
    				ticket = returnJson.getString("ticket");
    				accessToken.setToken(ticket);
    				accessToken.setExpiresIn(returnJson.getInt("expires_in"));
    				JsapiTicketCache.putTicket(accessToken);
    				logger.info(">>>>>>>>>>>>>>接口获取jsapi_ticket："+ticket);
    			}
    		}
    		
    		return ticket;
		} catch (Exception e) {
			logger.error(">>>>>>>>>>getJsapiTicket出错:"+e.getMessage(),e);
		}
    	
    	return null;
	}
    
    /**
     * 生成随机字符串
     * @return
     */
    public static String create_nonce_str() 
    {
        return UUID.randomUUID().toString();
    }
    
    /**
     * 生成时间戳
     * @return
     */
    public static String create_timestamp() 
    {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
    
    /**
     * https请求
     * @param requestUrl 地址
     * @param requestMethod 请求方法
     * @param outputStr 请求参数
     * @return
     */
    public static JSONObject httpsRequest(String requestUrl,String requestMethod, String outputStr)
    {
    	JSONObject jsonObject = null;
        StringBuilder sb = new StringBuilder();
        try {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = { new X509TrustManager() {
            	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            	}

            	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            	}

            	public X509Certificate[] getAcceptedIssuers() {
            		return null;
            	}
            } };
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                    httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                    OutputStream outputStream = httpUrlConn.getOutputStream();
                    // 注意编码格式，防止中文乱码
                    outputStream.write(outputStr.getBytes("UTF-8"));
                    outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
            	sb.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(sb.toString());
        } catch (Exception e) {
        	logger.error("发送https请求出错：",e);
        }
        return jsonObject;
    }
    
    /**
     * http请求
     * @param requestUrl 地址
     * @param requestMethod 请求方法
     * @param outputStr 请求参数
     * @return
     */
    public static JSONObject httpRequest(String requestUrl,String requestMethod, String outputStr)
    {
        JSONObject jsonObject = null;
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                    httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                    OutputStream outputStream = httpUrlConn.getOutputStream();
                    // 注意编码格式，防止中文乱码
                    outputStream.write(outputStr.getBytes("UTF-8"));
                    outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                sb.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(sb.toString());
        } catch (Exception e) {
            logger.error("发送http请求出错：",e);
        }
        return jsonObject;
    }
    
    /**
     * 使用微信JS-SDK，生成签名信息
     * @return
     * @author meiiy
     * @version 2016年4月9日
     */
    public static String getSign(String jsapi_ticket, String noncestr, String timestamp, String url)
    {
    	String string1 = "jsapi_ticket=" + jsapi_ticket + 
    			"&noncestr=" + noncestr + 
    			"&timestamp=" + timestamp + 
    			"&url=" + url;
    	String signature = "";
    	 try {
             MessageDigest crypt = MessageDigest.getInstance("SHA-1");
             crypt.reset();
             crypt.update(string1.getBytes("UTF-8"));
             signature = byteToHex(crypt.digest());
         } catch (Exception e) {
             e.printStackTrace();
         }
    	 
    	return signature;
    }
    
    private static String byteToHex(final byte[] hash)
    {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
    /**
     * 从微信端获取文件
     * @param serverId 文件编号
     * @return
     * @throws Exception
     * @author meiiy
     * @version 2016年4月9日
     */
    public static InputStream getMedia(String serverId) throws Exception
    {
    	URL url = new URL(MessageFormat.format(GET_MEDIA,getACCESSTOKEN(),serverId));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		InputStream inputStream = conn.getInputStream();
		
		return inputStream;
    }
    
    /**
     * 发送模板消息
     * @version Apr 12, 2016
     * @param json 发送的数据
     */
    public static void sendTempleteMsg(String json)
    {
    	String url =  MessageFormat.format(SEND_TEMPLETE_URL,getACCESSTOKEN());
    	httpsRequest(url,"POST",json);
    }
    
    /**
     * 获取永久二维码的Ticket
     * @param accessToken
     */
    public static String getQrcodeTicket(String inviterOpenid){
    	String url = MessageFormat.format(QR_SCENE, getACCESSTOKEN());
    	String param = "{'expire_seconds': 604800, 'action_name': 'QR_LIMIT_STR_SCENE', 'action_info': {'scene': {'scene_str': '" + inviterOpenid + "'}}}";
    	String jsonParam = JSONObject.fromObject(param).toString();
    	JSONObject jsonObject = httpsRequest(url, "POST", jsonParam);
    	return jsonObject.getString("ticket");
    }
    
    public static void main(String[] args) {
	}
    
}
