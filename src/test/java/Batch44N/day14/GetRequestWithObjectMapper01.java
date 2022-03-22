package Batch44N.day14;

import Batch44N.testBase.JsonPlaceHolderTestBase;
import Batch44N.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjectMapper01 extends JsonPlaceHolderTestBase {
    /*   https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
         Dönen response ‘un status kodunun 200 ve body kısmının
         {       "userId": 10,
                 "id": 198,
                 "title": "quis eius est sint explicabo",
                 "completed": true
         }
        Olduğunu Object Mapper kullanarak test edin */
    @Test
    public void test01() {
        specJPH.pathParams("param1", "todos",
                "param2", 198);

        //expected data olusturalim
        String jsonData = "{\"userId\": 10,\n" +
                "             \"id\": 198,\n" +
                "             \"title\": \"quis eius est sint explicabo\",\n" +
                "             \"completed\": true\n" +
                "     }";

        // metoda goondermeliyim, mape yazdir
        Map<String, Object> expectedDataMap = JsonUtil.convertJsonToJava(jsonData, Map.class);  // json datasi gonder ve map gibi
        //Hashmap de olabilirdi
        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response = given().contentType(ContentType.JSON)
                .spec(specJPH).when().get("/{param1}/{param2}");

        response.prettyPrint();

//JsonUtilden degeri al. string gonder. response'a asString stringe cevir.Map.classa cevir
        Map<String, Object> actualDataMap=JsonUtil.convertJsonToJava(response.asString(), Map.class);
        System.out.println(" actualDataMap  : "+actualDataMap);

        Assert.assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("id"),actualDataMap.get("id"));
        Assert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));


    }
}
