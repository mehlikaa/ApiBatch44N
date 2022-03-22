package Batch44N.day14;

import Batch44N.testBase.HerOkuAppTestBase;
import Batch44N.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequestWitObjectMapper02 extends HerOkuAppTestBase {
/*  https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
      status kodun 200 ve response body’nin
         {     "firstname": "Jim",
               "lastname": "Wilson",
               "totalprice": 932,
               "depositpaid": false,
               "bookingdates": {
                        "checkin": "2016-03-30",
                        "checkout": "2020-04-30" }      }
Olduğunu Object Mapper kullanarak test edin */

    @Test
    public void test01(){
        specHOA.pathParams("param1","booking", "param2","2");

        //expected data olusturabilmek icin,reusable metoduma gonderecegim String parametremi olusturalim.
        String jsonData="{ \"firstname\": \"Jim\",\n" +
                "          \"lastname\": \"Wilson\",\n" +
                "          \"totalprice\": 932,\n" +
                "          \"depositpaid\": false,\n" +
                "          \"bookingdates\": {\n" +
                "               \"checkin\": \"2016-03-30\",\n" +
                "               \"checkout\": \"2020-04-30\" }      }";

    //JsonUtil clasimdaki metod kullanarak, json datayi gonderecegim, Hashmapte tutmak istiyorum.
        HashMap<String,Object> expectedData=JsonUtil.convertJsonToJava(jsonData, HashMap.class);
        // java formatina cevirdi ve map olusarak dondurdu.
        System.out.println("expectedData = " + expectedData);

        Response response=given().contentType(ContentType.JSON)
                        .spec(specHOA)
                        .when().get("/{param1}/{param2}");
        response.prettyPrint();

        //string olan herseyi javaya ceviriyoruz

        HashMap<String,Object> actualData = JsonUtil.convertJsonToJava(response.asString(),HashMap.class); //Hashmap formatinda istiyorum
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        Assert.assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        Assert.assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        Assert.assertEquals(((Map) expectedData.get("bookingdates")).get("checkin"),
                            ((Map) actualData.get("bookingdates")).get("checkin"));

        Assert.assertEquals(((Map) expectedData.get("bookingdates")).get("checkout"),
                ((Map) actualData.get("bookingdates")).get("checkout"));
    }

}
