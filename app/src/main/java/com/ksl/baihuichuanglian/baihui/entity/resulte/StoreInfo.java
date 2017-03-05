package com.ksl.baihuichuanglian.baihui.entity.resulte;

/**
 * 作者：Bill on 2017/2/20 14:31
 * 备注：单个Store信息
 */
public class StoreInfo {
    private String store_id;      //店铺id
    private String logo_img;      //图标url
    private String store_name;    //店铺名称
    private String sales;         //销售量
    private String contact_describe;   //描述
    private String lontitude;          //经度
    private String latitude;           //纬度
    private double score;              //评分
    private String province;           //省份
    private String city;               //城市
    private String district;           //城区
    private String contact_address;    //地址
    private double distance;           //距离

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getLogo_img() {
        return logo_img;
    }

    public void setLogo_img(String logo_img) {
        this.logo_img = logo_img;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getContact_describe() {
        return contact_describe;
    }

    public void setContact_describe(String contact_describe) {
        this.contact_describe = contact_describe;
    }

    public String getLontitude() {
        return lontitude;
    }

    public void setLontitude(String lontitude) {
        this.lontitude = lontitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getContact_address() {
        return contact_address;
    }

    public void setContact_address(String contact_address) {
        this.contact_address = contact_address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "StoreInfo{" +
                "store_id='" + store_id + '\'' +
                ", logo_img='" + logo_img + '\'' +
                ", store_name='" + store_name + '\'' +
                ", sales='" + sales + '\'' +
                ", contact_describe='" + contact_describe + '\'' +
                ", lontitude='" + lontitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", score=" + score +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", contact_address='" + contact_address + '\'' +
                ", distance=" + distance +
                '}';
    }
}
