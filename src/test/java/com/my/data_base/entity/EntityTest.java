package com.my.data_base.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntityTest {
    private Entity item;

    @Before
    public void setUp() throws Exception {
        item = new Entity();
    }

    @Test
    public void getId() {
        int expected = 1;
        item.setId(expected);
        assertEquals(expected, item.getId());
    }
}