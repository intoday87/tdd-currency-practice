import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bank {
    public static final String CHF = "CHF";
    public static final String USD = "USD";

    private Map<Pair, Integer> rates = new HashMap();

    public Money reduce(Expression source, String currency) {
        return source.reduce(this, currency);
    }

    public void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), rate);
    }

    public int rate(String from, String to) {
        if (from.equals(to)) {
            return 1;
        }
        return rates.get(new Pair(from, to));
    }

    private class Pair {
        private String from;
        private String to;

        public Pair(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return Objects.equals(from, pair.from) &&
                    Objects.equals(to, pair.to);
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
}
