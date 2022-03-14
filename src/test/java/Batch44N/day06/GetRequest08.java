package Batch44N.day06;

import Batch44N.testBase.DummyTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.groovy.json.internal.BaseJsonParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends DummyTestBase {
    /*GetRequest08:
http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
1) Butun calisanlarin isimlerini consola yazdıralim 2) 3. calisan kisinin ismini konsola yazdıralim
3) Ilk 5 calisanin adini konsola yazdiralim
4) En son calisanin adini konsola yazdiralim
     */

    @Test
    public void test01(){

        specDum.pathParam("param1","employees");

        Response response=given()
                .accept(ContentType.JSON)
                .spec(specDum)
                .when()
                .get("/{param1}");

        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath(); //response jsonpath icinde sakli
        System.out.println("jsonPath.getList(\"data.employeename\") = " + jsonPath.getList("data.employee_name"));
        System.out.println("jsonPath.getList(\"data.employee_salary\") = " + jsonPath.getList("data.employee_salary"));
        System.out.println("jsonPath.getList(\"data.employee_age\") = " + jsonPath.getList("data.employee_age"));

        System.out.println("\n----\njsonPath.getString(\"data[2].employee_name\") = " + jsonPath.getString("data[2].employee_name"));
        //ikisi de kullanilabilir ama gecerli olan ustteki yazim
        System.out.println("\n----\njsonPath.getString(\"--data.employee_name[2]\") = " + jsonPath.getString("data.employee_name[2]"));

        System.out.println("Ilk 5 kisinin ismi   = " + jsonPath.getString("data.employee_name[0,1,2,3,4]"));

        //it doesn't work ??????
//        List<String> myList=new ArrayList<>();
//        for (int i = 0; i <5 ; i++) {
//            //myList.add(jsonPath.getString("data.employee_name[i]"));
//            System.out.println(jsonPath.getString("data.employee_name[i]"));
//        }
//        System.out.println("myList : "+ myList);

        Assert.assertEquals("Ashton Cox",jsonPath.getString("data[2].employee_name"));

        System.out.println("----\nSon kisinin ismi ve yasi= " + jsonPath.getString("data[-1].employee_name")+" - "+jsonPath.getString("data[-1].employee_age"));

    }


}
