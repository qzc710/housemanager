package com.tn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tn.entity.House;
import com.tn.entity.HouseExample;
import com.tn.mapper.HouseMapper;
import com.tn.mapper.StreetMapper;
import com.tn.mapper.TypeMapper;
import com.tn.service.HouseService;
import com.tn.utils.CheckParam;
import com.tn.utils.HouseParam;
import com.tn.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private StreetMapper streetMapper;
    @Autowired
    private TypeMapper typeMapper;

    //查看是否通过审核的用户信息
    public PageInfo<House> getHouseIsPass(Integer ispass, CheckParam checkParam) {
        PageHelper.startPage(checkParam.getPage(), checkParam.getRows());
        List<House> list = houseMapper.getHouseIsPass(ispass, checkParam);
        PageInfo<House> pageInfo = new PageInfo<House>(list, 4);
        return pageInfo;
    }

    //添加用户
    public boolean addHouse(House house) {
        return houseMapper.insertSelective(house) > 0;
    }

    //审查房屋信息
    public boolean isPassCheck(Integer ispass, String id) {
        House house = houseMapper.selectByPrimaryKey(id);
        house.setIspass(ispass);
        return houseMapper.updateByPrimaryKeySelective(house) > 0;
    }

    //根据用户id分页查询所有用户信息
    public PageInfo<House> getHouseByUid(Integer id, PageParam pageParam) {

        PageHelper.startPage(pageParam.getPage(), pageParam.getRows());
        List<House> houseList = houseMapper.getHouseByUid(id);
        PageInfo<House> pageInfo = new PageInfo<House>(houseList, 3);
        return pageInfo;
    }

    //修改房屋信息
    public boolean updateHouse(House house) {

        return houseMapper.updateByPrimaryKeySelective(house) > 0;
    }

    //查询单条房屋信息
    public House getHouseById(String id) {
        return houseMapper.selectByPrimaryKey(id);
    }

    //删除房屋信息
    public boolean deleteHouse(String id) {
        House house = houseMapper.selectByPrimaryKey(id);
        house.setIsdel(1);
        return houseMapper.updateByPrimaryKeySelective(house) > 0;
    }

    //批量审核
    public boolean isPassMoreCheck(Integer ispass, String[] ids) {
        HouseExample houseExample = new HouseExample();
        HouseExample.Criteria criteria = houseExample.createCriteria();
        boolean flag = true;
        for (String id : ids) {
            House house = new House();
            house.setId(id);
            house.setIspass(ispass);
            int i = houseMapper.updateByPrimaryKeySelective(house);
            if (i < 0) {
                flag = false;
            }
        }
        return flag;
    }

    //待条件的分页,获取房屋信息
    public PageInfo<House> getHouseByPass(HouseParam houseParam) {
        PageHelper.startPage(houseParam.getPage(),houseParam.getRows());

        List<House> houseList = houseMapper.getHouseByPass(houseParam);

        PageInfo<House> pageInfo = new PageInfo<House>(houseList);
        return pageInfo;
    }
}
