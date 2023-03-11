package self.learning.lib.utils;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T get(String url, ParameterizedTypeReference<T> returnType) {
        return request(url, HttpMethod.GET, null, returnType);
    }

    public <T> T post(String url, @Nullable Object body, ParameterizedTypeReference<T> returnType) {
        return request(url, HttpMethod.POST, body, returnType);
    }

    public <T> T put(String url, @Nullable Object body, ParameterizedTypeReference<T> returnType) {
        return request(url, HttpMethod.PUT, body, returnType);
    }

    public <T> T patch(String url, @Nullable Object body,
            ParameterizedTypeReference<T> returnType) {
        return request(url, HttpMethod.PATCH, body, returnType);
    }

    public <T> T delete(String url, ParameterizedTypeReference<T> returnType) {
        return request(url, HttpMethod.DELETE, null, returnType);
    }

    private <T> T request(String url, HttpMethod method, @Nullable Object body,
            ParameterizedTypeReference<T> returnType) {

        HttpEntity<Object> requestEntity = null;
        if (body != null) {
            requestEntity = new HttpEntity<Object>(body);
        }

        ResponseEntity<T> responseEntity =
                restTemplate.exchange(url, method, requestEntity, returnType);
        return responseEntity.getBody();
    }
}
