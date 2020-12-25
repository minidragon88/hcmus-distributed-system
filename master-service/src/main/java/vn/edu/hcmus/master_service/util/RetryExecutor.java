package vn.edu.hcmus.master_service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

public class RetryExecutor<T>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RetryExecutor.class);
    private final Call<T> call;
    private int maxRetry = 3;
    private int maxRetryWait = 120;

    public RetryExecutor(final Call<T> call)
    {
        this.call = call;
    }

    public Response<T> executeWithRetry()
    {
        Exception exception = null;
        int currentRun = 0;
        while (currentRun < maxRetry) {
            try {
                final Response<T> response = call.clone().execute();
                if (!response.isSuccessful()) {
                    throw new RuntimeException(String.format("Error from server. Code : %s, Body: %s", response.code(), response.errorBody().string()));
                }
                return response;
            }
            catch (final Exception e) {
                exception = e;
            }
            currentRun++;
            long sleepTime = Math.round(Math.pow(2, currentRun));
            sleepTime = sleepTime > maxRetryWait ? maxRetryWait : sleepTime;
            try {
                LOGGER.info(String.format("Sleeping %s to next retry", sleepTime));
                Thread.sleep(sleepTime * 1000);
            }
            catch (final InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException(exception);
    }

    public int getMaxRetry()
    {
        return maxRetry;
    }

    public void setMaxRetry(final int maxRetry)
    {
        this.maxRetry = maxRetry;
    }

    public int getMaxRetryWait()
    {
        return maxRetryWait;
    }

    public void setMaxRetryWait(final int maxRetryWait)
    {
        this.maxRetryWait = maxRetryWait;
    }

    public static class Builder<T>
    {
        private Call<T> call;
        private int maxRetry = 3;
        private int maxRetryWait = 120;
        public Builder() {}

        public RetryExecutor<T> build()
        {
            if (call == null) {
                throw new IllegalArgumentException("Parameter call is required");
            }
            final RetryExecutor<T> executor = new RetryExecutor<>(call);
            executor.setMaxRetry(maxRetry);
            executor.setMaxRetryWait(maxRetryWait);
            return executor;
        }

        public Builder<T> withCall(final Call<T> callable)
        {
            this.call = callable;
            return this;
        }

        public Builder<T> withMaxRetry(final int maxRetry)
        {
            this.maxRetry = maxRetry;
            return this;
        }

        public Builder<T> withMaxRetryWait(final int maxRetryWait)
        {
            this.maxRetryWait = maxRetryWait;
            return this;
        }
    }
}
