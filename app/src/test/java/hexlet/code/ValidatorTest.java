package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ValidatorTest {

    private Validator validator;
    private NumberSchema numberSchema;
    private StringSchema stringSchema;
    private MapSchema mapSchema;


    @BeforeEach
    public void setUp() {
        validator = new Validator();
        numberSchema = validator.number();
        stringSchema = validator.string();
        mapSchema = validator.map();
    }

    @Test
    public void schemaTestRequired() {
        Assertions.assertTrue(stringSchema.isValid("")); // true
        Assertions.assertTrue(stringSchema.isValid(null)); // true

        stringSchema.required();

        Assertions.assertTrue(stringSchema.isValid("what does the fox say")); //true
        Assertions.assertTrue(stringSchema.isValid("hexlet")); // true
        Assertions.assertFalse(stringSchema.isValid(null)); // false
        //Assertions.assertFalse(stringSchema.isValid(5)); // false
        Assertions.assertFalse(stringSchema.isValid("")); // false
    }

    @Test
    public void schemaTestContains() {

        Assertions.assertTrue(stringSchema.contains("wh").isValid("what does the fox say")); // true
        Assertions.assertTrue(stringSchema.contains("what").isValid("what does the fox say")); // true
        Assertions.assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say")); // false
        Assertions.assertFalse(stringSchema.isValid("what does the fox say")); // false
    }

    @Test
    public void schemaTestMinLength() {

        Assertions.assertTrue(stringSchema.minLength(5).isValid("what does the fox say"));
        System.out.println();
        Assertions.assertFalse(stringSchema.minLength(5).isValid("what"));
    }

    @Test
    public void numberTestRequired() {
        Assertions.assertTrue(numberSchema.isValid(null)); // true

        numberSchema.required();

        Assertions.assertFalse(numberSchema.isValid(null)); // false;
        Assertions.assertFalse(numberSchema.isValid(null)); // false
        Assertions.assertTrue(numberSchema.positive().isValid(10)); // true
        Assertions.assertFalse(numberSchema.isValid("5")); // false
        Assertions.assertFalse(numberSchema.positive().isValid(-10)); // false
//  Ноль - не положительное число
        Assertions.assertFalse(numberSchema.positive().isValid(0)); // false
    }

    @Test
    public void numberTestRange() {
        numberSchema.range(5, 10);

        Assertions.assertTrue(numberSchema.isValid(5)); // true
        Assertions.assertTrue(numberSchema.isValid(10)); // true
        Assertions.assertFalse(numberSchema.isValid(4)); // false
        Assertions.assertFalse(numberSchema.isValid(11)); // false
    }

    @Test
    public void mapTestRequired() {

        Assertions.assertTrue(mapSchema.isValid(null)); // true

        mapSchema.required();

        Assertions.assertFalse(mapSchema.isValid(null)); // false
        Assertions.assertTrue(mapSchema.isValid(new HashMap<>())); // true
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        Assertions.assertTrue(mapSchema.isValid(data)); // true

        mapSchema.sizeof(2);

        Assertions.assertFalse(mapSchema.isValid(data));  // false
        data.put("key2", "value2");
        Assertions.assertTrue(mapSchema.isValid(data)); // true
    }

    @Test
    public void nestedSchemaTest() {

        // shape позволяет описывать валидацию для значений каждого ключа объекта Map
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string()
                .required().minLength(2)); // "name" не может быть пустым
        schemas.put("age", validator.number()
                .positive().required()); // "age" не может быть null и должен быть положительным
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        Assertions.assertTrue(mapSchema.isValid(human1)); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        Assertions.assertFalse(mapSchema.isValid(human2)); // false, так как age не может быть null

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        Assertions.assertFalse(mapSchema.isValid(human3)); // false, так как name пустое и age null

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        Assertions.assertFalse(mapSchema.isValid(human4)); // false, так как age отрицательное значение


    }

}

