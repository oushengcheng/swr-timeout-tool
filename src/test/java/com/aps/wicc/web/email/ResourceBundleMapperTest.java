package com.aps.wicc.web.email;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class ResourceBundleMapperTest {

    private Map<String, String> map;

    @Before
    public void setUp() throws Exception {
         map = ResourceBundleMapper.create("test-resources");
    }

    @Test
    public void shouldContainKey() {
        assertThat(map.containsKey("testkey"), is(equalTo(true)));
    }

    @Test
    public void shouldReturnAllKeys() {
        assertThat(map.size(), is(equalTo(2)));
    }

    @Test
    public void shouldReturnValue() {
        System.out.println(map.get("testkey"));

        assertThat(map.get("testkey"), is(equalTo("testvalue")));

    }

}
