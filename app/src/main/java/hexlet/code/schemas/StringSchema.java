package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    public StringSchema() {
        addValidator(value -> value instanceof String);
    }

    public final StringSchema required() {
        setRequired(true);
        return this;
    }

    public final StringSchema minLength(int minLength) {
        addValidator(value -> value.toString().length() >= minLength);
        return this;
    }

    public final StringSchema contains(String string) {
        addValidator(value -> value.toString().contains(string));
        return this;
    }
}

