package Batch44N.day11;

import Batch44N.testBase.JsonPlaceHolderTestBase;
import Batch44N.testData.JPHTestData;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest03 extends JsonPlaceHolderTestBase {
    /*https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
}
"userId": 55,
"title": "Tidy your room", "completed": false
}
Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
{
"userId": 55,
"title": "Tidy your room", "completed": false,
"id": ...
}
     */
    @Test
    public void test01(){
        specJPH.pathParam("param1","todos");

        JPHTestData jphTestData=new JPHTestData();
        JSONObject expectedRequest=jphTestData.setupPostRequest02();
        System.out.println("expectedRequest = " + expectedRequest);

        Response response=given().contentType(ContentType.JSON)
                .spec(specJPH)
                .auth().basic("admin","password123")
                .body(expectedRequest.toString())
                .when().post("/{param1}");
        response.prettyPrint();

        //Matchers Class
        response.then().assertThat()//.statusCode(expectedRequest.getInt("statusCode"))
                .body("completed", equalTo(expectedRequest.getBoolean("completed")),
                "title",equalTo(expectedRequest.getString("title")),
                       "userId",equalTo(expectedRequest.getInt("userId")));


        //JsonPath ile
        JsonPath jsonPath=response.jsonPath();
        System.out.println("json Path   : "+ jsonPath.toString());
        // Assert.assertEquals(expectedRequest.getInt("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedRequest.getInt("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expectedRequest.getString("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),jsonPath.getBoolean("completed"));

        //Deserilization
        HashMap<String , Object> actualDataMap=response.as(HashMap.class);   //responsedan geleni al kaliba uygun aktar
        // Assert.assertEquals(expectedRequest.getInt("statusCode"), Matchers.equalTo(""));
        Assert.assertEquals(expectedRequest.getString("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedRequest.getInt("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedRequest.getBoolean("completed"),actualDataMap.get("completed"));


    }
}
