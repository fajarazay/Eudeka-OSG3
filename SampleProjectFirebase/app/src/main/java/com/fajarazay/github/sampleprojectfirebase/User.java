package com.fajarazay.github.sampleprojectfirebase;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Fajar Septian on 09/03/2019.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
@IgnoreExtraProperties
public class User {
    private String name, email, phone;

    public User() {
    }

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}


