package com.teste.testegfx.data.common;


public interface Callback<T> {
    void onSuccess(T data);
    void onError(Throwable throwable);
}
