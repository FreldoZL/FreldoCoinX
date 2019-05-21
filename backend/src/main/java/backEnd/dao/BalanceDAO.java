package backEnd.dao;

import backEnd.model.Balance;

import java.util.List;

public interface BalanceDAO {
    void saveBalance(Balance balance);

    List<Balance> getBalances();

    Balance getBalanceByCurrency(String currency);
}
