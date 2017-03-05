package com.ksl.baihuichuanglian.baihui.entity.resulte;

import java.util.List;

/**
 * 作者：Bill on 2017/2/20 14:23
 * 备注：
 */
public class SubjectStore {
    private int pages;
    private int total;
    private List<StoreInfo> list;   //store_list_model 队列信息

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<StoreInfo> getList() {
        return list;
    }

    public void setList(List<StoreInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "SubjectStore{" +
                "pages=" + pages +
                ", total=" + total +
                ", list=" + list +
                '}';
    }
}
