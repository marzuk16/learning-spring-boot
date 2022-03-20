package com.learning.Learningspringboot.utils.response;

import org.json.simple.JSONObject;

public interface Response {

    JSONObject getJson();

    JSONObject customGetJson();

    JSONObject customGetJsonForAll();
}
