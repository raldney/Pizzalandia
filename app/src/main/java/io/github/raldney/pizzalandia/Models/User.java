package io.github.raldney.pizzalandia.Models;

/**
 * Created by raldney on 03/12/2017.
 */

public class User {

    private String id;
    private String email;
    private String password;
    private String nome;
    private String phone_number;
    private String address;

    public User(String id, String email, String password, String nome, String phone_number, String address) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.phone_number = phone_number;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }




}
