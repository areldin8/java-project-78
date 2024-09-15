package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {

    @Test
    public void requiredValidationTests() {

        Validator v = new Validator();
        var testSchema = v.number();

        assertTrue(testSchema.required().isValid(5));
        assertFalse(testSchema.required().isValid(null));
        assertTrue(testSchema.required().isValid(0));
        assertTrue(testSchema.required().positive().isValid(5));
    }


    @Test
    public void positiveValidationTests() {

        Validator v = new Validator();
        var testSchema = v.number();

        assertTrue(testSchema.positive().isValid(5));
        assertFalse(testSchema.positive().isValid(0));
        assertFalse(testSchema.positive().isValid(-10));
        assertTrue(testSchema.required().positive().isValid(10));
    }


    @Test
    public void rangeValidationTests() {

        Validator v = new Validator();
        var testSchema = v.number();

        assertTrue(testSchema.range(1, 10).isValid(5));
        assertTrue(testSchema.range(0, 5).isValid(0));
        assertTrue(testSchema.range(0, 5).isValid(5));
        assertTrue(testSchema.range(10, 20).range(15, 25).isValid(18));
    }


    @Test
    public void combinedValidationTests() {

        Validator v = new Validator();
        var testSchema = v.number();

        assertTrue(testSchema.required().positive().range(1, 10).isValid(5));
        assertFalse(testSchema.required().positive().isValid(0));
        assertFalse(testSchema.required().range(1, 10).isValid(-1));
        assertTrue(testSchema.required().positive().range(1, 10).isValid(8));
    }
}
