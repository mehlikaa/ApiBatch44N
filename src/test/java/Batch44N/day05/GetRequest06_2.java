package Batch44N.day05;

import Batch44N.testBase.JsonPlaceHolderTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest06_2 extends JsonPlaceHolderTestBase {
    @Test
    public void test01(){
//GetRequest06:
//https://jsonplaceholder.typicode.com/todos/123 url'ine
//accept type'i "application/json" olan GET request'i yolladigimda gelen response’un
//status kodunun 200
//ve content type'inin "application/json"
//ve Headers'daki "Server" in "cloudflare"
//ve response body'deki "userId"'nin 7
//ve "title" in "esse et quis iste est earum aut impedit" ve
// "completed" bolumunun false
// oldugunu test edin
//Bir Utilities package olusturalim, icinde her bir baseURL icin bir TestBase class’i olsun,
// hangi baseURL’i kullanmak istersek onun child’ini olusturup testlerimizi yapalim

        //{
        //    "userId": 7,
        //    "id": 123,
        //    "title": "esse et quis iste est earum aut impedit",
        //    "completed": false
        //}


        //String url="https://jsonplaceholder.typicode.com/todos/123";   //2 parametreli
        specJPH.pathParams("param1","todos",
                "param2",123);

        Response response=given().
                accept(ContentType.JSON).  //sadece json data dondururyorsa yazmayabiliriz.
                spec(specJPH).
                when().
                get("/{param1}/{param2}");

        response.prettyPrint();



        response.then().assertThat()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .header("Server",equalTo("cloudflare"))
                .body("userId",equalTo(7))
                .body("title",equalTo("esse et quis iste est earum aut impedit"))
                .body("completed",equalTo(false));
    }
}
