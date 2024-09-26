package com.example.foodfinal.network;

import java.util.List;

public interface NetworkCallBack<T> {
    void onSuccess(List<T> response);
    void onFailure(String error);
}
