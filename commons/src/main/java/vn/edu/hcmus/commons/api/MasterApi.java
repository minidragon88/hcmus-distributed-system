package vn.edu.hcmus.commons.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import vn.edu.hcmus.commons.message.APIResponse;
import vn.edu.hcmus.commons.message.WorkerStatusMessage;

public interface MasterApi
{
    @Headers({
        "Accept: application/json",
        "Content-Type: application/json"
    })
    @POST("workers/status")
    Call<APIResponse<String>> updateStatus(@Body WorkerStatusMessage message);
}
