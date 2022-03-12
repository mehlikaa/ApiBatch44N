package Batch44N.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {
/*GetRequest01:
https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde donecek cevap(response) icin
HTTP status kodunun 200 Content Type’in Json
Ve Status Line’in HTTP/1.1 200 OK Oldugunu test edin.GetRequest01:
https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde donecek cevap(response) icin
HTTP status kodunun 200 Content Type’in Json
Ve Status Line’in HTTP/1.1 200 OK Oldugunu test edin.*/

    @Test
    public void test01() {
        // 1- api testi icin, ilk olarak URL (endpoint) belirlenir.
        String url = "https://restful-booker.herokuapp.com/booking/3";

        // 2- beklenen - expected result olustururlur.
             //  bu case de benden body dogrulamasi istenmedigi icin, beklenen sonuc olusturmuyorum.
        // 3- request gonderirim
        //request gondereceksem formatini bilmeliyim. json seciyoruz sonra http request get
        //sonucu bir yere atamadan kullanamam --> given().accept("application/json").when().get(url);
        Response response=given().
                accept("application/json").
                when().
                get(url);
                //response , request ile gelen, body header ve status code gibi tum bilgileri sakliyor.

        response.prettyPrint();    // tum bilgileri yazdirabiliriz.

        // 4- response body icin, actual result olustur.
        //response body ile ilgili islem yapmayacagimiz icin olusturmayacagiz

        // 5- assertion(dogrulama)
        System.out.println("response,getStatusCode :"+response.getStatusCode());       //200 olmali
        System.out.println("response.getContentType() : "+response.getContentType());
        System.out.println("response.getStatusLine() :"+response.getStatusLine());
        System.out.println("\n---\nresponse.getHeaders() = " + response.getHeaders());

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.getContentType());
        Assert.assertEquals("HTTP/1.1 200 OK",response.getStatusLine());


        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

    }

}
