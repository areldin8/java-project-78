package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StringSchemaTest {

    private final Validator validator = new Validator();

    @Test
    public void minLengthValidationTests() {
        StringSchema testSchema = validator.string();

        assertTrue(testSchema.minLength(0).isValid(""));
        assertTrue(testSchema.minLength(1).isValid("abc"));
        assertTrue(testSchema.minLength(3).isValid("adc"));
        assertFalse(testSchema.minLength(10).isValid("dgf"));
        assertFalse(testSchema.minLength(5).isValid("abc"));
        assertFalse(testSchema.minLength(5).isValid(""));
    }

    @Test
    public void containsValidationTests() {
        StringSchema testSchema = validator.string();

        assertTrue(testSchema.contains("qwe").isValid("qwer"));
        assertTrue(testSchema.contains("").isValid(""));
        assertFalse(testSchema.contains("ac").isValid("cap"));
        assertFalse(testSchema.contains("xyz").isValid("abc"));
        assertFalse(testSchema.contains("abc").isValid(""));
    }

    @Test
    public void requiredValidationTests() {
        StringSchema testSchema = validator.string();

        assertFalse(testSchema.required().isValid(""));
        assertFalse(testSchema.required().isValid(null));
        assertTrue(testSchema.required().isValid("abc"));

    }

    @Test
    public void combinedValidationTests() {
        StringSchema testSchema = validator.string();

        assertTrue(testSchema.required().minLength(10).minLength(1)
                .contains("x").contains("qwe").isValid("qwerty"));
        assertFalse(testSchema.required().minLength(10).minLength(1)
                .contains("x").contains("qwe").isValid("abc"));
    }
}





