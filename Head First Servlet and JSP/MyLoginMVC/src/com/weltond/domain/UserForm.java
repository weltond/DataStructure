package com.weltond.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Weltond Ning
 * @Project MyLoginMVC
 * @Date 5/22/2019
 */
public class UserForm {
    /*
    Username: Can't be empty, size 3 ~ 8! <br>
    Password: Can't be empty, size 3 ~ 8! <br>
    Confirm Password: Must be the same! <br>
    Email: Can't be empty, match email format! <br>
    Birthday: Can't be empty, must be yyyy-MM-dd! <br>
     */
    private int id;
    private String username;
    private String password;
    private String repassword;
    private String email;
    private String birthday;

    Map<String, String> msg = new HashMap<>();

    public boolean validate() {
        if ("".equals(username)) {
            msg.put("username", "Username can't be empty");
        } else if (!username.matches("\\w{3,8}")) {
            msg.put("username", "Username length has to be 3 ~ 8 chars");
        }

        if ("".equals(password)) {
            msg.put("password", "Password can't be empty");
        } else if (!password.matches("\\d{3,8}")) {
            msg.put("password", "Password length has to be 3 ~ 8 digits");
        }

        if (!repassword.equals(password)) {
            msg.put("repassword", "Password doesn't MATCH");
        }

        if ("".equals(email)) {
            msg.put("email", "Email can't be empty");
        } else if (!email.matches("\\b^['_a-z0-9-\\+]+(\\.['_a-z0-9-\\+]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*\\.([a-z]{2}|aero|asia|biz|cn|com|edi|gov|info|int|jobs|mil|mobi|museum|name|nato|net|org|pro|tel|travel|xxx)$\\b")) {
            msg.put("email", "Email format doesn't MATCH");
        }

        if ("".equals(birthday)) {
            msg.put("birthday", "Birthday can't be empty");
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                simpleDateFormat.parse(birthday);
            } catch (ParseException e) {
                msg.put("birthday", "Birthday format doesn't MATCH");
            }
        }

        return msg.isEmpty();
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Map<String, String> getMsg() {
        return msg;
    }

    public void setMsg(Map<String, String> msg) {
        this.msg = msg;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

}
