package utils;

import com.github.javafaker.Faker;

public class AccountFactory {

    Faker faker = new Faker();

    String username = faker.name().username();
    String firstname = faker.name().firstName();
    String lastname = faker.name().lastName();
    String email = firstname + "_@gmail.com";
    String password = username.toLowerCase() + "123";

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
