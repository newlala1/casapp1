package com.hebca.dao;

import com.hebca.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName UserRepository
 * @Description TODO
 * @Author xuhui
 * @Date 2019/5/27 15:49
 * @ModifyDate 2019/5/27 15:49
 * @Version 1.0
 */
@Repository
public interface UserRepository extends JpaRepository<UserModel,String> {
    List<UserModel> findByIdentity(String identity);
}
