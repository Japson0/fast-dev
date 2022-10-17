/*
 * Copyright (c) 2005-2022, EVECOM Technology Co.,Ltd. All rights reserved.
 *
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package net.evecom.custom.minio.response;

/**
 * Result
 *
 * @author Nick Lv
 * @created 2022/10/12 17:23
 */
public class Result {

    public static final Result SUCCESS = new Result(true);

    public static Result failed(String errorMessage) {
        return Result.builder().result(false).message(errorMessage).build();
    }

    private boolean result;

    private String message;

    public Result() {
    }

    public Result(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private boolean result;
        private String message;

        private Builder() {
        }


        public Builder result(boolean result) {
            this.result = result;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Result build() {
            Result response = new Result();
            response.setResult(result);
            response.setMessage(message);
            return response;
        }
    }
}



