package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class NumberSchema extends BaseSchema<Number> {
    private boolean isPositive = false;
    private Number minValue = null;
    private Number maxValue = null;

    public NumberSchema positive() {
        isPositive = true;
        addRule(value -> value instanceof Number && ((Number) value).doubleValue() > 0);
        return this;
    }

    public NumberSchema range(Number min, Number max) {
        minValue = min;
        maxValue = max;
        addRule(value -> value instanceof Number &&
                ((Number) value).doubleValue() >= min.doubleValue() &&
                ((Number) value).doubleValue() <= max.doubleValue());
        return this;
    }

    @Override
    public boolean isValid(Number value) {
        if (!super.isValid(value)) {
            return false;
        }

        // Check positive rule separately, as it's not general
        if (isPositive && (value instanceof Number && ((Number) value).doubleValue() <= 0)) {
            return false;
        }

        return true;
    }
}