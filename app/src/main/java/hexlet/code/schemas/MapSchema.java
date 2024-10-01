package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        super.addValidationRule("sizeof", value ->
                value == null || value.size() == size);
        return this;
    }


    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        super.addValidationRule("shape", value ->
                value == null || schemas.entrySet().stream().allMatch(item ->
                        item.getValue().isValid(value.get(item.getKey()))));
        return this;
    }
}



