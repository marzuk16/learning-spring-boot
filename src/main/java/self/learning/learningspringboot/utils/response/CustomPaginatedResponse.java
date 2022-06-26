package self.learning.learningspringboot.utils.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomPaginatedResponse {
    public Object markList;
    public Object meta;
}
