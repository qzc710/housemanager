package com.tn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tn.entity.Type;
import com.tn.entity.TypeExample;
import com.tn.mapper.TypeMapper;
import com.tn.service.TypeService;
import com.tn.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;


    public PageInfo<Type> getTypeByPage(String name, PageParam pageParam) {
        PageHelper.startPage(pageParam.getPage(),pageParam.getRows());
        TypeExample example=new TypeExample();
        TypeExample.Criteria criteria = example.createCriteria();
        if(name!=null){
            criteria.andNameLike("%"+name+"%");
        }
        List<Type> TypeList = typeMapper.selectByExample(example);

        PageInfo<Type> pageInfo= new PageInfo<Type>(TypeList);
        return pageInfo;
    }


    public boolean addType(Type Type) {
        return typeMapper.insertSelective(Type)>0;
    }

    public boolean updateType(Type Type) {
        return typeMapper.updateByPrimaryKeySelective(Type)>0;
    }

    public boolean deleteTypeById(Integer id) {
        return typeMapper.deleteByPrimaryKey(id)>0;
    }

    public boolean deleteTypeList(Integer[] ids) {
        TypeExample example=new TypeExample();
        TypeExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        return typeMapper.deleteByExample(example)>0;
    }

    public Type getTypeById(Integer id) {
        return  typeMapper.selectByPrimaryKey(id);
    }

    public List<Type> getAllType() {
        return typeMapper.selectByExample(null);
    }
}

