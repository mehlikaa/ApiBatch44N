package Batch44N.day13.JsonSchema02;

import Batch44N.day13.pojos.Data;
import Batch44N.day13.pojos.Dummy;
import Batch44N.testBase.DummyTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequestWithPojo01 extends DummyTestBase {

    //iki Pojo olustu; icteki Data disataki Dummy

    @Test
    public void test01(){
        specDum.pathParams("param1","employee","param2","1");

// expected data olusacak, dummy dekileri kullanmaliyim. Once datalari girmeliyim
// datadan obje uretip icine datanin degerlerini gir sonra,
// dummyden obje olustur. ve kalan degerleri gir

        Data data=new Data(1,"Tiger Nixon",320800,61,"");

        Dummy expectedData=new Dummy("success",data,"Successfully! Record has been fetched.");

        Response response=given().contentType(ContentType.JSON)
                        .spec(specDum)
                        .when().get("/{param1}/{param2}");
        response.prettyPrint();

        Dummy actualData=response.as(Dummy.class);
        System.out.println("GetRequestWithPojo01.test01");
        System.out.println("actualData = " + actualData);

    }
}
