package com.my.data_base.entity;


import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class CreditCardTest {
    private CreditCard item;

    @Before
    public void setUp() throws Exception {
        item = new CreditCard();
    }

    @Test
    public void getNumber() {
        int expected = 177788721;
        item.setNumber(expected);
        assertEquals(expected, item.getNumber());
    }

    @Test
    public void getExpiration() {

        String expected = "2025-09-10 00:00:00";
        item.setExpiration(expected);
        assertEquals(expected, item.getExpiration());
    }

    @Test
    public void getCvv() {
        int expected = 123;
        item.setCvv(expected);
        assertEquals(expected, item.getCvv());
    }

    @Test
    public void getCountId() {
        int expected = 1;
        item.setCountId(expected);
        assertEquals(expected, item.getCountId());
    }
}