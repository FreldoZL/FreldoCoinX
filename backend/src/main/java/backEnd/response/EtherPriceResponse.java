package backEnd.response;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "EtherPriceResponse")
public class EtherPriceResponse {

    @ApiObjectField
    private String ethbtc;

    @ApiObjectField
    private String ethbtc_timestamp;

    @ApiObjectField
    private String ethusd;

    @ApiObjectField
    private String ethusd_timestamp;

    public String getEthbtc() {
        return ethbtc;
    }

    public void setEthbtc(String ethbtc) {
        this.ethbtc = ethbtc;
    }

    public String getEthbtc_timestamp() {
        return ethbtc_timestamp;
    }

    public void setEthbtc_timestamp(String ethbtc_timestamp) {
        this.ethbtc_timestamp = ethbtc_timestamp;
    }

    public String getEthusd() {
        return ethusd;
    }

    public void setEthusd(String ethusd) {
        this.ethusd = ethusd;
    }

    public String getEthusd_timestamp() {
        return ethusd_timestamp;
    }

    public void setEthusd_timestamp(String ethusd_timestamp) {
        this.ethusd_timestamp = ethusd_timestamp;
    }
}
