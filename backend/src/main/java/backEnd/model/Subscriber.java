package backEnd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "subscriber")
public class Subscriber {

    @JsonIgnore
    private static final long serialVersionUID = -2397488314765938064L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "language")
    private String language;

    public Subscriber() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscriber email1 = (Subscriber) o;

        if (id != null ? !id.equals(email1.id) : email1.id != null) return false;
        if (email != null ? !email.equals(email1.email) : email1.email != null) return false;
        return language != null ? language.equals(email1.language) : email1.language == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    public static class Builder {
        private String email;
        private String language;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder language(String language) {
            this.language = language;
            return this;
        }

        public Subscriber build() {
            return new Subscriber(this);
        }
    }

    private Subscriber(Builder builder) {
        this.email = builder.email;
        this.language = builder.language;
    }
}
