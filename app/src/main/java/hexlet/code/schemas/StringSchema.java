package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        super.addValidationRule("required",
                value -> value != null && !((String) value).isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        super.addValidationRule("minLength",
                value -> value instanceof String && ((String) value).length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        super.addValidationRule("contains",
                value -> value instanceof String && ((String) value).contains(substring));
        return this;
    }

}



