package Batch44N.day12;

import Batch44N.testBase.DummyTestBase;
import Batch44N.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.RealSystem;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRequest01 extends DummyTestBase {
/*  http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde
Dönen response un status kodunun 200 ve
body kısmının aşağıdaki gibi olduğunu test edin
       {    "status": "success",
            "data": "2",
            "message": "Successfully! Record has been deleted"
        }           */

    @Test
    public void test01(){
        specDum.pathParams("param1","delete","param2",2);

        DummyTestData dummyTestData=new DummyTestData();
        JSONObject expectedDataJO=dummyTestData.setupDeleteExpectedData();

        Response response=given()
                .contentType(ContentType.JSON)
                .spec(specDum)
                .auth().basic("admin","password123")
                .when().delete("/{param1}/{param2}");
        response.prettyPrint();

        //Matchers
        response.then().assertThat()
                        .statusCode(200)
                                .body("status", equalTo(expectedDataJO.getString("status")),
                                        "data",equalTo(expectedDataJO.getString("data")),
                                        "message",equalTo(expectedDataJO.getString("message")));



    }
}
