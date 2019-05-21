package backEnd.response;

import backEnd.model.Subscriber;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.List;

@ApiObject(name = "SubscribersResponse")
public class SubscribersResponse extends ApiResponse{

    @ApiObjectField(name = "subscribers")
    private List<Subscriber> subscribers;

    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
