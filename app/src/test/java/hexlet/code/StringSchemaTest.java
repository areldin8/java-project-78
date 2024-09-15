package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StringSchemaTest {

    @Test
    public void minLengthValidationTests() {

        Validator v = new Validator();
        var testSchema = v.string();

        assertTrue(testSchema.minLength(0).isValid(""));
        assertTrue(testSchema.minLength(1).isValid("a"));
        assertTrue(testSchema.minLength(3).isValid("abc"));
        assertTrue(testSchema.minLength(2).minLength(5).isValid("abcde"));
    }

    @Test
    public void requiredValidationTests() {

        Validator v = new Validator();
        var testSchema = v.string();

        assertFalse(testSchema.required().isValid(null));
        assertFalse(testSchema.required().isValid(""));
        assertTrue(testSchema.required().isValid("valid"));
        assertTrue(testSchema.required().minLength(2).isValid("valid string"));
    }

    @Test

    public void containsValidationTests() {

        Validator v = new Validator();
        var testSchema = v.string();

        assertTrue(testSchema.contains("a").isValid("abc"));
        assertTrue(testSchema.contains("b").isValid("bcd"));
        assertTrue(testSchema.contains("").isValid("anything"));
        assertTrue(testSchema.contains("x").contains("y").isValid("xyz"));

    }

    @Test

    public void combinedValidationTests() {

        Validator v = new Validator();
        var testSchema = v.string();

        assertTrue(testSchema.required().minLength(5).contains("e").isValid("abcdef"));
        assertFalse(testSchema.required().minLength(10).isValid("short"));
        assertFalse(testSchema.required().contains("hello").isValid("hi"));
        assertTrue(testSchema.required().minLength(3).contains("x").isValid("xyzabc"));
    }
}




