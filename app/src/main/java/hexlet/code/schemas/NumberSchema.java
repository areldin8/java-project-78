package hexlet.code.schemas;


public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        super.addValidationRule("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int lowRange, int highRange) {
        super.addValidationRule("range", value -> value == null || (value >= lowRange && value <= highRange));
        return this;
    }
}









