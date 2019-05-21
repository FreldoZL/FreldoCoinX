package backEnd.service.impl;

import backEnd.AppPropertiesConfig;
import backEnd.contract.FreldoCoinXService;
import backEnd.dao.BalanceDAO;
import backEnd.model.Balance;
import backEnd.model.Currency;
import backEnd.response.EtherExchangeResponse;
import backEnd.response.TokenSupplyResponse;
import backEnd.service.SmartContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.util.Date;

@Service
public class SmartContractServiceImpl implements SmartContractService {

    @Autowired
    private FreldoCoinXService contractService;

    @Autowired
    private AppPropertiesConfig config;

    @Autowired
    private BalanceDAO balanceDAO;

    @Override
    public Float getBalance() {
        return contractService.currentBalance();
    }

    @Override
    public String getContractAddress() {
        return contractService.getContractAddress();
    }

    @Override
    public Float getTokensAmount() {

        RestTemplate restTemplateTokens = new RestTemplate();
        UriComponentsBuilder uriTokens = UriComponentsBuilder
                .fromUriString(config.etherscanApiUri)
                .queryParam("module", "stats")
                .queryParam("action", "tokensupply")
                .queryParam("contractaddress", config.token)
                .queryParam("apikey", config.etherscanApiKey);
        ResponseEntity<TokenSupplyResponse> tokens = restTemplateTokens.getForEntity(uriTokens.build().toString(), TokenSupplyResponse.class);

        String result = tokens.getBody().getResult();

        Float balanceInWEI = null;
        if (result != null && !result.isEmpty()) {
            balanceInWEI = Float.valueOf(result);
        }

        Float balanceInFRECN = null;
        if (balanceInWEI != null) {
            balanceInFRECN = Float.valueOf(convertFromWei(balanceInWEI.toString()));
        }
        return balanceInFRECN;
    }

    @Override
    public void updateBalanceEth() {
        Balance balance = balanceDAO.getBalanceByCurrency(Currency.ETH.getCurrency());
        if (balance == null) {
            balance = new Balance.Builder().balance(getBalance()).date(new Date()).build();
            balanceDAO.saveBalance(balance);
        } else {
            Float currentBalance = getBalance();
            if (currentBalance != null) {
                if (!balance.getBalance().equals(currentBalance)) {
                    balance.setBalance(currentBalance);
                    balance.setDate(new Date());
                    balanceDAO.saveBalance(balance);
                }
            }
        }
    }

    @Override
    public void updateBalanceUsd() {
        Balance balance = balanceDAO.getBalanceByCurrency(Currency.USD.getCurrency());
        if (balance == null) {
            Float balanceInUSD = getEthPriceInUSD() * getBalance();
            balance = new Balance.Builder().balance(balanceInUSD).date(new Date()).build();
            balanceDAO.saveBalance(balance);
        } else {
            Float currentBalance = getEthPriceInUSD() * getBalance();
            if (!balance.getBalance().equals(currentBalance)) {
                balance.setBalance(currentBalance);
                balance.setDate(new Date());
                balanceDAO.saveBalance(balance);
            }
        }
    }

    public Float getEthPriceInUSD() {
        RestTemplate restTemplateEtherPrice = new RestTemplate();
        UriComponentsBuilder uri = UriComponentsBuilder
                .fromUriString(config.etherscanApiUri)
                .queryParam("module", "stats")
                .queryParam("action", "ethprice")
                .queryParam("apikey", config.etherscanApiKey);
        ResponseEntity<EtherExchangeResponse> etherPrice = restTemplateEtherPrice.getForEntity(uri.build().toString(), EtherExchangeResponse.class);

        String ethPrice = etherPrice.getBody().getResult().getEthusd();
        return Float.valueOf(ethPrice);
    }

    @Override
    public Balance getEthBalance() {
        return balanceDAO.getBalanceByCurrency(Currency.ETH.getCurrency());
    }

    private String convertFromWei(String weiAmount) {
        Float convert = null;
        if (weiAmount != null) {
            convert = (Float.valueOf(weiAmount) / Float.valueOf(config.weiRate));
        }
        if (convert != null) {
            return convert.toString();
        } else {
            return null;
        }
    }
}
