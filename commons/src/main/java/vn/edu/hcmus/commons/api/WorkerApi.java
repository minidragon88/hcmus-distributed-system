package vn.edu.hcmus.commons.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import vn.edu.hcmus.commons.message.WorkerStatusMessage;

public interface WorkerApi
{
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json"
    })
    @GET("status")
    Call<WorkerStatusMessage> getStatus();
}
