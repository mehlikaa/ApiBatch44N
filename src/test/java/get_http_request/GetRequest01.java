package get_http_request;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {

    @Test
    public void test01(){
        String url="https://restful-booker.herokuapp.com/booking";

        Response response=given().when().get(url);
        //response.prettyPrint();
        System.out.println("-----");
        //response.prettyPeek();
        System.out.println("response.statuscode = " + response.statusCode());
        System.out.println("response.contentype = " + response.contentType());
        System.out.println("response.time = " + response.time());
        Assert.assertEquals(200,response.statusCode());

        Assert.assertTrue("contenttype hatali",response.contentType().contains("json"));

        response
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8");
    }
}
