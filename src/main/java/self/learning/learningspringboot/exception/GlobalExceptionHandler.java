package self.learning.learningspringboot.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import self.learning.learningspringboot.utils.response.ResponseBuilder;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static self.learning.learningspringboot.constants.ValidatorConstants.*;


@Slf4j
@ControllerAdvice(basePackages = "self.learning.learningspringboot.resource")
public class GlobalExceptionHandler {

//        @ExceptionHandler({Exception.class})
//        public ResponseEntity<JSONObject> handleGlobalExceptions(Exception ex, WebRequest request) {
//            log.error("Unknown exception.", ex);
//            if (ex instanceof InvalidFormatException) {
//                return new ResponseEntity<>(ResponseBuilder.error(ex.getMessage()).getJson(), HttpStatus.BAD_REQUEST);
//            }
//            if (ex instanceof HttpMessageNotReadableException) {
//                return new ResponseEntity<>(ResponseBuilder.error(ex.getMessage()).getJson(), HttpStatus.BAD_REQUEST);
//            }
//            return new ResponseEntity<>(ResponseBuilder.error((INTERNAL_SERVER_ERROR)).getJson(), HttpStatus.BAD_REQUEST);
//        }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<JSONObject> handleNotFoundExceptions(Exception ex, WebRequest request) {
        String message = ex.getMessage().equals("") ? RESOURCE_NOT_FOUND : ex.getMessage();
        return new ResponseEntity<>(ResponseBuilder.error((message)).getJson(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<JSONObject> handleBadRequestExceptions(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ResponseBuilder.error((ex.getMessage())).getJson(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<JSONObject> handleNumberFormatExceptions(InvalidFormatException ex, WebRequest request) {
        return new ResponseEntity<>(ResponseBuilder.error((NUMBER_FORMAT_EXCEPTION)).getJson(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage());
        String[] str = e.getBindingResult().getAllErrors().get(0).getCodes()[1].split("\\.");
        StringBuffer msg = new StringBuffer(str[1] + ":");
        msg.append(e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return new ResponseEntity(msg.toString(), HttpStatus.BAD_REQUEST);
    }
}
