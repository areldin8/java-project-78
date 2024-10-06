package hexlet.code.schemas;


public final class StringSchema extends BaseSchema<String> {

    @Override
    public StringSchema required() {
        super.addValidationRule("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        super.addValidationRule("minLength", value -> value == null || value.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        super.addValidationRule("contains", value -> value == null || value.contains(substring));
        return this;
    }
}






