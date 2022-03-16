package Batch44N.day08;

import Batch44N.testBase.DummyTestBase;
import Batch44N.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.*;
import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends DummyTestBase {
    /*
http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
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
        //response.prettyPrint();

        // de serialization islemi
        HashMap<String, Object> actualDataMap=response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);
//Status kodun 200 olduğunu,
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());  //body icinde olmadigi icin responsedan aldik.
        // //5. Çalışan isminin "Airi Satou" olduğunu ,
        Assert.assertEquals(expectedDataMap.get("besinciCalisan"),
                            ((Map)((List)actualDataMap.get("data")).get(4)).get("employee_name"));
        //                                              (4.indexte Map var)(list dondurecek)(datanin icine girdim)
        //çalışan sayısının 24 olduğunu,
        // ???? neden list
        Assert.assertEquals(expectedDataMap.get("calisanSayisi"),
                        ((List) actualDataMap.get("data")).size());

        //Sondan 2. çalışanın maaşının 106450 olduğunu
        //once bize donen datanin sizeini bulmaliyiz
        int dataSize=((List) actualDataMap.get("data")).size();
        Assert.assertEquals(expectedDataMap.get("sondanIkinciCalisan"),
                ((Map)((List) actualDataMap.get("data")).get(dataSize-2)).get("employee_salary"));

    //40,21 ve 19 yaslarında çalışanlar olup olmadığını     --> key value yok yaslar list cagir
    //birer assert edebilirim, employee ageleri bir liste atarsam
        List<Integer>  actualYasListesi=new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            actualYasListesi.add((Integer)(((Map)((List) actualDataMap.get("data")).get(i)).get("employee_age")));
        }
        System.out.println("actualYasListesi = " + actualYasListesi);
        Assert.assertTrue(actualYasListesi.containsAll(   (List) expectedDataMap.get("arananYaslar"))   );

//11. Çalışan bilgilerinin
//      “id”:”11”       "employee_name": "Jena Gaines",   "employee_salary": "90560",
//      "employee_age": "30", "profile_image": ""  -- olduğunu test edin.

        Assert.assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_name"),
                            ((Map)((List) actualDataMap.get("data")).get(10)).get("employee_name"));
        Assert.assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_salary"),
                            ((Map)((List) actualDataMap.get("data")).get(10)).get("employee_salary"));
        Assert.assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("employee_age"),
                            ((Map)((List) actualDataMap.get("data")).get(10)).get("employee_age"));
        Assert.assertEquals(((Map)expectedDataMap.get("onbirinciCalisan")).get("profile_image"),
                            ((Map)((List) actualDataMap.get("data")).get(10)).get("profile_image"));



    }

}
