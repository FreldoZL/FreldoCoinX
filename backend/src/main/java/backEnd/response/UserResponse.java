package backEnd.response;

import backEnd.model.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "UserResponse")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponse extends ApiResponse{

    @ApiObjectField(name = "user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
