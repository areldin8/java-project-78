package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        super.addValidationRule("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        super.addValidationRule("sizeof", value -> value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        super.addValidationRule("shape", value ->
            schemas.entrySet().stream().allMatch(item -> {
                Map<?, ?> map = value;
                Object object = map.get(item.getKey());
                return item.getValue().isValid(object);
            }));
        return this;

    }
}





