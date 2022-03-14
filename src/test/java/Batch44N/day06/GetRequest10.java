package Batch44N.day06;

import Batch44N.testBase.DummyTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends DummyTestBase {
    /*    GetRequest10:
http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde Dönen response un
Status kodunun 200,
1)10’dan büyük tüm id’leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu,
2)30’dan küçük tüm yaşları ekrana yazdırın ve
bu yaşların içerisinde en büyük yaşın 23 olduğunu
3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
bunların içerisinde “Charde Marshall” olduğunu test edin
     */

    @Test
    public void test01(){
        specDum.pathParam("param1","employees");

        Response response=given().accept(ContentType.JSON)
                .spec(specDum).when().get("/{param1}");

        JsonPath jsonPath=response.jsonPath(); //responseun sadece bodysini jsonPathe attim

        Assert.assertEquals(200,response.getStatusCode());   //body disindakiler responsedan alinir.

        List<Integer> idList=jsonPath.getList("data.findAll{it.id>10}.id");
        System.out.println("IdList :  "+ idList);

        Assert.assertEquals(14,idList.size());

        List<Integer> ageList=jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println("ageList :  "+ ageList+"\n Age Size : "+ageList.size());

        Assert.assertEquals(6,ageList.size());

        Collections.sort(ageList);
        ageList.get(ageList.size()-1);          //siralanan listedeki son elemani getirelim.
        Assert.assertEquals(23,(int)ageList.get(ageList.size()-1));

        List<Integer> salaryNameList=jsonPath.getList("data.findAll{it.employee_salary>350000}.employee_name");
                                                    // salary buyuk olanlarin, namelerini getir.
        System.out.println("salaryNameList :  "+ salaryNameList+"\n salaryNameList Size : "+salaryNameList.size());

        Assert.assertTrue(salaryNameList.contains("Charde Marshall"));
    }
}
