package com.tn.controller;

import com.github.pagehelper.PageInfo;
import com.tn.entity.Type;
import com.tn.service.TypeService;
import com.tn.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin/")
public class TypeController {

    @Autowired
    private TypeService typeService;
   @RequestMapping("getTypeByPage")
   @ResponseBody
    public Map<String,Object> getTypeByPage(String name,PageParam pageParam){
       PageInfo<Type> pageInfo = typeService.getTypeByPage(name,pageParam);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
   }

    @RequestMapping("getTypeById")
    @ResponseBody
    public Type getTypeById(Integer id){

        Type type= typeService.getTypeById(id);

        return type;
    }

    @RequestMapping("addType")
    @ResponseBody
    public String getTypeByDid(Type Type){

        boolean result = typeService.addType(Type);

        return " {\"result\":"+result+"}";
    }
    @RequestMapping("updateType")
    @ResponseBody
    public String updateType(Type Type){

        boolean result = typeService.updateType(Type);

        return " {\"result\":"+result+"}";
    }
    @RequestMapping("deleteType")
    @ResponseBody
    public String deleteType(Integer id){

        boolean result = typeService.deleteTypeById(id);

        return " {\"result\":"+result+"}";
    }

    @RequestMapping("deleteTypeList")
    @ResponseBody
    public String deleteTypeList(Integer[] ids){

        boolean result = typeService.deleteTypeList(ids);

        return " {\"result\":"+result+"}";
    }
}
