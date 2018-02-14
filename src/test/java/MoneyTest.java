import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MoneyTest {

    private static final String USD = "USD";
    private static final String CHF = "CHF";

    @Test
    public void testMultiplication() {
        Money five = new Money(5, USD);
        assertEquals(five.times(10), new Money(50, USD));
        assertEquals(five.times(15), new Money(75, USD));
        assertNotEquals(five.times(15), new Money(75, CHF));
    }

    @Test
    public void testSideEffect() {
        assertSideEffect(5, 5);
        assertSideEffect(1, 7);
    }

    private void assertSideEffect(int amount, int times) {
        Money dollar = new Money(amount, USD);
        dollar.times(times);
        assertEquals(dollar, new Money(amount, USD));
    }

    @Test
    public void testCurrency() {
        assertEquals(USD, new Money(1, USD).getCurrency());
        assertEquals(CHF, new Money(1, CHF).getCurrency());
    }

    @Test
    public void testDifferentClassEquality() {
        assertEquals(new Money(1, CHF), new Money(1, CHF));
        assertEquals(new Money(1, USD), new Money(1, USD));
        assertNotEquals(new Money(1, USD), new Money(1, CHF));
        assertNotEquals(new Money(1, CHF), new Money(1, USD));
    }

    @Test
    public void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, USD);
        assertEquals(Money.dollar(10), reduced);
    }
}