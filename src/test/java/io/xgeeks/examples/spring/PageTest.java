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
        Page page = Page.of(1, 2);
        Assertions.assertNotNull(page);
        Assertions.assertEquals(0L, page.getOffset());
        Assertions.assertEquals(2L, page.getLimit());
    }

    @Test
    public void shouldReturnNext() {
        Page page = Page.of(1, 2);
        Assertions.assertEquals(0L, page.getOffset());
        Assertions.assertEquals(2L, page.getLimit());
        Page next = page.next();
        Assertions.assertEquals(2L, next.getOffset());
        Assertions.assertEquals(2L, next.getLimit());
    }

}