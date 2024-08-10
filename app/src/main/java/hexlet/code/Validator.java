package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Validator {
    public StringSchema string() {
        return new StringSchema();
    }

    public static void main(String[] args) {
        Validator v = new Validator();

        // Создание схемы
        StringSchema schema = v.string();

        // Пока не вызван метод required(), null и пустая строка считаются валидным
        System.out.println(schema.isValid("")); // true
        System.out.println(schema.isValid(null)); // true

        schema.required();

        System.out.println(schema.isValid(null)); // false
        System.out.println(schema.isValid("")); // false
        System.out.println(schema.isValid("what does the fox say")); // true
        System.out.println(schema.isValid("hexlet")); // true

        // Проверка на содержание подстроки
        System.out.println(schema.contains("wh").isValid("what does the fox say")); // true
        System.out.println(schema.contains("what").isValid("what does the fox say")); // true
        System.out.println(schema.contains("whatthe").isValid("what does the fox say")); // false

        // Если добавлена проверка
        System.out.println(schema.isValid("what does the fox say")); // false
        // Здесь уже false, так как добавлена еще одна проверка contains("whatthe")

        // Если один валидатор вызывался несколько раз
        var schema1 = v.string();
        System.out.println(schema1.minLength(10).minLength(4).isValid("Hexlet")); // true
    }
}