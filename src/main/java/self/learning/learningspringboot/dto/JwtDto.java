package self.learning.learningspringboot.dto;

import org.springframework.security.access.method.P;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtDto {
    public String username;
    public String password;

    @Override
    public String toString() {
        return username + " " + password;
    }
    
}
