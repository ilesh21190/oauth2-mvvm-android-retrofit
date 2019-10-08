package com.connectlifes.admin.oauth2.response;

import java.util.UUID;

public class Leads {
    UUID uuid;
    String name;
    String email;
    String phone;
    String address;

    public UUID getUuid() {
        return uuid;
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

    public String getAddress() {
        return address;
    }
}
