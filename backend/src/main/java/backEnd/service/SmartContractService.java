package backEnd.service;

import backEnd.model.Balance;

import java.math.BigInteger;

public interface SmartContractService {
    Float getBalance();

    String getContractAddress();

    Float getTokensAmount();

    Float getEthPriceInUSD();

    void updateBalanceEth();

    void updateBalanceUsd();

    Balance getEthBalance();
}
