package by.taskManager.taskservice.endpoint.service.controller.fallback;

import by.TaskManeger.utils.error.ErrorResponse;
import by.taskManager.taskservice.core.exception.ErrorResponseException;
import by.taskManager.taskservice.core.exception.ErrorResponseExceptionBad;
import by.taskManager.taskservice.core.exception.StrcturedErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;


public class RetreiveMessageErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        StrcturedErrorException strcturedErrorException = null;
        ErrorResponse errorResponse = null;
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream bodyIs = response.body().asInputStream()) {
            strcturedErrorException = mapper.readValue(bodyIs, StrcturedErrorException.class);
        } catch (IOException e) {
        }
        try (InputStream bodyIs = response.body().asInputStream()) {
            errorResponse = mapper.readValue(bodyIs, ErrorResponse.class);
        } catch (IOException e) {
        }
        switch (response.status()) {
            case 400:
                if (strcturedErrorException != null){
                    return new StrcturedErrorException(strcturedErrorException.getErrors());
                }else
                    return new ErrorResponseException(errorResponse.getErrors());
            case 500:
                return new ErrorResponseExceptionBad(errorResponse.getErrors());
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
