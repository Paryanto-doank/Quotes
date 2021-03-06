package mrdoank.blogspot.co.id.quotes.model;

/**
 * Created by root on 03/12/17.
 */

public class Person {

    private int id;
    private String name;
    private String email;
    private String pass;

    public Person() {
    }

    public Person(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public Person(int id, String name, String email, String pass) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name='" + name + '\'' +
                ", email='" + email + '\'' + ", pass='" + pass + '\'' + '}';
    }
}
