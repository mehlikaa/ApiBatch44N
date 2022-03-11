package get_http_request;



import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class GetRequest02_ {
    @Test
    public void test02(){

        String url="https://reqres.in/api/users";

        Response response=given().when().get(url);
        // get ; Postmandaki GET bilgiyi getir komutu

        //response.prettyPrint(); //postmandaki gibi butun datayi gorebiliyoruz
                                //responsedaki body yi getirir.
        //response.prettyPeek(); // karsi taraftan ne var ne aldigi herseyi getirir.
                                //bu bilgiler nerede, Postman Headerin icinde
            // peek ile zincirleme islemlerde cok daha fazla secenek var.
        //System.out.println("--- all---\nresponse.then().log().all() ile butun datayi getirdi");
        //response.then().log().all();    //yine butun datayi getirir.  prettypeek gibi


        //headers test edilir.
        response.then().
            assertThat().
            statusCode(200).
            contentType("application/json; charset=utf-8").
            statusLine("HTTP/1.1 200 OK");
        //and ile ekelenebilir ama ayri test acalim
        //onemli etstleri smoke test icine atiyorduk


        //body test edilir.
        response.then().body("data[0].first_name", equalTo("George"),"data[0].last_name", equalTo("Bluth"),"data[0].email", equalTo("george.bluth@reqres.in"));
        //json path ile locate etmis olduk ---- Key - Value ile kontrol yapildi
        // Macthers bir class.
        //Matchers i kaldirip Matcher.equalto yu static import ederek kullanilabilir
        response.then().body("data[0].first_name", equalTo("George"),"data[0].last_name",equalTo("Bluth"),"data[0].email",equalTo("george.bluth@reqres.in"));


        // asil testlerde, butun data bir listeye atilir ve var mi yok mu test ediliir.


        response.then().body("data[1].id",equalTo(2),
                "data[1].email",equalTo("janet.weaver@reqres.in"),
                "data[1].first_name",equalTo("Janet"),
                "data[1].last_name",equalTo("Weaver"),
                "data[1].avatar",equalTo("https://reqres.in/img/faces/2-image.jpg"));
    //locate edip , key value mantigi ile Matchers calssta test etmis olduk.

    }
}
