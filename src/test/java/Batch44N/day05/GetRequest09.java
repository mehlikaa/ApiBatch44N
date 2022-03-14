package Batch44N.day05;

import Batch44N.testBase.DummyTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends DummyTestBase {
    /*GetRequest09:
http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde, status kodun 200,
gelen body de,  5. çalışanın isminin "Airi Satou" olduğunu ,
                6. çalışanın maaşının "372000" olduğunu ,
                Toplam 24 tane çalışan olduğunu,
"Rhona Davidson" ın employee lerden biri olduğunu
"21", "23", "61" yaşlarında employeeler olduğunu test edin
Oluşturduğumuz json içerindeki değerlere ulaşabilmek için json methodlarını kullanırız.
json.getString , json.getInt , json.getList , json.getBoolean gibi.....
     */
    @Test
    public void test01(){

        specDum.pathParam("param1","employees");

        Response response=given().accept(ContentType.JSON)
                        .spec(specDum).when().get("/{param1}");

        JsonPath jsonPath=response.jsonPath();
       Assert.assertEquals(200,response.getStatusCode());

        System.out.println(jsonPath.getList("data.id").size()+" adet datamiz var.");   //
        //  jsonPath.getString("data.id").    //getString ile size metoduna ulasamiyoruz

        Assert.assertEquals(24,jsonPath.getList("data.id").size());
        Assert.assertTrue(response.getStatusCode()==200);
        Assert.assertFalse(response.getStatusCode()!=200);

        System.out.println("5. kisinin ismi = " + jsonPath.getString("data[4].employee_name"));
        Assert.assertEquals("Airi Satou", jsonPath.getString("data[4].employee_name"));
        System.out.println("6. kisinin maasi = " + jsonPath.getInt("data[5].employee_salary"));
        Assert.assertEquals(372000,jsonPath.getInt("data[5].employee_salary"));
        //---------------

        Assert.assertTrue(jsonPath.getList("data.employee_name").contains("Rhona Davidson"));

        List<Integer> arananYaslar= Arrays.asList(21,23,61);
//        List<Integer> arananlar =new ArrayList<>();
//        arananlar.add(21);   arananlar.add(23);     arananlar.add(63);
        System.out.println("Aranan yaslar : "+ arananYaslar);
        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll(arananYaslar));
                                                        //containsAll benden bir collection bir llist istiyor.
        Assert.assertTrue(jsonPath.getList("data.employee_age").contains(21)&&jsonPath.getList("data.employee_age").contains(23)&&jsonPath.getList("data.employee_age").contains(61));

    }
}
