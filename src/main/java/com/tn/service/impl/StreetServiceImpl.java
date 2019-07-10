package com.tn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tn.entity.District;
import com.tn.entity.Street;
import com.tn.entity.StreetExample;
import com.tn.mapper.DistrictMapper;
import com.tn.mapper.StreetMapper;
import com.tn.service.StreetService;
import com.tn.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class StreetServiceImpl implements StreetService {

    @Autowired
    private StreetMapper streetMapper;
    @Autowired
    private DistrictMapper districtMapper;

    public PageInfo<Street> getStreetByPage(String name, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();

        if(name!=null){
            criteria.andNameLike("%"+name+"%");
        }
        List<Street> streetList = streetMapper.selectByExample(example);
        for(Street s:streetList){
            District district = districtMapper.selectByPrimaryKey(s.getDistrictId());
            s.setDistrictName(district.getName());
        }
        PageInfo<Street> pageInfo= new PageInfo<Street>(streetList);
        return pageInfo;
    }

    public PageInfo<Street> getStreetByDid(Integer id, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(id);
        List<Street> streetList = streetMapper.selectByExample(example);
        for(Street s:streetList){
            District district = districtMapper.selectByPrimaryKey(s.getDistrictId());
            s.setDistrictName(district.getName());
        }
        PageInfo<Street> pageInfo= new PageInfo<Street>(streetList);
        return pageInfo;
    }

    public boolean addStreet(Street Street) {
        return streetMapper.insertSelective(Street)>0;
    }

    public boolean updateStreet(Street Street) {
        return streetMapper.updateByPrimaryKeySelective(Street)>0;
    }

    public boolean deleteStreetById(Integer id) {
        return streetMapper.deleteByPrimaryKey(id)>0;
    }

    public boolean deleteStreetList(Integer[] ids) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));

        return streetMapper.deleteByExample(example)>0;
    }

    public boolean deleteStreetByDisId(Integer id) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(id);
        return  streetMapper.deleteByExample(example)>0;
    }

    public Street getStreetById(Integer id) {

        Street street = streetMapper.selectByPrimaryKey(id);
        District district = districtMapper.selectByPrimaryKey(street.getDistrictId());
        street.setDistrictName(district.getName());
        return street;
    }

    public List<Street> getAllStreetByDid(Integer id) {
        StreetExample example=new StreetExample();
        StreetExample.Criteria criteria = example.createCriteria();
        criteria.andDistrictIdEqualTo(id);
        List<Street> streetList = streetMapper.selectByExample(example);
        return streetList;
    }

    public List<Street> getAllStreet() {
        return streetMapper.selectByExample(null);
    }
}

