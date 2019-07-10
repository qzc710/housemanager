package com.tn.controller.page;

import com.github.pagehelper.PageInfo;
import com.tn.entity.*;
import com.tn.service.DistrictService;
import com.tn.service.HouseService;
import com.tn.service.StreetService;
import com.tn.service.TypeService;
import com.tn.utils.HouseParam;
import com.tn.utils.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller("hourseController1")
@RequestMapping("/page/")
public class HourseController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private HouseService houseService;

    @RequestMapping("goFabu")
    public String getFabu(Model model) {
        List<Type> typeList = typeService.getAllType();
        List<District> districtList = districtService.getDistrictList();
        model.addAttribute("typeList", typeList);
        model.addAttribute("districtList", districtList);
        return "fabu";
    }

    //通过districtId查询所有街道
    @RequestMapping("getStreetByDid")
    @ResponseBody
    public List<Street> getStreetByDid(Integer id) {
        List<Street> streetList = streetService.getAllStreetByDid(id);

        return streetList;
    }

    //图片上传
    @RequestMapping("showPicture")
    @ResponseBody
    public String showPicture(String path, MultipartFile pFile,HttpSession session) throws IOException {
        //上传图片 e:/images/
        //上传文件名
        String saveFileName = "";
        String fileName = pFile.getOriginalFilename();
        if (fileName.length() != 0) {
            //文件后缀名
            String expName = fileName.substring(fileName.lastIndexOf("."));
            //上传文件扩展名称
            saveFileName = System.currentTimeMillis() + expName;
            //保存文件名称

            String pathFile = "e:/images/" + saveFileName;
            if(path!=null){
                session.setAttribute("path",path);
            }
            File file = new File(pathFile);
            pFile.transferTo(file);
        }

        return saveFileName;
    }


    @RequestMapping("addHouse")
    public String addHouse(House house, HttpSession session) throws IOException {

        //添加用户信息
        house.setIsdel(0);
        house.setIspass(0);
        house.setId(System.currentTimeMillis() + "");
        Users users = (Users) session.getAttribute("users");
        house.setUserId(users.getId());
        boolean addHouse = houseService.addHouse(house);
        if (addHouse) {
            return "redirect:getHouseByUid";
        } else {
            return "redirect:goFabu";
        }
    }

    @RequestMapping("getHouseByUid")
    public String getHouseByUid(PageParam pageParam, Model model, HttpSession session) {

        Users users = (Users) session.getAttribute("users");
        Integer id = users.getId();
        PageInfo<House> pageInfo = houseService.getHouseByUid(id, pageParam);
        model.addAttribute("pageInfo", pageInfo);
        return "guanli";
    }


    @RequestMapping("getHouseByPass")
    public String getHouseByPass(HouseParam houseParam, Model model) {

        PageInfo<House> pageInfo = houseService.getHouseByPass(houseParam);
        List<Type> typeList = typeService.getAllType();
        List<District> districtList = districtService.getDistrictList();

        model.addAttribute("typeList", typeList);
        model.addAttribute("houseParam", houseParam);
        model.addAttribute("districtList", districtList);
        if (houseParam.getDistrictId() != null) {
            List<Street> streets = streetService.getAllStreetByDid(houseParam.getDistrictId());
            model.addAttribute("streets", streets);
        }
        model.addAttribute("pageInfo", pageInfo);
        return "list";
    }

    //修改前
    @RequestMapping("preUpdate")
    public String preUpdate(String id, Model model) {
        List<Type> typeList = typeService.getAllType();
        List<District> districtList = districtService.getDistrictList();
        House house = houseService.getHouseById(id);
        model.addAttribute("house", house);
        Street street = streetService.getStreetById(house.getStreetId());
        District district = districtService.getDistrictById(street.getDistrictId());
        model.addAttribute("street", street);
        model.addAttribute("district", district);
        model.addAttribute("typeList", typeList);
        model.addAttribute("districtList", districtList);
        return "update";
    }

    //修改
    @RequestMapping("updateHouse")
    public String updateHouse(House house,HttpSession session) throws IOException {

        boolean updateHouse = houseService.updateHouse(house);
        if (updateHouse) {
            String path =(String) session.getAttribute("path");
            //修改成功,删除掉运来的图片
             new File("e:/images/"+path).delete();
            return "redirect:getHouseByUid";
        } else {
            return "redirect:updateHouse";
        }
    }


    @RequestMapping("deleteHouse")
    public String deleteHouse(String id) {

        houseService.deleteHouse(id);
        return "redirect:getHouseByUid";
    }
}


