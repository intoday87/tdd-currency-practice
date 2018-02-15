import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTest {

    Expression fiveDollar;
    Expression tenFranc;
    Money dollarMoney;
    Money francMoney;
    Bank bank;

    @Before
    public void setUp() throws Exception {
        fiveDollar = Money.dollar(5);
        tenFranc = Money.franc(10);
        dollarMoney = Money.dollar(1);
        francMoney = Money.franc(1);
        bank = new Bank();
        bank.addRate(Bank.CHF, Bank.USD, 2);
    }

    @Test
    public void testMultiplication() {
        assertEquals(fiveDollar.times(10), Money.dollar(50));
        assertEquals(fiveDollar.times(15), Money.dollar(75));
        assertNotEquals(fiveDollar.times(15), Money.franc(75));
    }

    @Test
    public void testSideEffect() {
        assertSideEffect(5, 5);
        assertSideEffect(1, 7);
    }

    private void assertSideEffect(int amount, int times) {
        Money dollar = Money.dollar(amount);
        dollar.times(times);
        assertEquals(dollar, Money.dollar(amount));
    }

    @Test
    public void testCurrency() {
        assertEquals(Bank.USD, dollarMoney.getCurrency());
        assertEquals(Bank.CHF, francMoney.getCurrency());
    }

    @Test
    public void testDifferentClassEquality() {
        assertEquals(francMoney, francMoney);
        assertEquals(dollarMoney, dollarMoney);
        assertNotEquals(dollarMoney, francMoney);
        assertNotEquals(francMoney, dollarMoney);
    }

    @Test
    public void testSimpleAddition() {
        Expression sum = fiveDollar.plus(fiveDollar);
        Money reduced = bank.reduce(sum, Bank.USD);
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    public void testPlusReturnsSum() {
        Expression result = fiveDollar.plus(fiveDollar);
        Sum sum = (Sum) result;
        assertEquals(fiveDollar, sum.augend);
        assertEquals(fiveDollar, sum.addend);
    }

    @Test
    public void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    @Test
    public void testReduceMoney() {
        Money result = bank.reduce(dollarMoney, Bank.USD);
        assertEquals(dollarMoney, result);
    }

    @Test
    public void testReduceMoneyDifferentCurrency() {
        Money result = bank.reduce(Money.franc(2), Bank.USD);
        assertEquals(dollarMoney, result);
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
        Money result = bank.reduce(fiveDollar.plus(tenFranc), Bank.USD);
        assertEquals(result, Money.dollar(10));
    }

    @Test
    public void testSumPlusMoney() {
        Expression sum = new Sum(fiveDollar, tenFranc).plus(fiveDollar);
        Money result = bank.reduce(sum, Bank.USD);
        assertEquals(result, Money.dollar(15));
    }

    @Test
    public void testSumTimes() {
        Expression sum = new Sum(fiveDollar, tenFranc).times(2);
        assertEquals(sum.reduce(bank, Bank.USD), Money.dollar(20));
    }
}