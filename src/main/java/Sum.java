public class Sum implements Expression {
    public Expression augend;
    public Expression addend;

    public Sum(Expression augend, Expression addend) {
        this.augend = augend;
        this.addend = addend;
    }

    public Money reduce(Bank bank, String to) {
        return new Money(augend.reduce(bank, to).getAmount() + addend.reduce(bank, to).getAmount(), to);
    }

    @Override
    public Expression plus(Expression source) {
        return new Sum(new Sum(augend, addend), source);
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(addend.times(multiplier), addend.times(multiplier));
    }
}
