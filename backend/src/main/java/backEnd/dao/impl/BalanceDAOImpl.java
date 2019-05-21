package backEnd.dao.impl;

import backEnd.dao.AbstractDAO;
import backEnd.dao.BalanceDAO;
import backEnd.model.Balance;

import java.util.List;

public class BalanceDAOImpl extends AbstractDAO<Integer, Balance> implements BalanceDAO {

    @Override
    public void saveBalance(Balance balance) {
        getSession().saveOrUpdate(balance);
    }

    @Override
    public List<Balance> getBalances() {
        return getSession().createQuery("SELECT b FROM Balance b").list();
    }

    @Override
    public Balance getBalanceByCurrency(String currency) {
        return (Balance)getSession().createQuery("SELECT b FROM Balance b WHERE b.currency =:currency")
                .setParameter("currency", currency).uniqueResult();
    }
}
