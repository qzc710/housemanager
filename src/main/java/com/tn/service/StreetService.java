package com.tn.service;

import com.github.pagehelper.PageInfo;
import com.tn.entity.Street;
import com.tn.utils.PageParam;

import java.util.List;

public interface StreetService {

    PageInfo<Street> getStreetByPage(String name, PageParam pageParam);
    PageInfo<Street> getStreetByDid(Integer id,PageParam pageParam);
    boolean addStreet(Street street);
    boolean updateStreet(Street street);
    boolean deleteStreetById(Integer id);
    boolean deleteStreetList(Integer[] ids);
    boolean deleteStreetByDisId(Integer id);
    Street getStreetById(Integer id);
    List<Street> getAllStreetByDid(Integer id);
    List<Street> getAllStreet();
}
