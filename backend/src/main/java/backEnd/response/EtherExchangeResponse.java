package backEnd.response;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "EtherExchangeResponse")
public class EtherExchangeResponse {

    @ApiObjectField
    private String status;

    @ApiObjectField
    private String message;

    @ApiObjectField
    private EtherPriceResponse result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EtherPriceResponse getResult() {
        return result;
    }

    public void setResult(EtherPriceResponse result) {
        this.result = result;
    }
}
