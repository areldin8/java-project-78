package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {

    private final Validator validator = new Validator();

    @Test
    public void requiredValidationTest() {
        NumberSchema numberSchema = validator.number();
        assertTrue(numberSchema.isValid(null));
        assertFalse(numberSchema.required().isValid(null));
    }

    @Test
    public void positiveValidationTests() {
        NumberSchema numberSchema = validator.number();
        var positiveSchema = numberSchema.positive();
        assertTrue(positiveSchema.isValid(null));
        assertTrue(positiveSchema.isValid(5));
        assertFalse(positiveSchema.isValid(0));
    }

    @Test
    public void rangeValidationTests() {
        NumberSchema numberSchema = validator.number();
        var inRangeSchema = numberSchema.range(5, 10);
        assertTrue(inRangeSchema.isValid(null));
        assertTrue(inRangeSchema.isValid(5));
        assertTrue(inRangeSchema.isValid(10));
        assertFalse(inRangeSchema.isValid(4));
        assertFalse(inRangeSchema.isValid(11));
    }

    @Test
    public void combinedValidationTest() {
        NumberSchema numberSchema = validator.number();
        NumberSchema actual = numberSchema.positive();
        assertTrue(actual.isValid(null));
        actual.required();
        assertFalse(actual.isValid(null));
        assertFalse(actual.isValid(-10));
        assertTrue(actual.isValid(10));
        actual.range(-5, 7);
        assertFalse(actual.isValid(-4));
        assertTrue(actual.isValid(6));
    }
}


