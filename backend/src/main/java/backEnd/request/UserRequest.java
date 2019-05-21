package backEnd.request;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

@ApiObject(name = "UserRequest")
public class UserRequest {

    @ApiObjectField(name = "email")
    private String email;

    @ApiObjectField(name = "firstName")
    private String firstName;

    @ApiObjectField(name = "lastName")
    private String lastName;

    @ApiObjectField(name = "wallet")
    private String wallet;

    @ApiObjectField(name = "country")
    private String country;

    @ApiObjectField(name = "language")
    private String language;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
