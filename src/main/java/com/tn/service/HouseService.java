package com.tn.service;

import com.github.pagehelper.PageInfo;
import com.tn.entity.House;
import com.tn.utils.CheckParam;
import com.tn.utils.HouseParam;
import com.tn.utils.PageParam;

public interface HouseService {

    //查询是否通过审查的房屋信息
    PageInfo<House> getHouseIsPass(Integer ispass, CheckParam checkParam);

    //添加房屋信息
    boolean addHouse(House house);

    //审查房屋信息
    boolean isPassCheck(Integer ispass,String id);

    //通过用户id分页查询所有房屋信息
    PageInfo<House> getHouseByUid(Integer id, PageParam pageParam);
    //修改房屋信息
    boolean updateHouse(House house);
    //通过id得到房屋信息
    House getHouseById(String id);
    //删除房屋信息
    boolean deleteHouse(String id);

    //是否审核多条
    boolean  isPassMoreCheck(Integer ispass,String [] ids);
    //通过审核的房屋信息
    PageInfo<House> getHouseByPass(HouseParam houseParam);
}
