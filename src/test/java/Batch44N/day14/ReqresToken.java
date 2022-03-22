package Batch44N.day14;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class ReqresToken {
//before metodu gibi her islemde yeni token istemeli
    public  String tokenAl() {

        String url = "https://reqres.in/api/login";

        HashMap<String, String> requestBody = new HashMap<>();
        requestBody.put("email", "eve.holt@reqres.in");
        requestBody.put("password","cityslicka");
        //System.out.println("requestBody = " + requestBody);

        Response response=given().accept(ContentType.JSON)  //contentType("application/json")
                .body(requestBody)  //.auth().//basic("admin","password");   // body icerisinde kullanici adi ve sifreyi gonderip izin istegi gondermis oluyorum.
                .when().post(url);        //post request kullanacagim, bodyye ihtiyac var
       //response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();
        String token=jsonPath.getString("token");
        System.out.println("token = " + token);

        return token;

    }
}
