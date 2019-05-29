package com.hebca.service;

import com.hebca.model.UserModel;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author xuhui
 * @Date 2019/5/27 15:50
 * @ModifyDate 2019/5/27 15:50
 * @Version 1.0
 */

public interface UserService {
    UserModel save(UserModel userModel);
    UserModel findById(String id);
    UserModel findByIdentity(String identity);

}
