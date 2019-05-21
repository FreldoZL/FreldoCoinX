package backEnd;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-${spring.profiles.active}.properties")
public class AppPropertiesConfig {

    @Value("${contract.url}")
    public String contractUrl;

    @Value("${contract}")
    public String contract;

    @Value("${wallet.private.key}")
    public String walletPrivateKey;

    @Value("${wallet.public.key}")
    public String walletPublicKey;

    @Value("${token}")
    public String token;;

    @Value("${email.send.name}")
    public String emailName;

    @Value("${email.send.from}")
    public String emailFrom;

    @Value("${etherscan.api.uri}")
    public String etherscanApiUri;

    @Value("${etherscan.api.key}")
    public String etherscanApiKey;

    @Value("${wei.rate}")
    public String weiRate;

    @Value("${backend.server.address}")
    public String backendServerAddress;

    @Value("${email.admin}")
    public String emailAdmin;

    @Value("${ipapi.access.key}")
    public String ipApiAccessKey;

    @Value("${ipapi.request.url}")
    public String ipApiRequestUrl;
}
