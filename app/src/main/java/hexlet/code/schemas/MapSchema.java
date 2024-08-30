package hexlet.code.schemas;

import java.util.Map;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    public MapSchema() {
        addValidator(value -> value instanceof Map<?, ?>);
    }

    public final MapSchema<K, V> required() {
        setRequired(true);
        return this; // поддержка цепочки вызовов
    }

    public final MapSchema<K, V> sizeof(int count) {
        addValidator(value -> ((Map<?, ?>) value).size() == count);
        return this; // поддержка цепочки вызовов
    }

    public final MapSchema<K, V> shape(Map<String, BaseSchema<V>> map) {
        addValidator(value -> {
            if (!(value instanceof Map)) {
                return false;
            }
            Map<?, ?> valueMap = (Map<?, ?>) value;
            for (Map.Entry<String, BaseSchema<V>> entry : map.entrySet()) {
                String key = entry.getKey();
                // Проверка с приведением по ключу
                if (!(entry.getValue().isValid(valueMap.getOrDefault(key, null)))) {
                    return false;
                }
            }
            return true;
        });
        return this; // поддержка цепочки вызовов
    }
}

