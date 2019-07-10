package com.tn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tn.entity.District;
import com.tn.entity.DistrictExample;
import com.tn.entity.StreetExample;
import com.tn.mapper.DistrictMapper;
import com.tn.mapper.StreetMapper;
import com.tn.service.DistrictService;
import com.tn.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;

    //待条件分页查询
    public PageInfo<District> getDistrictByPage(String name, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        DistrictExample example=new DistrictExample();
        DistrictExample.Criteria criteria = example.createCriteria();
        if(name!=null){
            criteria.andNameLike("%"+name+"%");
        }
        List<District> districtList = districtMapper.selectByExample(example);
        PageInfo<District> pageInfo= new PageInfo<District>(districtList);
        return pageInfo;
    }

    //添加区域
    public boolean addDistrict(District district) {
        return districtMapper.insertSelective(district)>0;
    }
    //修改区域
    public boolean updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district)>0;
    }

    //删除区域的同时删除区域下的街道
    @Transactional
    public boolean deleteDistrict(Integer id) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(id);
        streetMapper.deleteByExample(example);
        districtMapper.deleteByPrimaryKey(id);
        return true;
    }

    //删除多条
    @Transactional
    public boolean deleteDistrictList(Integer[] ids) {

        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria streetCriteria = streetExample.createCriteria();
        streetCriteria.andDistrictIdIn(Arrays.asList(ids));
        streetMapper.deleteByExample(streetExample);

        DistrictExample example=new DistrictExample();
        DistrictExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        districtMapper.deleteByExample(example);
        return true;
    }

    //通过id得到区域
    public District getDistrictById(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    public List<District> getDistrictList() {
      return districtMapper.selectByExample(null);
    }
}
