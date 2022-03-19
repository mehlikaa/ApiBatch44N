package Batch44N.day10;

import Batch44N.testBase.DummyTestBase;
import Batch44N.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Assert;
import org.junit.Test;

import java.awt.geom.RectangularShape;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends DummyTestBase {
/*
http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak {
        "name":"Ahmet Aksoy",
        "salary":"1000",
        "age":"18",
        "profile_image": ""
}    gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
{
"status": "success",
"data": { “id”:...
},
"message": "Successfully! Record has been added." }olduğunu test edin
 */
    @Test
    public void test01(){
        specDum.pathParam("param1","create");//post request yaparken body gondermeliyiz.
                                                    // testdata claasinda olusturdugumuz request bodyyi buradan cagiriyoruz.

        DummyTestData obje=new DummyTestData();    //hem request body  hem expected dataya ulasilacak
        HashMap<String,String> requestBodyMap=obje.setupRequestBody();
                                //test icin expected data lazim
        HashMap<String , Object>  expectedDataMap=obje.setupExpectedData();

        //request bodyyi gonderecegimiz bir alan body olmali. //response restassureddan
        Response response=given().accept("application/json")
                .spec(specDum).
                auth().basic("admin","password123").   //dummyde public gerekmiyor ama bu islemde authorization ister ve
                body(requestBodyMap).                      // bu bilgileri firma verir.spec ve body arasina yazilir.
                when().post("/{param1}");
        response.prettyPrint();

        // response sonucunu, deserialization ila Mape atacagizm
        HashMap<String, Object >  actualDataMap=new HashMap<>();
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"),actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"),actualDataMap.get("message"));

        //Json
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());   //ayni
        Assert.assertEquals(expectedDataMap.get("status"),jsonPath.getString("status"));
        Assert.assertEquals(expectedDataMap.get("message"),jsonPath.getString("message"));


        // **  expectedDataMap ile type castingden kurtulmak icin, expected data yi
        // bu sefer json Object ile olusturacagiz. Map olusturmayacagiz


        }

    }


