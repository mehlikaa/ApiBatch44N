package Batch44N.day09;

import Batch44N.testBase.DummyTestBase;
import Batch44N.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest13Matchers extends DummyTestBase {
    /*   Matchers ile test
http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
5. Çalışan isminin "Airi Satou" olduğunu ,
çalışan sayısının 24 olduğunu,
Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını     --> key value yok yaslar list cagir
11. Çalışan bilgilerinin
{
“id”:”11”                       --> key-value = map olusturulacak
"employee_name": "Jena Gaines",  "employee_salary": "90560",
"employee_age": "30", "profile_image": "" } olduğunu test edin.
    -- >hepsini tutan buyuk bir map de olacak      */
    @Test
    public void test01() {

        specDum.pathParam("param1", "employees");

        DummyTestData expectedObj = new DummyTestData();

        HashMap<String, Object> expectedDataMap = expectedObj.setupDumTestData();  //map dondurur ve bunu mape aktar tanimla
        System.out.println("expectedDataMap = " + expectedDataMap);

        Response response = given().                //request
                accept(ContentType.JSON).
                spec(specDum).
                when().
                get("/{param1}");
        //response.prettyPrint();

        response.then().assertThat().statusCode((Integer) expectedDataMap.get("statusCode")).
                body("data[4].employee_name", Matchers.equalTo(expectedDataMap.get("besinciCalisan")),
                        "data.id",hasSize((Integer) expectedDataMap.get("calisanSayisi")),
                        "data[-2].employee_salary", equalTo(expectedDataMap.get("sondanIkinciCalisan")),
                        "data.employee_age",hasItems(((List)expectedDataMap.get("arananYaslar")).get(0),
                                                     ((List)expectedDataMap.get("arananYaslar")).get(1),
                                                     ((List)expectedDataMap.get("arananYaslar")).get(2)));
        response.then().assertThat().
                        body("data[10].employee_name",equalTo(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_name")),
                        "data[10].employee_salary",equalTo(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_salary")),
                        "data[10].employee_age",equalTo(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_age")),
                        "data[10].profile_image",equalTo(((Map)expectedDataMap.get("onbirinciCalisan")).get("profile_image")));

// tek response.then().assertThat(). satiri ile de yazilabilir. Aldigim hata
        // Status Code 429-- The HTTP 429 Too Many Requests response status code indicates the user has
        // sent too many requests in a given amount of time ("rate limiting"). A Retry-After header might be
        // included to this response indicating how long to wait before making a new request.

    }
}
