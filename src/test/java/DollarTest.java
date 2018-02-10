import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

public class DollarTest {

    @Test
    public void testMultiplication() throws Exception {
        assertThat(new Dollar(5).times(10), equalTo(new Dollar(50)));
        assertThat(new Dollar(1).times(3), not(equalTo(new Dollar(5))));
    }

    @Test
    public void testSideEffect() throws Exception {
        assertSideEffect(5, 5);
        assertSideEffect(1, 7);
    }

    private void assertSideEffect(int amount, int times) {
        Dollar dollar = new Dollar(amount);
        dollar.times(times);
        assertThat(dollar, equalTo(new Dollar(amount)));
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(new Dollar(5), new Dollar(5));
        assertThat(new Dollar(5), not(equalTo(new Dollar(3))));
    }
}