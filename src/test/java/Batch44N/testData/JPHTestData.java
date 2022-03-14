package Batch44N.testData;

import java.util.HashMap;
import java.util.Map;

public class JPHTestData {

    public Map<String, Object> setupTestData() {


        HashMap<String, Object> expectedData = new HashMap<String, Object>(); // key-value var Map, Object data type

        expectedData.put("statusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

        return expectedData;
    }
}
