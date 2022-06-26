package self.learning.learningspringboot.utils.response;

import org.json.simple.JSONObject;

public interface Response {
    JSONObject getJson();

    JSONObject customGetJson();

    JSONObject customGetJsonForAll();

}
