public class Franc {
    private int amount;

    public Franc(int amount) {
        this.amount = amount;
    }

    public Franc times(int times) {
        return new Franc(amount * times);
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        return amount == ((Franc) o).getAmount();
    }

    @Override
    public String toString() {
        return "Franc{" +
                "amount=" + amount +
                '}';
    }
}
