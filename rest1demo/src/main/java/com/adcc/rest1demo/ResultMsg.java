package com.adcc.rest1demo;

/**
 * Created by Liu.DA on 2019/9/25
 */
public class ResultMsg {
    private int errCode;
    private String errmsg;
    private Object p2pdata;

    public ResultMsg(int Errcode, String ErrMsg, Object P2pData){
        this.errCode = Errcode;
        this.errmsg = ErrMsg;
        this.p2pdata = P2pData;
    }

    public int getErrCode(){
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public Object getP2pdata() {
        return p2pdata;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setP2pdata(Object p2pdata) {
        this.p2pdata = p2pdata;
    }
}
