package ua.hillel;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SimpleMathLibraryTest {

    @Test
    public void SimpleMathLibraryAddTest() {
        assertEquals(4.5, SimpleMathLibrary.add(2.25, 2.25), 2);
    }

    @Test
    public void SimpleMathLibraryMinusTest() {
        assertEquals(4.5, SimpleMathLibrary.mines(6.725, 2.25), 2);
    }

    @Test
    public void SimpleMathLibraryChunkTest() {
        int[][] testArrays = {
                {1, 2, 3, 4, 0, 5, 6, 7, 8, 9, 10},
                {1, 1, 2, 4, 5},
                {1, 2, 3, 2, 5, 6, 9, 10},
                {1, 45, 23, 67, 3, 4},
                {4, 5, 4, 5, 4, 5, 4, 5}
        };
        int[][] resultArrays = {
                {5, 6, 7, 8, 9, 10},
                {2, 4, 5},
                {5, 6, 9, 10},
                {4},
                {5},

        };

        for (int i = 0; i < 5; i++) {
            assertArrayEquals(resultArrays[i], SimpleMathLibrary.chunk(testArrays[i], i));
        }
//Да, я знаю что надо было расписать по отдельным тестам, иначе процесс тестирования усложняется
        //   но блин я устал, я всю неделю эту тесты пишу, причем браузерные  на dusk'e. А я не фронт ни разу.  Достало ,честно. давайте сделаем вид что так тоже можно(((
    }

    @Test
    public void SimpleMathLibraryCheckTest() {
        int[][] testArrays = {
                {1, 2, 3, 4,},
                {2, 3, 5, 6},
                {1, 2, 3, 2,},

        };
        boolean[] resultArrays = {
                true,
                false,
                false,

        };

        for (int i = 0; i < 3; i++) {
            assertEquals(resultArrays[i], SimpleMathLibrary.check(testArrays[i]));
        }
    }
}
