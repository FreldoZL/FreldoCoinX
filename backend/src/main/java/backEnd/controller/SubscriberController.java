package backEnd.controller;

import backEnd.request.SubscriberRequest;
import backEnd.response.ApiResponse;
import backEnd.response.SubscribersResponse;
import backEnd.service.SubscriberService;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/subscriber")
@Api(description = "Services for users", name = "User controller")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method uses to save subscriber")
    private ApiResponse saveSubscribe(@RequestBody SubscriberRequest request){
        return subscriberService.saveSubscribe(request);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method uses to get all subscribers")
    private SubscribersResponse getSubscribers(@RequestParam(value = "top", required = false, defaultValue = "0") Integer top,
                                               @RequestParam(value = "skip", required = false, defaultValue = "0") Integer skip){
        return subscriberService.getSubscribers(top, skip);
    }

    @RequestMapping(value = "/export_subscribers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiMethod(description = "Method uses to export all subscribers in excel")
    public void exportSubscribersInExcel() {
        subscriberService.exportSubscribersInExcel();
    }
}
