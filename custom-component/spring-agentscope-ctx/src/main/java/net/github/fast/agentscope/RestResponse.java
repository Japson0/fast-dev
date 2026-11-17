package net.github.fast.agentscope;

/** Self-contained REST-style response envelope. */
public class RestResponse<T> {
    private boolean result = true;

    private String code = "200";

    private T data;

    private String message;

    public static <T> RestResponse<T> renderSuccess(T data) {
        var response = new RestResponse<T>();
        response.setData(data);
        return response;
    }

    public static <T> RestResponse<T> renderError(String code, String message) {
        var response = new RestResponse<T>();
        response.setResult(false);
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public boolean isResult() { return result; }
    public void setResult(boolean result) { this.result = result; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
