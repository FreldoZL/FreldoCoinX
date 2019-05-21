package backEnd.service;

import backEnd.request.SubscriberRequest;
import backEnd.response.ApiResponse;
import backEnd.response.SubscribersResponse;

public interface SubscriberService {
    ApiResponse saveSubscribe(SubscriberRequest request);

    SubscribersResponse getSubscribers(Integer top, Integer skip);

    void exportSubscribersInExcel();
}
