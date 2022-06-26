package self.learning.learningspringboot.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import self.learning.learningspringboot.utils.MailSender;

import static org.springframework.http.ResponseEntity.ok;
import static self.learning.learningspringboot.utils.response.ResponseBuilder.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mail-sender")
@Api(tags = "mail sender data")
public class MailSenderResource {
    private final MailSender mailSender;

    @PostMapping("/text")
    @ApiOperation(value = "get mapping", response = String.class)
    public ResponseEntity<JSONObject> sendText(
            @RequestParam(value = "message", defaultValue = "") String message,
            @RequestParam(value = "subject", defaultValue = "") String subject,
            @RequestParam(value = "to", defaultValue = "") String to,
            @RequestParam(value = "from", defaultValue = "") String from,
            @RequestParam(value = "password", defaultValue = "") String password
    ) {

        mailSender.send(message, subject, to.split(","), from, password);
        return ok(success(null).getJson());
    }

    @PostMapping("/text-with-attachment")
    @ApiOperation(value = "get mapping", response = String.class)
    public ResponseEntity<JSONObject> sendMail(
            @RequestParam(value = "message", defaultValue = "") String message,
            @RequestParam(value = "subject", defaultValue = "") String subject,
            @RequestParam(value = "to", defaultValue = "") String to,
            @RequestParam(value = "from", defaultValue = "") String from,
            @RequestParam(value = "password", defaultValue = "") String password
    ) {


        String path = "/home/marzuk/testFileZilla.txt";
        mailSender.sendWithAttachments(message, subject, to.split(","), from, password, path);
        return ok(success(null).getJson());
    }
}
