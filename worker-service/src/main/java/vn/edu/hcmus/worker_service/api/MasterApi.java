package vn.edu.hcmus.worker_service.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import vn.edu.hcmus.worker_service.message.APIResponse;
import vn.edu.hcmus.worker_service.message.WorkerRegisterMessage;

public interface MasterApi
{
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json"
    })
    @POST("workers/register")
    Call<APIResponse<String>> register(@Body WorkerRegisterMessage message);
}
