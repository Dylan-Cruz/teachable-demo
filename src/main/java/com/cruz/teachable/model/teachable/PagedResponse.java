package com.cruz.teachable.model.teachable;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class PagedResponse<T> {
    private T data;
    private Meta meta;

    @JsonAnySetter
    public void setData(String key, T value) {
        this.data = value;
    }

    public T getData() {
        return data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "PagedResponse(data=" + this.getData() + ", meta=" + this.getMeta() + ")";
    }
}