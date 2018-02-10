public class Money {
    protected int amount;

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        Money money = (Money) o;
        return amount == money.getAmount();
    }
}
