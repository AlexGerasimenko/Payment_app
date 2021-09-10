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
public enum Job {
    ADMIN, CLIENT;

    public static Job getJob(User user) {
        int jobId = user.getJobId();
        return Job.values()[jobId];
    }

    public static Map<Integer, String> getMap() {
        Map<Integer, String> map = new HashMap<>();
        for (Job job : Job.values()) {

            map.put(job.getId(), job.getName().toUpperCase());

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
