package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {

    @Test
    public void requiredValidationTests() {
        var v = new Validator();
        var testSchema = v.number();

        assertFalse(testSchema.required().isValid(null));
        assertTrue(testSchema.required().isValid(5));
    }

    @Test
    public void positiveValidationTests() {
        var v = new Validator();
        var testSchema = v.number();

        assertTrue(testSchema.positive().isValid(5));
        assertFalse(testSchema.positive().isValid(0));
        assertFalse(testSchema.positive().isValid(-5));
    }

    @Test
    public void rangeValidationTests() {
        var v = new Validator();
        var testSchema = v.number();

        assertTrue(testSchema.range(1, 10).isValid(4));
        assertTrue(testSchema.range(0, 5).isValid(0));
        assertTrue(testSchema.range(0, 5).isValid(5));
        assertFalse(testSchema.range(5, 10).isValid(3));
        assertFalse(testSchema.range(5, 10).isValid(15));
        assertTrue(testSchema.range(-15, 0).isValid(-4));
    }

    @Test
    public void combinedValidationTests() {
        var v = new Validator();
        var testSchema = v.number();

        assertTrue(testSchema.required().positive().range(0, 4).
                range(15, 20).isValid(17));
    }
}


