package self.learning.learningspringboot.utils.response;

import lombok.Data;
import org.json.simple.JSONObject;
import self.learning.learningspringboot.constants.ResponseStatus;
import self.learning.learningspringboot.enums.ResponseType;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseBuilder implements Response{
    private final ResponseType type;
    private String status = null;
    private Object data = null;
    private Object markList = null;
    private Object meta = null;
    private String message = null;
    private Object errors = null;
    private Long timestamp = null;


    public ResponseBuilder(ResponseType type) {
        this.type = type;
    }

    public static ResponseBuilder paginatedSuccess(PaginatedResponse data) {
        ResponseBuilder response = new ResponseBuilder(ResponseType.DATA);
        response.data = data.list;
        response.meta = data.meta;
        response.status = ResponseStatus.SUCCESS;
        response.timestamp = response.getTimestamp();
        return response;
    }

    public static ResponseBuilder paginatedSuccess(CustomPaginatedResponse data) {
        ResponseBuilder response = new ResponseBuilder(ResponseType.DATA);
        response.markList = data.markList;
        response.meta = data.meta;
        response.status = ResponseStatus.SUCCESS;
        response.timestamp = response.getTimestamp();
        return response;
    }

    public static ResponseBuilder paginatedSuccess(PaginatedResponse data, String message) {
        ResponseBuilder response = new ResponseBuilder(ResponseType.DATA);
        response.data = data.list;
        response.meta = data.meta;
        response.message = message;
        response.status = ResponseStatus.SUCCESS;
        response.timestamp = response.getTimestamp();
        return response;
    }

    public static ResponseBuilder success(Object data) {
        ResponseBuilder response = new ResponseBuilder(ResponseType.DATA);
        response.data = data;
        response.status = ResponseStatus.SUCCESS;
        response.timestamp = response.getTimestamp();
        return response;
    }

    public static ResponseBuilder success(Object data, String message) {
        ResponseBuilder response = new ResponseBuilder(ResponseType.DATA);
        response.data = data;
        response.message = message;
        response.status = ResponseStatus.SUCCESS;
        response.timestamp = response.getTimestamp();
        return response;
    }

    public static ResponseBuilder error(Object errors) {
        ResponseBuilder response = new ResponseBuilder(ResponseType.ERROR);
        response.errors = errors;
        response.status = ResponseStatus.ERROR;
        response.timestamp = response.getTimestamp();
        return response;
    }

    public static ResponseBuilder error(Object errors, String message) {
        ResponseBuilder response = new ResponseBuilder(ResponseType.ERROR);
        response.errors = errors;
        response.message = message;
        response.status = ResponseStatus.ERROR;
        response.timestamp = response.getTimestamp();
        return response;
    }

    public Long getTimestamp() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return ts.getTime();
    }

    @Override
    public JSONObject getJson() {

        Map<String, Object> maps = new HashMap<String, Object>();

        switch (this.type) {
            case DATA:
                maps.put("status", status);
                maps.put("data", data);
                maps.put("meta", meta);
                maps.put("timestamp", timestamp);
                maps.put("message", message);
            case ERROR:
                maps.put("status", status);
                maps.put("message", message);
                maps.put("timestamp", timestamp);
                maps.put("errors", errors);
        }

        return new JSONObject(maps);
    }

    @Override
    public JSONObject customGetJson() {

        Map<String, Object> maps = new HashMap<String, Object>();

        switch (this.type) {
            case DATA:
                maps.put("status", status);
                maps.put("markList", markList);
                maps.put("meta", meta);
                maps.put("timestamp", timestamp);
                maps.put("message", message);
            case ERROR:
                maps.put("status", status);
                maps.put("message", message);
                maps.put("timestamp", timestamp);
                maps.put("errors", errors);
        }

        return new JSONObject(maps);
    }

    @Override
    public JSONObject customGetJsonForAll() {

        Map<String, Object> maps = new HashMap<String, Object>();

        switch (this.type) {
            case DATA:
                maps.put("status", status);
                maps.put("markList", data);
                maps.put("meta", meta);
                maps.put("timestamp", timestamp);
                maps.put("message", message);
            case ERROR:
                maps.put("status", status);
                maps.put("message", message);
                maps.put("timestamp", timestamp);
                maps.put("errors", errors);
        }

        return new JSONObject(maps);
    }
}
