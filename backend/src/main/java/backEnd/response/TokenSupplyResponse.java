package backEnd.response;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "TokenSupplyResponse")
public class TokenSupplyResponse{

    @ApiObjectField(name = "status")
    private String status;

    @ApiObjectField(name = "message")
    private String message;

    @ApiObjectField(name = "result")
    private String result;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
