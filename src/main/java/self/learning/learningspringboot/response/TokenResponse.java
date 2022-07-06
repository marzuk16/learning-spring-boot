package self.learning.learningspringboot.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class TokenResponse {
    public String token;

    public static TokenResponse response(String token){
        TokenResponse response = new TokenResponse();
        response.setToken(token);
        return response;
    }
}
