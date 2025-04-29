import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonComparatorTest {
    @Test
    public void testIdenticalJsons() throws JSONException {
        String expected = "{\"name\":\"test\",\"value\":42}";
        String actual = "{\"name\":\"test\",\"value\":42}";
        JSONAssert.assertEquals(expected, actual, true);
    }

    @Test
    public void testDifferentJsons() {
        String expected = "{\"name\":\"test\",\"value\":42}";
        String actual = "{\"name\":\"test\",\"value\":43}";
        assertThrows(JSONException.class, () ->
                JSONAssert.assertEquals(expected, actual, true));
    }
}
