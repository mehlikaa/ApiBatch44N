package Batch44N.testData;

import io.restassured.path.json.JsonPath;
import org.json.JSONObject;

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

    public JSONObject setupPostRequest02(){
        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("userId",55);
        expectedRequest.put("title","Tidy your room");
        expectedRequest.put("completed", false);
        //expectedRequest.put("statusCode", 201);

        return expectedRequest;
    }

    public JSONObject setupPutData(){
        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("userId",21);
        expectedRequest.put("title","Wash the dishes");
        expectedRequest.put("completed",false);

        return expectedRequest;
    }
}
