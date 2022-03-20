package Batch44N.day11;

import Batch44N.testBase.JsonPlaceHolderTestBase;
import Batch44N.testData.JPHTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;


public class PutRequest01 extends JsonPlaceHolderTestBase {
    /*  https://jsonplaceholder.typicode.com/todos/198
    URL ine aşağıdaki body gönerdiğimde
    {       "userId": 21,
        "title": "Wash the dishes", "completed": false
    }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    {       "userId": 21,
        "title": "Wash the dishes", "completed": false,
        "id": 198
    }
     */
@Test
    public void test01(){
        specJPH.pathParams("param1","todos","param2",198);

    JPHTestData testObj=new JPHTestData();
    JSONObject expectedRequest=testObj.setupPutData();
    System.out.println("expectedRequest = " + expectedRequest);

    Response response=given().contentType(ContentType.JSON)
                            .spec(specJPH)
                     .auth().basic("admin","password123")
                     .body(expectedRequest.toString())
                     .when().put("/{param1}/{param2}");
    response.prettyPrint();

    // JsonPath
    JsonPath jsonPath=response.jsonPath();
    Assert.assertEquals(200,response.getStatusCode());
    Assert.assertEquals(expectedRequest.getInt("userId"),jsonPath.getInt("userId"));
    Assert.assertEquals(expectedRequest.getString("title"),jsonPath.getString("title"));
    Assert.assertEquals(expectedRequest.getBoolean("completed"),jsonPath.getBoolean("completed"));


}


}
