package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;


public class BaseSchema<T> {

    private final Map<String, Predicate<T>> validationRules = new HashMap<>();
    /**
     * Добавляет правило валидации, которое требует, чтобы значение было не null.
     *
     * @return этот объект для построения цепочки вызовов
     */
    public BaseSchema<T> required() {
        addValidationRule("required", Objects::nonNull);
        return this;
    }

    protected final void addValidationRule(String checkType, Predicate<T> validationPredicate) {
        this.validationRules.put(checkType, validationPredicate);
    }

    public final boolean isValid(T data) {
        if (!validationRules.containsKey("required") && data == null) {
            return true;
        }
        if (validationRules.containsKey("required") && data == null) {
            return false;
        }
        for (var rule : validationRules.values()) {
            if (!rule.test(data)) {
                return false;
            }
        }
        return true;
    }
}














