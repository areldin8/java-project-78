package hexlet.code.schemas;

import java.util.HashMap;

import java.util.Map;
import java.util.function.Predicate;




public abstract class BaseSchema<T> {

    private final Map<String, Predicate<Object>> validationRules = new HashMap<>();

    protected final void addValidationRule(String checkType, Predicate<Object> validationPredicate) {
        this.validationRules.put(checkType, validationPredicate);
    }

    public final boolean isValid(Object object) {
        return validationRules.values().stream().allMatch(predicate -> predicate.test(object));
    }
}













