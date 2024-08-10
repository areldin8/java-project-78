package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class StringSchema {
    private List<Predicate<String>> rules = new ArrayList<>();
    private boolean isRequired = false;
    private int minLength = -1;  // -1 обозначает, что ограничение не установлено
    private String contains = null; // null обозначает, что ограничение не установлено

    public StringSchema required() {
        isRequired = true;
        rules.add(str -> str != null && !str.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        rules.add(str -> str.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        contains = substring;
        rules.add(str -> str.contains(contains));
        return this;
    }

    public boolean isValid(String value) {
        // Проверка на обязательность
        if (isRequired && (value == null || value.isEmpty())) {
            return false;
        }

        // Проверка на минимальную длину
        if (minLength != -1 && value.length() < minLength) {
            return false;
        }

        // Проверка на содержание подстроки
        if (contains != null && !value.contains(contains)) {
            return false;
        }

        // Проверка всех добавленных правил
        for (Predicate<String> rule : rules) {
            if (!rule.test(value)) {
                return false;
            }
        }
        return true;
    }
}