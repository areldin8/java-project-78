package hexlet.code.schemas;


import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        super.addValidationRule("required",
                Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        super.addValidationRule("positive",
                value -> value == null || ((Integer) value) > 0);
        return this;
    }

    public NumberSchema range(int lowRange, int highRange) {
        super.addValidationRule("range",
                value -> value != null && ((Integer) value) >= lowRange && ((Integer) value) <= highRange);
        return this;
    }
}






