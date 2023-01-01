import static org.junit.Assert.*;
import org.junit.Test;

public class withdrawTest{

    @Test
    public void test1() {
        assertEquals(4, RunBank.withdrawFunction(10, 6), 0);
    }

    @Test
    public void test2() {
        assertEquals(11, RunBank.withdrawFunction(10, -1), 0);
    }
    @Test
    public void test3() {
        assertEquals(-1, RunBank.withdrawFunction(10, 11), 0);
    }

    @Test
    public void test4() {
        assertNotSame(452.5, RunBank.withdrawFunction(500, 45.5));
    }

    @Test
    public void test5() {
        assertFalse(RunBank.withdrawFunction(1524, 573.5)>1000);
    }

    @Test
    public void test6() {
        assertTrue(RunBank.withdrawFunction(2379, 1524.5)>1000);
    }
}