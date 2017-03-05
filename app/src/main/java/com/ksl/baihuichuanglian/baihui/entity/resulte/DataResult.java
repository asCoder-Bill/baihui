package com.ksl.baihuichuanglian.baihui.entity.resulte;

import java.util.List;

/**
 * 作者：Bill on 2017/2/20 14:01
 * 备注：返回的“data”的数据
 */
public class DataResult {
    private List<AdInfo> home_img_ad_list;     //广告队列
    private List<CategoryInfo> home_category_menu_list;     //Category队列
    private List<CustomInfo> custom_model;   //Custom队列
    private SubjectStore store_list_model;    //Store信息（整体）

    public List<AdInfo> getHome_img_ad_list() {
        return home_img_ad_list;
    }

    public void setHome_img_ad_list(List<AdInfo> home_img_ad_list) {
        this.home_img_ad_list = home_img_ad_list;
    }

    public List<CategoryInfo> getHome_category_menu_list() {
        return home_category_menu_list;
    }

    public void setHome_category_menu_list(List<CategoryInfo> home_category_menu_list) {
        this.home_category_menu_list = home_category_menu_list;
    }

    public List<CustomInfo> getCustom_model() {
        return custom_model;
    }

    public void setCustom_model(List<CustomInfo> custom_model) {
        this.custom_model = custom_model;
    }

    public SubjectStore getStore_list_model() {
        return store_list_model;
    }

    public void setStore_list_model(SubjectStore store_list_model) {
        this.store_list_model = store_list_model;
    }

    @Override
    public String toString() {
        return "DataResult{" +
                "home_img_ad_list=" + home_img_ad_list +
                ", home_category_menu_list=" + home_category_menu_list +
                ", custom_model=" + custom_model +
                ", store_list_model=" + store_list_model +
                '}';
    }
}
