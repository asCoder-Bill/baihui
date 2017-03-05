package com.ksl.baihuichuanglian.baihui.entity.resulte;

import java.util.List;

/**
 * 作者：Bill on 2017/2/20 14:17
 * 备注：直接请求返回数据格式
 */
public class RetrofitEntity {
    private int code;
    private boolean success;
    private String msg;
    private String ui_name;
    private DataResult data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUi_name() {
        return ui_name;
    }

    public void setUi_name(String ui_name) {
        this.ui_name = ui_name;
    }

    public DataResult getData() {
        return data;
    }

    public void setData(DataResult data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "RetrofitEntity{" +
                "code=" + code +
                ", success=" + success +
                ", msg='" + msg + '\'' +
                ", ui_name='" + ui_name + '\'' +
                ", data=" + data +
                '}';
    }


    /*private int ret;
    private String msg;
    private List<SubjectResulte> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<SubjectResulte> getData() {
        return data;
    }

    public void setData(List<SubjectResulte> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RetrofitEntity{" +
                "ret=" + ret +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }*/
}
