package Batch44N.day08;

import Batch44N.testBase.HerOkuAppTestBase;
import Batch44N.testData.HerOkuAppTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends HerOkuAppTestBase {
    /* https://restful-booker.herokuapp.com/booking/1 urline bir istek gönderildiğinde dönen response bodynin
{
"firstname": "Sally", "lastname": "Smith", "totalprice": 208, "depositpaid": false, "bookingdates": {
"checkin": "2016-09-09",
"checkout": "2017-09-21" }
} gibi olduğunu test edin.

nested map yapmaliyim
     */

@Test
    public void test01(){
        specHOA.pathParams("param1","booking","param2",1);

        //expectedData HOATestData da olusturmustum
    HerOkuAppTestData expectedObje=new HerOkuAppTestData();
    // --> expectedObje.setupTestData(); mapimi aldim ama assertionlarda kullanabilmek icin, yone bir mape atamaliyim.
    HashMap<String, Object> expectedDataMap=expectedObje.setupTestData(); //

    System.out.println("expectedDataMap = " + expectedDataMap);

    //simdi request gondermeliyim ve gelen yaniti responce da tutyorum
    Response response=given()
            //.accept(ContentType.JSON)   // I'm a teapot hatasi verdi
            .accept("application/json")
            .spec(specHOA)
            .when().get("/{param1}/{param2}");
    response.prettyPrint();

    //response dan geleni actual datayi al HashMap gibi kullan
    HashMap<String, Object> actualDataMap=response.as(HashMap.class);
    System.out.println("actualDataMap = " + actualDataMap);

    // 1 - De-serialization yapalim
    Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
    Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
    Assert.assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
    Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
    Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                        ((Map)actualDataMap.get("bookingdates")).get("checkin"));
    Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkout"),
                        ((Map)actualDataMap.get("bookingdates")).get("checkout"));

    // 2 - JsonPath
    JsonPath jsonPath=response.jsonPath();
    Assert.assertEquals(expectedDataMap.get("firstname"),jsonPath.getString("firstname"));
    Assert.assertEquals(expectedDataMap.get("lastname"),jsonPath.getString("lastname"));
    Assert.assertEquals(expectedDataMap.get("totalprice"),jsonPath.getInt("totalprice"));
    Assert.assertEquals(expectedDataMap.get("depositpaid"),jsonPath.getBoolean("depositpaid"));
    Assert.assertEquals(  ((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                            jsonPath.getString("bookingdates.checkin"));
    Assert.assertEquals(  ((Map)expectedDataMap.get("bookingdates")).get("checkout"),
            jsonPath.getString("bookingdates.checkout"));
}
}
