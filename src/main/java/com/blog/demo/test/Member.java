package com.blog.demo.test;

public class Member {
    // 변수는 다 private으로 만든다
    // 변수를 업데이트 하고 싶을 땐 메소드로 한다
    private int id;
    private String username;
    private String password;
    private String email;

    // 생성자
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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
}
