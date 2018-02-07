import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class TestTest {
    @Test
    public void testMultiplication() throws Exception {
       Dallar five = new Dollar(5);
       five.times(2);
       assertThat(five.amount, equalTo(10));
    }
}