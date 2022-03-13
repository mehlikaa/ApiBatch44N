package Batch44N.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.awt.geom.RectangularShape;

import static io.restassured.RestAssured.given;
//import org.hamcrest.Matchers;
//import static org.hamcrest.Matchers.hasItem;
//import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.*;

public class GetRequest05 {

    @Test
    public void test01(){
        //GetRequest05:
        //http://dummy.restapiexample.com/api/v1/employees url'ine
        // accept type'i "application/json" olan GET request'i yolladigimda gelen response'un
        //status kodunun 200
        //ve content type'inin "application/json"
        //ve employees sayisinin 24
        //ve employee'lerden birinin "Ashton Cox"
        //ve gelen icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin
        String url="http://dummy.restapiexample.com/api/v1/employees";
        Response response=given()// given() rest assured import edilir.
                .accept(ContentType.JSON)
                .when()
                .get(url);

        response.prettyPrint();

        response.then()
                .assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("data.id", hasSize(24))
                .body("data.employee_name",hasItem("Ashton Cox"))    // //birden fazla Matchers.hasItem
                .body("data.employee_age",hasItems(21,61,23));      //birden fazla Matchers.hasItems
        //---------------------------------
        //body("data.id", Matchers.hasSize(" value ")) : key olarak verilen degisken’in
        // sayisinin value’ya esit olup olmadigini kontrol eder.
        // body("data.employee_name",Matchers.hasItem(" value ")) : key olarak verilen degisken’in
        // aldigi degerlerin icinde value var mi diye kontrol eder. Value birden fazla ise hasItems
        // kullanip value’lari vigulle yanyana yazilabilir
    }
}
