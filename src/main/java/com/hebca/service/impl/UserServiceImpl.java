package com.hebca.service.impl;

import com.hebca.dao.UserRepository;
import com.hebca.model.UserModel;
import com.hebca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author xuhui
 * @Date 2019/5/27 15:50
 * @ModifyDate 2019/5/27 15:50
 * @Version 1.0
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userDao;
    @Override
    public UserModel save(UserModel userModel) {
        UserModel model=userDao.save(userModel);
        return model;
    }

    @Override
    public UserModel findById(String id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public UserModel findByIdentity(String identity) {
        List<UserModel> list=userDao.findByIdentity(identity);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
