package com.sophos.backendcanvas.Dto;

public class RespuestaGenericaDto {
    private int status;
    private Object data;

    public RespuestaGenericaDto() {}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
