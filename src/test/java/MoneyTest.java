import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTest {

    @Test
    public void testMultiplication() {
        Money five = new Money(5, Bank.USD);
        assertEquals(five.times(10), new Money(50, Bank.USD));
        assertEquals(five.times(15), new Money(75, Bank.USD));
        assertNotEquals(five.times(15), new Money(75, Bank.CHF));
    }

    @Test
    public void testSideEffect() {
        assertSideEffect(5, 5);
        assertSideEffect(1, 7);
    }

    private void assertSideEffect(int amount, int times) {
        Money dollar = new Money(amount, Bank.USD);
        dollar.times(times);
        assertEquals(dollar, new Money(amount, Bank.USD));
    }

    @Test
    public void testCurrency() {
        assertEquals(Bank.USD, new Money(1, Bank.USD).getCurrency());
        assertEquals(Bank.CHF, new Money(1, Bank.CHF).getCurrency());
    }

    @Test
    public void testDifferentClassEquality() {
        assertEquals(new Money(1, Bank.CHF), new Money(1, Bank.CHF));
        assertEquals(new Money(1, Bank.USD), new Money(1, Bank.USD));
        assertNotEquals(new Money(1, Bank.USD), new Money(1, Bank.CHF));
        assertNotEquals(new Money(1, Bank.CHF), new Money(1, Bank.USD));
    }

    @Test
    public void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, Bank.USD);
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    public void testPlusReturnsSum() {
       Money five = Money.dollar(5);
       Expression result = five.plus(five);
       Sum sum = (Sum) result;
       assertEquals(five, sum.augend);
       assertEquals(five, sum.addend);
    }

    @Test
    public void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    @Test
    public void testReduceMoney() {
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1), Bank.USD);
        assertEquals(Money.dollar(1), result);
    }

    @Test
    public void testReduceMoneyDifferentCurrency() {
       Bank bank = new Bank();
       bank.addRate(Bank.CHF, Bank.USD, 2);
       Money result = bank.reduce(Money.franc(2), Bank.USD);
       assertEquals(Money.dollar(1), result);
    }

    @Test
    public void testArrayEquals() { // 교제에서는 왜 실패한다고 나오는지 히스토리를 모르겠다 10년전 책이라 그런가..
        // assertEquals는 deprecated 되었다고 안내한다
        assertArrayEquals(new Object[]{"abc", "efg"}, new Object[]{"abc", "efg"});
        // 아래 단전은 실패한다
//        assertArrayEquals(new Object[]{"abc", "efg"}, new Object[]{"abc", "hij"});
    }

    @Test
    public void testIdentityRate() {
        assertEquals(1, new Bank().rate(Bank.USD, Bank.USD));
    }

    @Test
    public void testMixedAddition() {
        Expression fiveDollar = Money.dollar(5);
        Expression tenFranc = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate(Bank.CHF, Bank.USD, 2);
        Money result = bank.reduce(fiveDollar.plus(tenFranc), Bank.USD);
        assertEquals(result, Money.dollar(10));
    }
}