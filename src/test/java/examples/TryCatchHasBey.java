package examples;

//import base_url.HerokuAppBaseUrl;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import org.junit.Test;
//import utilities.JsonUtil;
//import java.io.IOException;
//import java.util.HashMap;
//import static io.restassured.RestAssured.given;
//
//public class GetRequestObjectMapper02Deneme extends HerokuAppBaseUrl {
//     /*
// https://restful-booker.herokuapp.com/booking/2 url’ine bir get request gönderildiğinde,
// status kodun 200 ve response body’nin
//{
//"firstname": "Ali",
//"lastname": "Can",
//"totalprice": 500,
//"depositpaid": true,
//"bookingdates": {
//"checkin": "2022-03-01",
//"checkout": "2022-03-11"
//},
//"additionalneeds": "Breakfast"
//}
//​
//Olduğunu Object Mapper kullanarak test edin.
//*/
//
//    @Test
//    public <T> void test01 ()  {
////expecteddata olustur
//
//        String jsonData = "{\n" +
//                "\"firstname\": \"Ali\",\n" +
//                "\"lastname\": \"Can\",\n" +
//                "\"totalprice\": 500,\n" +
//                "\"depositpaid\": true,\n" +
//                "\"bookingdates\": {\n" +
//                "\"checkin\": \"2022-03-01\",\n" +
//                "\"checkout\": \"2022-03-11\"\n" +
//                "},\n" +
//                "\"additionalneeds\": \"Breakfast\"\n" +
//                "}";
//
//
//        HashMap<String, Object> expectedData = JsonUtil.convertJsonToJava(jsonData, HashMap.class);
//        System.out.println("expectedData = " + expectedData);
//
//        //request ve response
//        for (int i = 0; i < 40; i++) {
//            //url olustur
//            specHerOku.pathParams("param1", "booking", "param2", i);
//            Response response = given()
//                    .contentType(ContentType.JSON)
//                    .spec(specHerOku)
//                    .when()
//                    .get("/{param1}/{param2}/");
//            // T javaResult= null;
//            try {
//                response.prettyPrint();
//            } catch ( IllegalArgumentException e) {
//                System.out.println("response yazdirilamadi");
//            }
//        }
//
//    }}
