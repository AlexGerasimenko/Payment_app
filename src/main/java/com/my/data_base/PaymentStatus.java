package com.my.data_base;


import com.my.data_base.entity.Payment;

import java.util.HashMap;
import java.util.Map;

/**
 * Status entity.
 *
 * @author
 */
public enum PaymentStatus {
    PREPARED, SENT;

    public static PaymentStatus getStatus(Payment payment) {
        int status_id = payment.getStatusId();
        return PaymentStatus.values()[status_id];
    }

    public static Map<Integer, String> getMap() {
        Map<Integer, String> map = new HashMap<>();
        for (PaymentStatus status : PaymentStatus.values()) {

            map.put(status.getId(), status.getName().toUpperCase());

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