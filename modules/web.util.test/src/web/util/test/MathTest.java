package web.util.test;

import org.junit.jupiter.api.Test;
import web.util.Math;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathTest {
    @Test
    public void testAdd() {
        assertEquals(2, Math.add(1, 1));
    }
}