package Batch44N.day08;

import Batch44N.testBase.DummyTestBase;
import Batch44N.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends DummyTestBase {
    /*
http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde Status kodun 200 olduğunu,
5. Çalışan isminin "Airi Satou" olduğunu ,
çalışan sayısının 24 olduğunu,
Sondan 2. çalışanın maaşının 106450 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını     --> key value yok yaslar list cagir
11. Çalışan bilgilerinin
{
“id”:”11”                       --> key-value = map olusturulacak
"employee_name": "Jena Gaines",
"employee_salary": "90560", "employee_age": "30", "profile_image": "" }
} gibi olduğunu test edin.

           -- >hepsini tutan buyuk bir map de olacak
     */
     /*
-----{
    "status": "success",
    "data": [
        {   "id": 1,
            "employee_name": "Tiger Nixon",
            "employee_salary": 320800,
            "employee_age": 61,
            "profile_image": ""
        },
     */
    @Test
    public void test01(){

        specDum.pathParam("param1","employees");

        DummyTestData expectedObj=new DummyTestData();

        HashMap<String,Object> expectedDataMap=expectedObj.setupDumTestData();  //map dondurur ve bunu mape aktar tanimla

        System.out.println("expectedDataMap = " + expectedDataMap);


        //request
        Response response=given().
                            accept(ContentType.JSON).
                            spec(specDum).
                            when().
                            get("/{param1}");
        response.prettyPrint();

        Assert.assertEquals(expectedDataMap.get("employee_name"),);
    }

}
