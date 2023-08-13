package by.taskManager.taskservice.core.exception;

import jakarta.servlet.http.HttpServletResponse;

public class HandlerFeignClient extends RuntimeException{
    private HttpServletResponse response;
    public HandlerFeignClient(HttpServletResponse response) {
        this.response =response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
