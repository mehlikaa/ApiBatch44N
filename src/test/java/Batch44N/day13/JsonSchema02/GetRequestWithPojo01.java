package Batch44N.day13.JsonSchema02;

import Batch44N.day13.JsonSchema02.pojos.Data;
import Batch44N.day13.JsonSchema02.pojos.Dummy;
import Batch44N.testBase.DummyTestBase;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.Assert;
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

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedData.getStatus(),actualData.getStatus());
        Assert.assertEquals(expectedData.getData().getId(),actualData.getData().getId());
        Assert.assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        Assert.assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        Assert.assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        Assert.assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());
        Assert.assertEquals(expectedData.getMessage(),actualData.getMessage());



        // Serializiation, java yapisinda olan datayi, Json formatina donusturme islemidir.
        // bunun icin once, Gson classindan obje uretiyoruz.
        //yapmamiza gerek yok sadece nasil gorundugunu goruyoruz

        Gson gson=new Gson();
        String jsonFromJava=gson.toJson(actualData);
        System.out.println("jsonFromJava = " + jsonFromJava);
        //mesela yeni bir datayi create*(post request) edeceksiniz,
        // Veritabanindaki datayi yedegini olusturscaksiniz alacaksiniz.
        //

    }
}
