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
            @RequestParam(value = "subject", defaultValue = "") String subject) {


        List<String> to = new ArrayList<>();
        to.add("marzuk777@gmail.com");
        to.add("marzuk.dev@gmail.com");
        to.add("marzuk@eatlbd.com");

        String path = "/home/marzuk/testFileZilla.txt";
//        MailSender.send(message, subject, to, "200.successful@gmail.com", "M4rzuk!6");
        MailSender.send(message, subject, to, "200.successful@gmail.com", "M4rzuk!6", path);
        return ok(success(null).getJson());
    }
}
