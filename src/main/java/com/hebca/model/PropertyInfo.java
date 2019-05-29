package com.hebca.model;

/**
 * @ClassName PropertyInfo
 * @Description TODO
 * @Author xuhui
 * @Date 2019/5/27 15:32
 * @ModifyDate 2019/5/27 15:32
 * @Version 1.0
 */
public class PropertyInfo  {
    private String name;
    private String value;

    public PropertyInfo(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
