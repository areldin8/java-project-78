package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema {
    public MapSchema() {
        addValidator(value -> value instanceof Map<?, ?>);
    }

    public final void required() {
        setRequired(true);
    }

    public final void sizeOf(int count) {
        addValidator(value -> ((Map<?, ?>) value).size() == count);
    }

    public final void shape(Map<String, BaseSchema> map) {
        addValidator(value -> {
            for (Map.Entry<String, BaseSchema> entry : map.entrySet()) {
                String key = entry.getKey();
                if (!(entry.getValue().isValid(((Map<?, ?>) value).getOrDefault(key, null)))) {
                    return false;
                }
            }
            return true;
        });
    }
}
