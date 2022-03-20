package com.learning.Learningspringboot.resource;

import com.learning.Learningspringboot.utils.MailSender;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.learning.Learningspringboot.utils.response.ResponseBuilder.success;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/test")
@Api(tags = "test data")
public class TestResource {

    @GetMapping("/")
    @ApiOperation(value = "get mapping", response = String.class)
    public ResponseEntity<JSONObject> get(
            @RequestParam(value = "message", defaultValue = "") String message,
            @RequestParam(value = "subject", defaultValue = "") String subject,
            @RequestParam(value = "to", defaultValue = "") String to,
            @RequestParam(value = "from", defaultValue = "") String from,
            @RequestParam(value = "password", defaultValue = "") String password
            ) {


        String path = "/home/marzuk/testFileZilla.txt";
//        MailSender.send(message, subject, to, to.split(","), password);
        MailSender.send(message, subject, to.split(","), from, password, path);
        return ok(success(null).getJson());
    }
}
