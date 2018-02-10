public class Franc extends Money {

    public Franc(int amount) {
        this.amount = amount;
    }

    public Franc times(int times) {
        return new Franc(amount * times);
    }

    @Override
    public String toString() {
        return "Franc{" +
                "amount=" + amount +
                '}';
    }
}
