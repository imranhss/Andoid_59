package com.emranhss.androidappjee59.api;

import com.emranhss.androidappjee59.model.NotificationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NotificationApi {

    @GET("get_notices.php")
    Call<List<NotificationModel>> getNotifications();


}
