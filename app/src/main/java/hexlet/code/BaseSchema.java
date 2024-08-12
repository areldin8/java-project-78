package hexlet.code;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected List<Predicate<T>> rules = new ArrayList<>();
    protected boolean isRequired = false;

    protected void addRule(Predicate<T> rule) {
        rules.add(rule);
    }

    public boolean isValid(T value) {
        if (isRequired && (value == null)) {
            return false;
        }

        for (Predicate<T> rule : rules) {
            if (!rule.test(value)) {
                return false;
            }
        }

        return true;
    }

    public BaseSchema<T> required() {
        isRequired = true;
        addRule(value -> value != null);
        return this;
    }
}

