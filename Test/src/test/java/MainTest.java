import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void newArrayAfter4()
    {
        int[] input = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] expected = {1, 7};
        int[] actual = Main.newArrayAfter4(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void newArrayAfter4_1()
    {
        int[] input = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expected = {5, 6, 7, 8, 9};
        int[] actual = Main.newArrayAfter4(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void newArrayAfter4_2()
    {
        int[] input = {1, 2, 3, 5, 6, 7, 8, 9};

        assertThrows(RuntimeException.class, () -> Main.newArrayAfter4(input));
    }

    @Test
    void newArrayAfter4_3()
    {
        int[] input = {1, 8, 5, 2, 9, 1, 7};

        assertThrows(RuntimeException.class, () -> Main.newArrayAfter4(input));
    }

    @Test
    void newArrayAfter4_4()
    {
        int[] input = {};

        assertThrows(RuntimeException.class, () -> Main.newArrayAfter4(input));
    }

    @Test
    void correctlyChecked1And4InArray()
    {
        int[] input = {1, 7, 4, 3, 7, 9};
        boolean expected = true;
        boolean actual = Main.correctlyChecked1And4InArray(input);
        assertEquals(expected, actual);
    }

    @Test
    void correctlyChecked1And4InArray2()
    {
        int[] input = {1, 6, 7, 3, 8, 9};
        boolean expected = false;
        boolean actual = Main.correctlyChecked1And4InArray(input);
        assertEquals(expected, actual);
    }

    @Test
    void correctlyChecked1And4InArray3()
    {
        int[] input = {3, 7, 4, 8, 7};
        boolean expected = false;
        boolean actual = Main.correctlyChecked1And4InArray(input);
        assertEquals(expected, actual);
    }

    @Test
    void correctlyChecked1And4InArray4()
    {
        int[] input = {};
        boolean expected = false;
        boolean actual = Main.correctlyChecked1And4InArray(input);
        assertEquals(expected, actual);
    }


}