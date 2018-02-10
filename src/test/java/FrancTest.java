import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class FrancTest {
    @Test
    public void testMultiplication() throws Exception {
        assertThat(new Franc(5).times(10), equalTo(new Franc(50)));
        assertThat(new Franc(1).times(3), not(equalTo(new Franc(5))));
    }
}
