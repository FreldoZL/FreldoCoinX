package backEnd.response;

import backEnd.model.User;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.List;

@ApiObject(name = "Users")
public class UsersResponse extends ApiResponse{

    @ApiObjectField(name = "users")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
