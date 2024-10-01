package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StringSchemaTest {

    @Test
    public void minLengthValidationTests() {
        var v = new Validator();
        var testSchema = v.string();

        assertTrue(testSchema.minLength(0).isValid(""));
        assertTrue(testSchema.minLength(1).isValid("abc"));
        assertTrue(testSchema.minLength(3).isValid("adc"));
        assertFalse(testSchema.minLength(10).isValid("dgf"));
    }

    @Test
    public void containsValidationTests() {
        var v = new Validator();
        var testSchema = v.string();

        assertTrue(testSchema.contains("qwe").isValid("qwer"));
        assertTrue(testSchema.contains("").isValid(""));
        assertFalse(testSchema.contains("ac").isValid("cap"));
    }

    @Test
    public void requiredValidationTests() {
        var v = new Validator();
        var testSchema = v.string();

        assertFalse(testSchema.required().isValid(""));
        assertFalse(testSchema.required().isValid(null));
        assertTrue(testSchema.required().isValid("abc"));
    }

    @Test
    public void combinedValidationTests() {
        var v = new Validator();
        var testSchema = v.string();

        assertTrue(testSchema.required().minLength(10).minLength(1)
                .contains("x").contains("qwe").isValid("qwerty"));
    }
}





