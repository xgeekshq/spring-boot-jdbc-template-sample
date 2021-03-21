package io.xgeeks.examples.spring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageTest {

    @Test
    public void shouldReturnErrorWhenValueIsInvalid() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Page.of(0, 2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Page.of(2, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Page.of(0, 0));
    }

    @Test
    public void shouldCreatePage() {

    }

}