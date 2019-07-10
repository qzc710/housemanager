package com.tn.service;

import com.github.pagehelper.PageInfo;
import com.tn.entity.District;
import com.tn.utils.PageParam;

import java.util.List;

public interface DistrictService {

    PageInfo<District> getDistrictByPage(String name, PageParam pageParam);

    boolean addDistrict(District district);
    boolean updateDistrict(District district);
    boolean deleteDistrict(Integer id);
    boolean deleteDistrictList(Integer[] ids);
    District getDistrictById(Integer id);
    List<District> getDistrictList();
}
