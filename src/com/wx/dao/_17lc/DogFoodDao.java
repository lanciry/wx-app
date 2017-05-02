package com.wx.dao._17lc;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.wx.dao.base.BaseDao;
import com.wx.model._17lc.DogfoodAssistance;
import com.wx.model._17lc.DogfoodConversion;
import com.wx.model._17lc.DogfoodProducing;
import com.wx.model._17lc.DogfoodQrcode;
import com.wx.model._17lc.DogfoodWithdraw;

/**
 * 刷狗粮活动数据保存类
 * @author meiiy
 * @version 2017年3月24日
 */
@Repository
public class DogFoodDao extends BaseDao
{
    /**
     * 更新所有未兑换狗粮状态为已兑换
     * @param openid 兑换人openid
     * @author meiiy
     * @version 2017年3月25日
     */
    public void updateConversionStatus(String openid)
    {
        sqlSession.update("dogfood.updateConversionStatus", openid);
    }
    
    /**
     * 插入狗粮兑换记录
     * @param dogfoodConversion
     * @author meiiy
     * @version 2017年3月25日
     */
    public void insertConversionRecord(DogfoodConversion dogfoodConversion)
    {
        sqlSession.insert("dogfood.insertConversionRecord", dogfoodConversion);
    }
    
    /**
     * 获取提现人不是本次提现提现手机号码的记录
     * @param dogfoodWithdraw
     * @return
     * @author meiiy
     * @version 2017年3月25日
     */
    public Integer getWithdrawOfOpenid(DogfoodWithdraw dogfoodWithdraw)
    {
        return sqlSession.selectOne("dogfood.getWithdrawOfOpenid",dogfoodWithdraw);
    }
    
    /**
     * 获取提现手机号码不是本次提现人的记录
     * @param dogfoodWithdraw
     * @return
     * @author meiiy
     * @version 2017年3月25日
     */
    public Integer getWithdrawOfMobile(DogfoodWithdraw dogfoodWithdraw)
    {
        return sqlSession.selectOne("dogfood.getWithdrawOfMobile",dogfoodWithdraw);
    }
    
    /**
     * 根据订单号查询提现记录
     * @param orderId 订单号
     * @return
     * @author meiiy
     * @version 2017年4月7日
     */
    public DogfoodWithdraw findWithdrawByOrderId(String orderId)
    {
        return sqlSession.selectOne("dogfood.findWithdrawByOrderId",orderId);
    }
    
    /**
     * 更新所有未兑换狗粮状态为已兑换
     * @param openid 兑换人openid
     * @author meiiy
     * @version 2017年3月25日
     */
    public void updateCashStatus(String openid)
    {
        sqlSession.update("dogfood.updateCashStatus", openid);
    }
    
    /**
     * 插入提现记录
     * @param dogfoodWithdraw
     * @author meiiy
     * @version 2017年3月25日
     */
    public void insertWithdraw(DogfoodWithdraw dogfoodWithdraw)
    {
        sqlSession.insert("dogfood.insertWithdraw", dogfoodWithdraw);
    }
    
    /**
     * 更新提现记录
     * @param dogfoodWithdraw
     * @author meiiy
     * @version 2017年3月27日
     */
    public void updateWithdraw(DogfoodWithdraw dogfoodWithdraw)
    {
        sqlSession.update("dogfood.updateWithdraw", dogfoodWithdraw);
    }
    
    /**
     * 回调通知更新提现记录
     * @param dogfoodWithdraw
     * @author meiiy
     * @version 2017年4月7日
     */
    public void updateWithdrawForNotify(DogfoodWithdraw dogfoodWithdraw)
    {
        sqlSession.update("dogfood.updateWithdrawForNotify", dogfoodWithdraw);
    }
    
    /**
     * 插入刷狗粮数据
     * @param dogfoodProducing
     * @author meiiy
     * @version 2017年3月25日
     */
    public void insertProducing(DogfoodProducing dogfoodProducing)
    {
        sqlSession.insert("dogfood.insertProducing", dogfoodProducing);
    }
    
    /**
     * 获取狗粮数目
     * @param dogfoodProducing 查询条件
     * @return
     * @author meiiy
     * @version 2017年3月24日
     */
    public Integer sumDodFood(DogfoodProducing dogfoodProducing)
    {
        return sqlSession.selectOne("dogfood.sumDodFood",dogfoodProducing);
    }
    
    /**
     * 获取红包金额
     * @param dogfoodConversion 查询条件
     * @return
     * @author meiiy
     * @version 2017年3月24日
     */
    public BigDecimal sumRedAmount(DogfoodConversion dogfoodConversion)
    {
        return sqlSession.selectOne("dogfood.sumRedAmount",dogfoodConversion);
    }
    
    /**
     * 返回助力好友列表
     * @param openid 分享人openid
     * @return
     * @author meiiy
     * @version 2017年3月24日
     */
    public List<DogfoodAssistance> getAssistanceList(String openid)
    {
        return sqlSession.selectList("dogfood.getAssistanceList",openid);
    }
    
    /**
     * 获取用户参与活动的天数以及最后一次参与活动的日期
     * @param openid 用户openid
     * @return 
     * @author meiiy
     * @version 2017年3月24日
     */
    public List<DogfoodProducing> countNumForDay(String openid)
    {
        return sqlSession.selectList("dogfood.countNumForDay",openid);
    }
    
    /**
     * 获取用户刷狗粮的次数
     * @param dogfoodProducing 查询条件
     * @return 
     * @author meiiy
     * @version 2017年3月24日
     */
    public Integer countNum(DogfoodProducing dogfoodProducing)
    {
        return sqlSession.selectOne("dogfood.countNum",dogfoodProducing);
    }
    
    
    /**
     * 获取用户每一天刷狗粮的次数
     * @param openid 用户openid
     * @return 
     * @author meiiy
     * @version 2017年3月25日
     */
    public List<DogfoodProducing> countNumEveryDay(String openid)
    {
        return sqlSession.selectList("dogfood.countNumEveryDay",openid);
    }
    
    /**
     * 判断用户是否已经助力
     * @param openid
     * @return
     */
    public Long getAssistanceNumByOpenid(String openid){
        return sqlSession.selectOne("dogfood.getAssistanceNumByOpenid", openid);
    }
    
    /**
     * 保存助力记录
     * @param dogfoodAssistance
     * @return
     */
    public Long saveAssistance(DogfoodAssistance dogfoodAssistance){
        return sqlSession.selectOne("dogfood.saveAssistance", dogfoodAssistance);
    }
    
    /**
     * 保存带有OpenID参数的的ticket
     * @param dogfoodQrcode
     * @return
     */
    public Long saveQrcode(DogfoodQrcode dogfoodQrcode){
        return sqlSession.selectOne("dogfood.saveQrcode", dogfoodQrcode);
    }
    
    /**
     * 根据OpenID获取二维码的ticket
     * @param openid
     * @return
     */
    public DogfoodQrcode getQrcodeByOpenid(String openid){
        return sqlSession.selectOne("dogfood.getQrcodeByOpenid", openid);
    }
    
    /**
     * 查询当天提现次数
     * @param dogfoodWithdraw
     * @return
     * @author meiiy
     * @version 2017年4月11日
     */
    public Integer countWithdrawNum(DogfoodWithdraw dogfoodWithdraw)
    {
        return sqlSession.selectOne("dogfood.countWithdrawNum",dogfoodWithdraw);
    }
}
