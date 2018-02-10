public class Dollar extends Money {

    public Dollar(int amount) {
        this.amount = amount;
    }

    public Dollar times(int times) {
        return new Dollar(amount * times);
    }

    @Override
    public String toString() {
        return "Dollar{" +
                "amount=" + amount +
                '}';
    }
}
