package hexlet.code;

public class StringSchema extends BaseSchema {
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

