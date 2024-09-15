package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {

    @Test
    public void requiredValidationTests() {

        var v = new Validator();
        var testSchema = v.map();

        assertTrue(testSchema.isValid(null));
        assertFalse(testSchema.required().isValid(null));
        assertTrue(testSchema.required().isValid(new HashMap<>()));
        assertTrue(testSchema.required().isValid(Collections.singletonMap("key", "value")));

    }


    @Test
    public void sizeValidationTests() {

        var v = new Validator();
        var testSchema = v.map();

        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(testSchema.sizeof(1).isValid(data));
        assertFalse(testSchema.sizeof(2).isValid(data));
        assertTrue(testSchema.sizeof(1).isValid(Collections.singletonMap("key", "value")));
        assertTrue(testSchema.sizeof(0).isValid(new HashMap<>()));

    }


    @Test
    public void shapeValidationTests() {

        var v = new Validator();
        var testSchema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        testSchema.shape(schemas);
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "Josh");
        human1.put("lastName", "Smith");
        assertTrue(testSchema.isValid(human1));


        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "Josh");
        human2.put("lastName", null);
        assertFalse(testSchema.isValid(human2));


        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(testSchema.isValid(human3));


        Map<String, String> human4 = new HashMap<>();
        human4.put("firstName", "Anna");
        human4.put("lastName", "Brown");
        assertTrue(testSchema.isValid(human4));

    }

    @Test
    public void combinedValidationTests() {

        var v = new Validator();
        var testSchema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        testSchema.required().sizeof(2).shape(schemas);
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "Josh");
        human1.put("lastName", "Smith");
        assertTrue(testSchema.isValid(human1));


        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "Josh");
        human2.put("lastName", null);
        assertFalse(testSchema.isValid(human2));


        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(testSchema.isValid(human3));


        Map<String, String> human4 = new HashMap<>();
        human4.put("firstName", "Anna");
        human4.put("lastName", "Brown");
        assertTrue(testSchema.isValid(human4));

    }
}
