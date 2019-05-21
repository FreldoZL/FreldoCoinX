package backEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "balance")
public class Balance implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = -2397488314765938064L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "balance")
    private Float balance;

    @Column(name = "currency")
    private String currency;

    @Column(name = "date")
    @Type(type = "timestamp")
    private Date date;

    public Balance() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Balance balance1 = (Balance) o;

        if (id != null ? !id.equals(balance1.id) : balance1.id != null) return false;
        if (balance != null ? !balance.equals(balance1.balance) : balance1.balance != null) return false;
        if (currency != null ? !currency.equals(balance1.currency) : balance1.currency != null) return false;
        return date != null ? date.equals(balance1.date) : balance1.date == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "id=" + id +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", date=" + date +
                '}';
    }

    public static class Builder {
        private Float balance;
        private String currency;
        private Date date;

        public Builder balance(Float balance) {
            this.balance = balance;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder date(Date date){
            this.date = date;
            return this;
        }

        public Balance build(){
            return new Balance(this);
        }
    }

    private Balance(Builder builder){
        this.balance = builder.balance;
        this.currency = builder.currency;
        this.date = builder.date;
    }
}
