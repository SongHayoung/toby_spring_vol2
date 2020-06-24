package Spring.Test.jdk.web.hello;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {
    boolean duplicated;
    String availableId;
}
