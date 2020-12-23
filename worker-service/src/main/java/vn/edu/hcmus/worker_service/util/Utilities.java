package vn.edu.hcmus.worker_service.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Utilities
{
    private Utilities() {}
    public static Retrofit getRetrofit(final String baseUrl)
    {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
