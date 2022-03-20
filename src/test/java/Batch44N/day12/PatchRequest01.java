package Batch44N.day12;

import Batch44N.testBase.JsonPlaceHolderTestBase;
import Batch44N.testData.JPHTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import javax.naming.ldap.HasControls;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends JsonPlaceHolderTestBase {
    /*  https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki 
    body gönderdiğimde {
                            "title": "API calismaliyim"
                        }
Dönen response un status kodunun 200 ve 
body kısmının aşağıdaki gibi olduğunu test edin
{
"userId": 10,
"title": "API calismaliyim" "completed": true,
"id": 198
}                     */
    @Test
    public void test01(){
        specJPH.pathParams("param1","todos","param2",198);
        // expected ve request data olustur

        JPHTestData testDataJsonO=new JPHTestData();

        JSONObject requestDataJsonO   =   testDataJsonO.setupPatchRequestData();
        System.out.println("requestData = " + requestDataJsonO);

        JSONObject expectedDataJsonO=testDataJsonO.setupPatchExpectedData();
        System.out.println("expectedDataJsonO = " + expectedDataJsonO);
        
        //request gonder
        Response response=given().
                contentType(ContentType.JSON).
                spec(specJPH)
                //.auth().basic("admin","password123")
                .body(requestDataJsonO.toString())   //JsonObject toString ile okunabiliyor
                .when().patch("/{param1}/{param2}");
        response.prettyPrint();

        //De serailization
        HashMap<String , Object> actualDataMap=response.as(HashMap.class);
        // calismiyor Assert.assertEquals(expectedDataJsonO.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedDataJsonO.getInt("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataJsonO.getInt("id"),actualDataMap.get("id"));
        Assert.assertEquals(expectedDataJsonO.getString("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedDataJsonO.getBoolean("completed"),actualDataMap.get("completed"));

    }
}
