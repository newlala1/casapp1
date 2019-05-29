package com.hebca.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @ClassName BaseModel
 * @Description TODO
 * @Author xuhui
 * @Date 2019/5/27 15:46
 * @ModifyDate 2019/5/27 15:46
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseModel {
    @Id
    @GenericGenerator(name = "uuid",strategy = "uuid.hex")
    @GeneratedValue(generator = "uuid")
    private String id;

}
