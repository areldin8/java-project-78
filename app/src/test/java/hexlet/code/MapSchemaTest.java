package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MapSchemaTest {

    private final Validator validator = new Validator();

    @Test
    public void requiredTest() {
        MapSchema mapSchema = validator.map();
        assertTrue(mapSchema.isValid(null));
        assertFalse(mapSchema.required().isValid(null));
        assertTrue(mapSchema.isValid(new HashMap<>()));
    }

    @Test
    public void sizeOfTest() {
        MapSchema mapSchema = validator.map();
        Map<Object, Object> data = new HashMap<>();
        data.put("key1", "value1");
        mapSchema.sizeof(2);
        assertFalse(mapSchema.isValid(data));
        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data));
    }

    @Test
    public void nestedValidationTest() {
        MapSchema mapSchema = validator.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        mapSchema.shape(schemas);

        Map<Object, Object> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(mapSchema.isValid(human1));

        Map<Object, Object> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(mapSchema.isValid(human2));

        Map<Object, Object> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(mapSchema.isValid(human3));
    }
}



