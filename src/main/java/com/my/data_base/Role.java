package com.my.data_base;

import com.my.data_base.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Entity:
 * Admin
 * Client
 *
 * @author Herasimenko Olexandra
 */
public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public static Map<Integer, String> getMap() {
        Map<Integer, String> map = new HashMap<>();
        for (Role role : Role.values()) {

            map.put(role.getId(), role.getName().toUpperCase());

        }
        return map;
    }

    public String getName() {
        return name().toLowerCase();
    }

    public int getId() {
        return ordinal();
    }
}
