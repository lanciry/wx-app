package com.wx.dao.statistics;

import org.springframework.stereotype.Repository;

import com.wx.dao.base.BaseDao;
import com.wx.model.statistics.WechatActionStatistics;
import com.wx.model.statistics.WechatShareStatistics;

/**
 * 微信活动分享，响应统计数据库操作
 * @author meiiy
 * @version 2017年3月24日
 */
@Repository
public class WechatStatisticsDao extends BaseDao
{
    /**
     * 插入分享数据
     * @param shareStatistics 分享数据实体
     * @author meiiy
     * @version 2017年3月24日
     */
    public void insertShare(WechatShareStatistics shareStatistics)
    {
        sqlSession.insert("wechatStatistics.insertShare", shareStatistics);
    }
    
    /**
     * 插入响应数据
     * @param actionStatistics 响应数据实体
     * @author meiiy
     * @version 2017年3月24日
     */
    public void insertAction(WechatActionStatistics actionStatistics)
    {
        sqlSession.insert("wechatStatistics.insertAction", actionStatistics);
    }
}
