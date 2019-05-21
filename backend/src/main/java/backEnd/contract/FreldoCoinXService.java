package backEnd.contract;

import backEnd.AppPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.math.BigInteger;

@Component
public class FreldoCoinXService {

    private static FreldoCoinX CONTRACT;

    private static Long WEI_RATE = 1000000000000000000L;

    @Autowired
    private AppPropertiesConfig config;

    @Autowired
    private void init() throws Exception {
        Web3j WEB3J = Web3j.build(new HttpService(config.contractUrl));

        String hexPrivateKey = String.format("%040x", new BigInteger(1, config.walletPrivateKey.getBytes()));
        String hexPublicKey = String.format("%040x", new BigInteger(1, config.walletPublicKey.getBytes()));
        Credentials CREDENTIALS = Credentials.create(hexPrivateKey, hexPublicKey);

        CONTRACT = FreldoCoinX.load(config.contract, WEB3J, CREDENTIALS, Contract.GAS_PRICE, Contract.GAS_LIMIT);
    }

    public Float currentBalance(){
        RemoteCall<BigInteger> weiRaised = CONTRACT.totalSupply();
        Float balance = null;
        try {
            BigInteger contractBalance = weiRaised.send();
            balance = (contractBalance.floatValue() / Float.valueOf(WEI_RATE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return balance;
    }

    public String getContractAddress() {
        return CONTRACT.getContractAddress();
    }
}
