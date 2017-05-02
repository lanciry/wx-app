package com.wx.model._17lc;

import java.io.Serializable;

import com.wx.model.base.BaseModel;

/**
 * 助力实体
 * @author meiiy
 * @version 2017年3月24日
 */
public class DogfoodAssistance extends BaseModel implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String inviterOpenid;
    private String assistorOpenid;
    private String assistorNickname;
    private String assistorHeadimg;
    
    public String getInviterOpenid() {
        return inviterOpenid;
    }
    public void setInviterOpenid(String inviterOpenid) {
        this.inviterOpenid = inviterOpenid;
    }
    public String getAssistorOpenid() {
        return assistorOpenid;
    }
    public void setAssistorOpenid(String assistorOpenid) {
        this.assistorOpenid = assistorOpenid;
    }
    public String getAssistorNickname() {
        return assistorNickname;
    }
    public void setAssistorNickname(String assistorNickname) {
        this.assistorNickname = assistorNickname;
    }
    public String getAssistorHeadimg() {
        return assistorHeadimg;
    }
    public void setAssistorHeadimg(String assistorHeadimg) {
        this.assistorHeadimg = assistorHeadimg;
    }
}
