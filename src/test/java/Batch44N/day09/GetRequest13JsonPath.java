package Batch44N.day09;

import Batch44N.testBase.DummyTestBase;
import Batch44N.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.*;
import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest13JsonPath extends DummyTestBase {
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

        //JsonPath Yontemi once obje

        JsonPath json=response.jsonPath();
        //Status kodun 200 olduğunu,
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        //5. Çalışan isminin "Airi Satou" olduğunu ,
        Assert.assertEquals(expectedDataMap.get("besinciCalisan"),json.getString("data[4].employee_name"));
        //çalışan sayısının 24 olduğunu,
        Assert.assertEquals(expectedDataMap.get("calisanSayisi"),json.getList("data.id").size());
        //Sondan 2. çalışanın maaşının 106450 olduğunu
        Assert.assertEquals(expectedDataMap.get("sondanIkinciCalisan"),json
                .getInt("data[-2].employee_salary"));

        //40,21 ve 19 yaslarında çalışanlar olup olmadığını     --> key value yok yaslar list cagir
        Assert.assertTrue(json.getList("data.employee_age").containsAll((List) expectedDataMap.get("arananYaslar")));
        //11. Çalışan bilgilerinin
        Assert.assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("id"),json.getInt("data[10].id"));
        Assert.assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_name"),json.getString("data[10].employee_name"));
        Assert.assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_salary"),json.getInt("data[10].employee_salary"));
        Assert.assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_age"),json.getInt("data[10].employee_age"));
        Assert.assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("profile_image"),json.getString("data[10].profile_image"));

    }
}
