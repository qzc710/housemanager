package com.tn.mapper;

import com.tn.entity.House;
import com.tn.entity.HouseExample;
import com.tn.utils.CheckParam;
import com.tn.utils.HouseParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int countByExample(HouseExample example);

    int deleteByExample(HouseExample example);

    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") House record, @Param("example") HouseExample example);

    int updateByExample(@Param("record") House record, @Param("example") HouseExample example);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    List<House> getHouseByUid(Integer id);

    List<House> getHouseIsPass(@Param("ispass") Integer ispass, @Param("checkParam") CheckParam checkParam);

    List<House> getHouseByPass(HouseParam houseParam);

}