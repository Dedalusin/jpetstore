package org.csu.jpetstore.domain;

public class Supplier {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String token;

    public String getUsername() {
        return username;
    }

    public void setUsrname(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }
}
