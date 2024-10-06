package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema sizeof(int size) {
        super.addValidationRule("sizeof", map -> {
            if (map == null) {
                return true;
            }
            return map.size() == size;
        });
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        super.addValidationRule("shape", map -> {
            if (map == null) {
                return true;
            }
            for (var entry : schemas.entrySet()) {
                var value = map.get(entry.getKey());
                var schema = entry.getValue();
                if (!schema.isValid((T) value)) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
