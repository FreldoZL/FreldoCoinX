package backEnd.model;

public enum Currency {

    USD("USD"),
    ETH("ETH");

    String currency;

    Currency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
