package com.wx.service._17lc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wx.dao._17lc.DogFoodDao;
import com.wx.model._17lc.DogfoodWithdraw;

@Service
public class DogFoodWithdrowService
{
    @Autowired
    private DogFoodDao dogFoodDao;
    
    /**
     * 保存提现记录
     * 新开启事物
     * @param dogfoodWithdraw
     * @author meiiy
     * @version 2017年3月27日
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertWithdraw(DogfoodWithdraw dogfoodWithdraw)
    {
        dogFoodDao.insertWithdraw(dogfoodWithdraw);
    }
}
