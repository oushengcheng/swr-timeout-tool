package com.aps.wicc.web.formatter.alteration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class ListFormatterTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldFormatSingleItem() {

        // Arrange
        List<String> list = Lists.newArrayList("A");

        // Act
        String formatted = new ListFormatter(list).format();

        // Assert
        assertThat(formatted, is(equalTo("A")));

    }

    @Test
    public void shouldFormatTwoItems() {

        // Arrange
        List<String> list = Lists.newArrayList("A", "B");

        // Act
        String formatted = new ListFormatter(list).format();

        // Assert
        assertThat(formatted, is(equalTo("A and B")));

    }

    @Test
    public void shouldFormatThreeItems() {

        // Arrange
        List<String> list = Lists.newArrayList("A", "B", "C");

        // Act
        String formatted = new ListFormatter(list).format();

        // Assert
        assertThat(formatted, is(equalTo("A, B and C")));

    }

    @Test
    public void shouldFormatFourItems() {

        // Arrange
        List<String> list = Lists.newArrayList("A", "B", "C", "D");

        // Act
        String formatted = new ListFormatter(list).format();

        // Assert
        assertThat(formatted, is(equalTo("A, B, C and D")));

    }
}
