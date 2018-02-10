public class Dollar {
    private int amount;

    public Dollar(int amount) {
        this.amount = amount;
    }

    public Dollar times(int times) {
        return new Dollar(amount * times);
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        return amount == ((Dollar) o).getAmount();
    }

    @Override
    public String toString() {
        return "Dollar{" +
                "amount=" + amount +
                '}';
    }
}
