package com.tn.service;

import com.github.pagehelper.PageInfo;
import com.tn.entity.Type;
import com.tn.utils.PageParam;

import java.util.List;

public interface TypeService {

    PageInfo<Type> getTypeByPage(String name, PageParam pageParam);
    boolean addType(Type type);
    boolean updateType(Type type);
    boolean deleteTypeById(Integer id);
    boolean deleteTypeList(Integer[] ids);
    Type getTypeById(Integer id);
    List<Type> getAllType();
}
