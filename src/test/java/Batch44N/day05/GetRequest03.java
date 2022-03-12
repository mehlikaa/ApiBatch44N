package Batch44N.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.urlEncodingEnabled;
// import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class GetRequest03 {
    @Test
    public void test01(){
        //GetRequest03:
        //https://restful-booker.herokuapp.com/booking/7 url'ine
        String url="https://restful-booker.herokuapp.com/booking/7";
        Response response=given().
                accept("application/json").  //contentType(ContentType.JSON).
                when().
                get(url);
        response.prettyPrint();
        //accept type'i "application/json" olan GET request'i yolladigimda gelen response'un
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", Matchers.equalTo("Eric")).
                body("lastname", Matchers.equalTo("Brown")).
                body("totalprice", Matchers.equalTo(445)).
                body("depositpaid", Matchers.equalTo(false)).
                body("bookingdates.checkin", Matchers.equalTo("2018-12-19")).
                body("bookingdates.checkin", Matchers.equalTo("2018-12-19"));

    //status kodunun 200
        //ve content type'inin "application/json"
        //ve firstname'in "Jim"
        //ve lastname'in "Wilson"
        //ve checkin date'in 2019-08-10"
        //ve checkout date'in 2022-01-29 oldugunu test edin
        //body("key", Matchers.equalTo("Value")) : key olarak verilen degisken’in degerinin value’ya esit olup olmadigini kontrol eder


    //  daha kisa yontem ile
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
                body("firstname",equalTo("Eric"),
                        "lastname", equalTo("Brown"),
                        "totalprice",equalTo(445),
                        "depositpaid",equalTo(false),
                        "bookingdates.checkin", equalTo("2018-12-19"),
                        "bookingdates.checkin", equalTo("2018-12-19")
                        );
    }
}
