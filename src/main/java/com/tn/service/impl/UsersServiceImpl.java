package com.tn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tn.entity.Users;
import com.tn.entity.UsersExample;
import com.tn.mapper.UsersMapper;
import com.tn.service.UsersService;
import com.tn.utils.MD5Utils;
import com.tn.utils.UsersParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    public PageInfo<Users> getUsersByPage(UsersParam usersParam) {
        PageHelper.startPage(usersParam.getPage(),usersParam.getRows());
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andIsadminEqualTo(1);
        if(usersParam.getName()!=null){
            criteria.andNameLike("%"+usersParam.getName()+"%");
        }
        if (usersParam.getTelephone()!=null){
            criteria.andTelephoneLike("%"+usersParam.getTelephone()+"%");
        }
        List<Users> users = usersMapper.selectByExample(example);

        PageInfo<Users> pageInfo=new PageInfo<Users>(users);

        return pageInfo;
    }

    public boolean addUsers(Users users) {
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.insertSelective(users)>0;
    }

    public boolean deleteUsers(Integer id) {
        return usersMapper.deleteByPrimaryKey(id)>0;
    }

    public boolean deleteUsersList(Integer[] ids) {

        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));

        return  usersMapper.deleteByExample(example)>0;
    }

    public boolean updateUsers(Users users) {
        users.setPassword(MD5Utils.md5Encrypt(users.getPassword()));
        return usersMapper.updateByPrimaryKeySelective(users)>0;
    }

    public Users getUsersById(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }

    //用过用户名判断用户是否存在
    public boolean checkUserName(String name) {
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        if (name != null) {
            criteria.andNameEqualTo(name);
        }
        List<Users> users = usersMapper.selectByExample(example);
        if(users.size()!=0){
            return true;
        }
        return  false;
    }

    public Users loginUsers(String name, String password) {
        password=MD5Utils.md5Encrypt(password);
        UsersExample example=new UsersExample();
        UsersExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        criteria.andPasswordEqualTo(password);
       List<Users> list= usersMapper.selectByExample(example);
       if(list.size()==0){
           return null;
       }else {
           return list.get(0);
       }
    }

}

