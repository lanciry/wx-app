package com.wx.controller.weixin.model;

/**
 * 回复音频文件类型消息
 * @author meiiy
 * @version Apr 4, 2016
 */
public class MusicMessageResp extends BaseMessageResp
{
	// 音乐
    private Music Music;

    public Music getMusic() {
            return Music;
    }

    public void setMusic(Music music) {
            Music = music;
    }
}
