package com.ksl.baihuichuanglian.baihui.entity.resulte;

/**
 * 作者：Bill on 2017/2/20 14:21
 * 备注：custom_model 单个信息
 */
public class CustomInfo {
    private String name;
    private String open;
    private String param;
    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "CustomInfo{" +
                "name='" + name + '\'' +
                ", open='" + open + '\'' +
                ", param='" + param + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
