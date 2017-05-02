package com.wx.service.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wx.dao.statistics.WechatStatisticsDao;
import com.wx.model.statistics.WechatActionStatistics;
import com.wx.model.statistics.WechatShareStatistics;

/**
 * 微信活动分享，响应统计业务逻辑处理
 * @author meiiy
 * @version 2017年3月24日
 */
@Service
public class WechatStatisticsService
{
    @Autowired
    private WechatStatisticsDao wechatStatisticsDao;
    
    /**
     * 保存分享数据
     * @param shareStatistics 分享数据实体
     * @author meiiy
     * @version 2017年3月24日
     */
    public void insertShare(WechatShareStatistics shareStatistics)
    {
        wechatStatisticsDao.insertShare(shareStatistics);
    }
    
    /**
     * 保存响应数据
     * @param actionStatistics 响应数据实体
     * @author meiiy
     * @version 2017年3月24日
     */
    public void insertAction(WechatActionStatistics actionStatistics)
    {
        wechatStatisticsDao.insertAction(actionStatistics);
    }
}
