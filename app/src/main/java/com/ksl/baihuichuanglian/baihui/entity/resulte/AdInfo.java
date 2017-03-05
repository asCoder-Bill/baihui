package com.ksl.baihuichuanglian.baihui.entity.resulte;

/**
 * 作者：Bill on 2017/2/20 14:11
 * 备注：广告信息（单个）
 */
public class AdInfo {
    private String id;
    private String img;     //图片url
    private String link;    //链接
    private String update_time;    //更新时间
    private String name;         //名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdInfo{" +
                "id='" + id + '\'' +
                ", img='" + img + '\'' +
                ", link='" + link + '\'' +
                ", update_time='" + update_time + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
