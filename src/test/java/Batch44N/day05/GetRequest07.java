package Batch44N.day05;

import Batch44N.testBase.HerOkuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest07 extends HerOkuAppTestBase {
/*
GetRequest07:
https://restful-booker.herokuapp.com/booking/5 url’ine bir
request yolladigimda HTTP Status Code’unun 200
ve response content type’inin “application/JSON” oldugunu
ve response body’sinin asagidaki gibi oldugunu test edin
{"firstname": Sally,
"lastname": "Smith",
"totalprice": 789,
"depositpaid": false,
"bookingdates":{ "checkin":"2017-12-11",
 }
"checkout":"2020-02-20" }
response.jsonPath(); methodu JsonPath class’indan obje ureterek response uzerinden
JsonPath class’indaki methodlari kullanmamizi saglar
 */
    @Test
    public void test01(){

        specHOA.pathParams("param1","booking",
                "param2",5);

        Response response=given().accept("application/json")
                .spec(specHOA)
                .when()
                .get("/{param1}/{param2}");

        //MAtchers class ile
        response.then().assertThat().contentType("application/JSON")
                .statusCode(200)
                .body("firstname",equalTo("Susan"),
                        "lastname",equalTo("Wilson"),
                        "totalprice",equalTo(486),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2021-06-28"),
                        "bookingdates.checkout",equalTo("2021-11-27"));

        JsonPath jsonPath=response.jsonPath();

        response.then().assertThat().contentType(ContentType.JSON).statusCode(200);

        assertEquals("Basarisiz Status Code islemi",200, response.getStatusCode());
        assertEquals("Basarisiz firstname","Susan",jsonPath.getString("firstname"));
        assertEquals("Basarisiz lastname","Wilson",jsonPath.getString("lastname"));
        assertEquals("Basarisiz totalprice",486,jsonPath.getInt("totalprice"));
        assertEquals("Basarisiz depositpaid",true,jsonPath.getBoolean("depositpaid"));
        assertEquals("Basarisiz checkin","2021-06-28",jsonPath.getString("bookingdates.checkin"));
        assertEquals("Basarisiz check out","2021-11-27",jsonPath.getString("bookingdates.checkout"));


    }
}
