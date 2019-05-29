package com.hebca.model;

import java.util.List;

/**
 * @ClassName JsonResp
 * @Description TODO
 * @Author xuhui
 * @Date 2019/5/27 15:16
 * @ModifyDate 2019/5/27 15:16
 * @Version 1.0
 */
public class  JsonResp<T extends Object> {
    private long total;
    private List<T> rows;

    public JsonResp(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
