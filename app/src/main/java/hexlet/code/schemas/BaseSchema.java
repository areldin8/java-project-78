package hexlet.code.schemas;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public abstract class BaseSchema {
    protected List<Predicate<Object>> validators = new ArrayList<>();
    protected boolean isRequired = false;

    public void setRequired(boolean required) {
        this.isRequired = required;
    }

    public void addValidator(Predicate<Object> validator) {
        if (validator != null) {
            validators.add(validator);
        }
    }
    public boolean isValid(Object object) {
        if (isNullOrEmpty(object)) {
            return !isRequired;
        }

        return validators.stream().allMatch(validator -> validator.test(object));
    }

    private boolean isNullOrEmpty(Object obj) {
        return obj == null || (obj instanceof String && ((String) obj).isEmpty());
    }
}


