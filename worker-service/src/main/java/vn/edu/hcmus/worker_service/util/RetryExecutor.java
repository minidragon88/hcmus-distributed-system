package vn.edu.hcmus.worker_service.util;

import retrofit2.Call;
import retrofit2.Response;

public class RetryExecutor<T>
{
    private final Call<T> callable;
    private int maxRetry = 3;
    private int maxRetryWait = 120;

    public RetryExecutor(final Call<T> callable)
    {
        this.callable = callable;
    }

    public Response<T> executeWithRetry()
    {
        Exception exception = null;
        int currentRun = 0;
        while (currentRun < maxRetry) {
            try {
                final Response<T> response = callable.clone().execute();
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
                System.out.println(String.format("Sleeping %s to next retry", sleepTime));
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
        private Call<T> callable;
        private int maxRetry = 3;
        private int maxRetryWait = 120;
        public Builder() {}

        public RetryExecutor<T> build()
        {
            if (callable == null) {
                throw new IllegalArgumentException("Callable must be passed");
            }
            final RetryExecutor<T> executor = new RetryExecutor<>(callable);
            executor.setMaxRetry(maxRetry);
            executor.setMaxRetryWait(maxRetryWait);
            return executor;
        }

        public Builder<T> withCallable(final Call<T> callable)
        {
            this.callable = callable;
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
