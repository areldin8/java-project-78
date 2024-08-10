package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    @Test
    public void testValidations() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        // Тесты на пустую строку и null, когда required() не вызван
        assertTrue(schema.isValid("")); // true
        assertTrue(schema.isValid(null)); // true

        // Теперь добавляем требование обязательности
        schema.required();
        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("")); // false
        assertTrue(schema.isValid("valid string")); // true

        // Добавляем проверки для minLength и contains
        schema.minLength(5);
        assertFalse(schema.isValid("abc")); // false (менее 5 символов)
        assertTrue(schema.isValid("valid")); // true
        schema.contains("valid");
        assertTrue(schema.isValid("valid string")); // true
        assertFalse(schema.isValid("string")); // false (не содержит "valid")
    }

    @Test
    public void testMultipleValidators() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.minLength(10).minLength(4);
        assertTrue(schema.isValid("HelloWorld")); // true
        assertTrue(schema.isValid("test")); // true
    }

}