package Batch44N.day09;

import Batch44N.testBase.DummyTestBase;
import Batch44N.testData.DummyTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import java.util.*;

import static io.restassured.RestAssured.given;

/// neden calismyor ????

public class GetRequest14 extends DummyTestBase {
    /*  http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    En yüksek maaşın 725000 olduğunu,
    En küçük yaşın 19 olduğunu,
    İkinci en yüksek maaşın 675000 olduğunu test edin. */

    @Test
    public void DummyTest(){
        specDum.pathParam("param1","employees");

        DummyTestData expectedObj=new DummyTestData();
        HashMap<String,Integer> expectedDataMap=expectedObj.setupTestData2();
        System.out.println("expectedDataMap = " + expectedDataMap);


        Response response=given().accept(ContentType.JSON).
                spec(specDum).when().get("/{param1}");
        //response.prettyPrint();

        HashMap<String ,Object> actualDataMap=response.as(HashMap.class);
        //System.out.println("actualDataMap = " + actualDataMap);

        //status code 200 mu?
        System.out.println("StatusCode = " + response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("statusCode"),(Integer)response.getStatusCode());

        List<Integer>  maasList=new ArrayList<Integer>();
        int dataSize=((List)actualDataMap.get("data")).size();

        for (int i = 0; i <dataSize; i++) {
            maasList.add( (Integer) ((Map) ((List) actualDataMap.get("data")). get(i)).get("employee_salary") );
        }
        System.out.println("Maas Listem :" + maasList);
        System.out.println("Maas liste sayisi :" + maasList.size());

        Collections.sort(maasList);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),maasList.get(maasList.size()-1));

        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"),maasList.get(maasList.size()-2));

        List<Integer>  yasList=new ArrayList<Integer>();

        for (int i = 0; i <dataSize; i++) {
            yasList.add( (Integer) ((Map) ((List) actualDataMap.get("data")). get(i)).get("employee_age") );
        }
        Collections.sort(yasList);
        System.out.println("Yas Listem :" + yasList);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"),yasList.get(0));

        //json Path ile

        JsonPath json=response.jsonPath();   //response bodiyi jsona attik
        System.out.println("json.getList(\"data\") = " + json.getList("data"));

        List<Integer> maasListJson=json.getList("data.employee_salary");

        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),maasListJson.get(maasListJson.size()-1));
        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"),maasListJson.get(maasListJson.size()-2));

        List<Integer> yasListJson=json.getList("data.employee_age");
        Collections.sort(yasListJson);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"),yasListJson.get(0));



    }
}
