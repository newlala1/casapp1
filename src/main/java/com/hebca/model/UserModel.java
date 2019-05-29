package com.hebca.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @ClassName UserModel
 * @Description TODO
 * @Author xuhui
 * @Date 2019/5/27 15:48
 * @ModifyDate 2019/5/27 15:48
 * @Version 1.0
 */
@Entity
@Data
public class UserModel extends BaseModel {
    private String name;
    private String identity;
    private String rolename;
    private Integer loginCount;
    private String userhash;
    @Column(columnDefinition = "longtext")
    private String content;
    public UserModel(){}
    public UserModel(String name, String identity, String rolename, Integer loginCount, String content,String userhash) {
        this.name = name;
        this.identity = identity;
        this.rolename = rolename;
        this.loginCount = loginCount;
        this.content = content;
        this.userhash=userhash;
    }
}
