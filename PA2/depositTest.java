import static org.junit.Assert.*;
import org.junit.Test;

public class depositTest{

    @Test
    public void test1() {
        assertEquals(16, RunBank.depositFunction(10, 6), 0);
    }

    @Test
    public void test2() {
        assertEquals(9, RunBank.depositFunction(10, -1), 0);
    }

    @Test
    public void test3() {
        assertNotSame(63, RunBank.depositFunction(500, 3.5));
    }

    @Test
    public void test4() {
        assertFalse(RunBank.depositFunction(40, 73.5)>1000);
    }

    @Test
    public void test5() {
        assertTrue(RunBank.depositFunction(40, 73.5)>1000);
    }
}