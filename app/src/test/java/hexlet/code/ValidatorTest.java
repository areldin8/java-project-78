package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ValidatorTest {

    @Test
    public void stringSchemaTest() {
        Validator v = new Validator();
        var testSchema = v.string();

        assertTrue(testSchema.isValid(""));
        assertTrue(testSchema.isValid(null));
        assertTrue(testSchema.minLength(1).isValid("abc"));
        assertTrue(testSchema.minLength(3).isValid("bcd"));
        assertTrue(testSchema.minLength(0).isValid(""));
        assertTrue(testSchema.contains("abc").isValid("abcd"));
        assertTrue(testSchema.contains("").isValid(""));
        assertTrue(testSchema.required().minLength(10).minLength(1)
                .contains("f").contains("abc").isValid("abcdef"));

        assertFalse(testSchema.required().isValid(""));
        assertFalse(testSchema.required().isValid(null));
        assertFalse(testSchema.minLength(10).isValid("bcd"));
        assertFalse(testSchema.contains("cd").isValid("gcd"));
    }

    @Test
    public void numberSchemaTest() {
        Validator v = new Validator();
        var testSchema = v.number();

        assertTrue(testSchema.isValid(5));
        assertTrue(testSchema.isValid(null));
        assertFalse(testSchema.required().isValid(null));
        assertTrue(testSchema.required().isValid(5));
        assertTrue(testSchema.positive().isValid(5));
        assertFalse(testSchema.positive().isValid(0));
        assertFalse(testSchema.positive().isValid(-5));

        var v2 = new Validator();
        var testSchema2 = v2.number();

        assertTrue(testSchema2.range(1, 10).isValid(4));
        assertTrue(testSchema2.range(0, 5).isValid(0));
        assertTrue(testSchema2.range(0, 5).isValid(5));
        assertFalse(testSchema2.range(5, 10).isValid(3));
        assertFalse(testSchema2.range(5, 10).isValid(15));
        assertTrue(testSchema2.required().positive().range(0, 4)
                .range(25, 50).isValid(29));

        var v3 = new Validator();
        var testSchema3 = v3.number();

        assertTrue(testSchema3.range(-15, 0).isValid(-4));
    }

    @Test
    public void mapSchemaTest() {
        Validator v = new Validator();
        var testSchema = v.map();

        assertTrue(testSchema.isValid(null));
        assertFalse(testSchema.required().isValid(null));
        assertTrue(testSchema.isValid(new HashMap<>()));

        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        assertTrue(testSchema.isValid(data));
        assertFalse(testSchema.sizeof(2).isValid(data));

        data.put("key2", "value2");
        assertTrue(testSchema.isValid(data));

        var data2 = new Validator();
        var testSchema2 = data2.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", data2.string().required());
        schemas.put("lastName", data2.string().required().minLength(2));

        testSchema2.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "Josh");
        human1.put("lastName", "Smith");
        assertTrue(testSchema2.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "Josh");
        human2.put("lastName", null);
        assertFalse(testSchema2.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(testSchema2.isValid(human3));
    }
}


