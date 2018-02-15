public class Money implements Expression {
    protected int amount;
    protected String currency;

    public Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        Money money = (Money) o;

        if (!getCurrency().equals(money.getCurrency())) {
            return false;
        }

        return amount == money.getAmount();
    }

    @Override
    public Expression times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }

    public static Money dollar(int amount) {
        return new Money(amount, Bank.USD);
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    @Override
    public Money reduce(Bank bank, String to) {
        int rate = bank.rate(currency, to);
        return new Money(amount / rate, to);
    }

    public static Money franc(int amount) {
        return new Money(amount, Bank.CHF);
    }
}
