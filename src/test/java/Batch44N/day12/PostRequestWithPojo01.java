package Batch44N.day12;

import Batch44N.pojos.TodosPojoJPH;
import Batch44N.testBase.JsonPlaceHolderTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.preemptive;

public class PostRequestWithPojo01 extends JsonPlaceHolderTestBase {
    /*  https://jsonplaceholder.typicode.com/todos url ‘ine bir request gönderildiğinde
        Request body {  "userId": 21,
                        "id": 201,
                        "title": "Tidy your room",
                        "completed": false          }
Status kodun 201, response body ‘nin ise
        {       "userId": 21,
                "id": 201,
                "title": "Tidy your room",
                "completed": false
        }                                 olduğunu test edin.    */
@Test
    public void test01(){
    specJPH.pathParam("param1","todos");

    TodosPojoJPH requestExpected=new TodosPojoJPH(21,201,"Tidy your room",false);
    System.out.println("requestExpected = " + requestExpected);
//requestExpected = TodosPojoJPH{userId=21, id=201, title='Tidy your room', completed=false}
    //constructor yardimi ile toString metoduna gitti,  ve key value kaliba doktu
    //toString ile oynarsaniz kalipta degisiklik yapilabilir

    //response olusturup requestimizi gonderelim
    Response response=given().contentType(ContentType.JSON)
            .spec(specJPH)
            .auth().basic("admin","password123")        //(zaten JsonObject degil)
            .body(requestExpected)              //post icin body gonderelim. zaten tosString ile geldigi icin gerek yok
            .when().post("/{param1}");
    response.prettyPrint();

    //      Map olusturmali miyim?
    // Pojonun guzelligi, Deserialization yapiyorsaniz, Mape gerek yok

    TodosPojoJPH actualData=response.as(TodosPojoJPH.class); //todospojoJPh classi kalip gibi kullan ve actual datayi olustur
                                                            //mape gerek kalmadi, bir datatypei yok
                                                            //kalibi bir defa olustur ve tum testlerde kullan
    Assert.assertEquals(201,response.getStatusCode());
    Assert.assertEquals(requestExpected.getUserId(),actualData.getUserId());
    Assert.assertEquals(requestExpected.getId(),actualData.getId());
    Assert.assertEquals(requestExpected.getTitle(),actualData.getTitle());
    Assert.assertEquals(requestExpected.isCompleted(),actualData.isCompleted());    //boolean ->get yerine isCompleted  Boolean kullanilirsa get kullanilabilir


}

}
