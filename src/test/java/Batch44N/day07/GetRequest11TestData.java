package Batch44N.day07;

import Batch44N.testBase.JsonPlaceHolderTestBase;
import Batch44N.testData.JPHTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequestTestData extends JsonPlaceHolderTestBase {
    @Test
    public void test01(){
        specJPH.pathParams("param1","todos","param2",2);
        Response response=given().
                accept(ContentType.JSON).
                spec(specJPH).
                when().get("/{param1}/{param2}");

        //response.prettyPrint();

        JPHTestData expectedObj=new JPHTestData();
        HashMap<String, Object> expectedData=(HashMap<String, Object>)expectedObj.setupTestData();

        System.out.println("expectedData = " + expectedData);


//map olusturdum, sonra disarida bir classa cikaricam, metodumdan test datasi olmayacak Dinamik data
        // hashmap elemanlari karisik koyuyordu ve hizliydi. Assertionda dinamik kullanabilmek icin

        // 1. yontem -> Matchers classsi ile
        response.then().assertThat()
                .statusCode((int)expectedData.get("statusCode"))
                .headers("Via", equalTo(expectedData.get("Via")),
                        "Server",equalTo(expectedData.get("Server")))
                .body("userId",equalTo(expectedData.get("userId")),
                        "title",equalTo(expectedData.get("title")),
                        "completed",equalTo(expectedData.get("completed")));

        // 2. yontem -> jsonPath  ile
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(expectedData.get("statusCode"),response.statusCode());
        Assert.assertEquals(expectedData.get("Via"),response.header("Via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));   // body disindakiler response ile alinir.
        Assert.assertEquals(expectedData.get("userId"),jsonPath.getInt("userId"));  //jsonpath sadece bodyyi saklardi
        Assert.assertEquals(expectedData.get("completed"),jsonPath.getBoolean("completed"));

        // 3. yontem  ==> De-Serialization ; iki yontem ile  1-object mapper    2- mapleri ile pojo class ile birlikte map


    }
}
