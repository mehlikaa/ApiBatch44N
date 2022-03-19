package Batch44N.day10;

import Batch44N.testBase.HerOkuAppTestBase;
import Batch44N.testData.HerOkuAppTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.*;
import java.util.*;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends HerOkuAppTestBase {
    // type belirtmedigimiz icin Type casting gerekmiyor.
    // request gonderilirken, body icerisinde toString
    /*  {https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
        "firstname": "Selim",
        "lastname": "Ak",
        "totalprice": 11111,
        "depositpaid": true,
        "bookingdates": {
                "checkin": "2020-09-09",
                "checkout": "2020-09-21" }}  gönderildiğinde,
                Status kodun 200 olduğunu ve dönen response body nin ,
        "booking": {
                "firstname": " Selim ",
                "lastname": " Ak ",
                "totalprice": 11111,
                "depositpaid": true,
                "bookingdates": {
                "checkin": "2020-09-01",
                "checkout": " 2020-09-21”   }, }    olduğunu test edin    */
    @Test
    public void test01(){
        // once url - dinamik yapmak icin, farkli bir packegeda baseUrl olusturduk
    specHOA.pathParam("param1","booking");

    //request  Body ve expectedData ayni old icin tek bir obje kullanilmasi yeterli
        HerOkuAppTestData testDataHOA=new HerOkuAppTestData();
        JSONObject expectedRequestData=testDataHOA.setupTestandRequestData();
        System.out.println("expectedRequestData = " + expectedRequestData);

        //3- request gonder
        Response response=given()
                .contentType(ContentType.JSON)  //.accept(ContentType.JSON)
                .spec(specHOA)
                .auth().basic("admin","password123")
                .body(expectedRequestData.toString())  //JsonObject .toString yazmak zorundayiz
                .when()
                .post("/{param1}");
        response.prettyPrint();

        //deserialization
        HashMap<String, Object> actualDataMap=response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);
        Assert.assertEquals(expectedRequestData.getString("firstname"),
                            ((Map) actualDataMap.get("booking")).get("firstname"));
        Assert.assertEquals(expectedRequestData.getString("lastname"),
                            ((Map) actualDataMap.get("booking")).get("lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"),
                            ((Map) actualDataMap.get("booking")).get("totalprice"));
        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),
                            ((Map) actualDataMap.get("booking")).get("depositpaid"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates"),
                ((Map) ((Map) actualDataMap.get("booking")).get("bookingdates")).get("checkin") );
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates"),
                ((Map) ((Map) actualDataMap.get("booking")).get("bookingDATES")).get("checkout"));

        //JsonPath
        JsonPath jsonPath=response.jsonPath();  //responsedan gelen datayi al, Json icine at
        Assert.assertEquals(expectedRequestData.getString("lastname"),
                            jsonPath.getString("booking.lastname"));
        Assert.assertEquals(expectedRequestData.getString("firstname"),
                            jsonPath.getString("booking.lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"),
                            jsonPath.getInt("booking.totalprice"));
        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),
                            jsonPath.getBoolean("booking.depositpaid"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                            jsonPath.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                jsonPath.getString("booking.bookingdates.checkout"));


    }

}
