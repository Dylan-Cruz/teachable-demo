package com.cruz.teachable.model;

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

    public String toString() {
        return "PagedResponse(data=" + this.getData() + ", meta=" + this.getMeta() + ")";
    }
}