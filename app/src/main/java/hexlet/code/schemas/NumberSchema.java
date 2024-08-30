package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Double> {
    public NumberSchema() {
        addValidator(value -> value instanceof Number);
    }

    public final NumberSchema required() {
        setRequired(true);
        return this;
    }

    public final NumberSchema positive() {
        addValidator(value -> ((Number) value).doubleValue() > 0);
        return this;
    }

    public final NumberSchema range(double lowRange, double highRange) {
        addValidator(value -> {
            double numberValue = ((Number) value).doubleValue();
            return numberValue >= lowRange && numberValue <= highRange;
        });
        return this;  // Поддержка цепочки вызовов
    }
}


