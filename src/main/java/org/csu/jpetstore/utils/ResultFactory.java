package org.csu.jpetstore.utils;

public class ResultFactory {
    private boolean status;
    private String msg;
    private Object data;

    public ResultFactory() {
    }

    public ResultFactory(boolean status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultFactory successResult(Object data,String msg) {
        return new ResultFactory(true, msg, data);
    }

    public static ResultFactory failedResult(String msg) {
        return new ResultFactory(false, msg, null);
    }
}
