package Batch44N.day11;

import Batch44N.testBase.JsonPlaceHolderTestBase;
import Batch44N.testData.JPHTestData;
import org.junit.Test;

import java.util.HashMap;

public class PostRequest03 extends JsonPlaceHolderTestBase {
    /*https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
}
"userId": 55,
"title": "Tidy your room", "completed": false
}
Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
{
"userId": 55,
"title": "Tidy your room", "completed": false,
"id": ...
}
     */
    @Test
    public void test01(){
        specJPH.pathParam("param1","todos");

        JPHTestData jphTestData=new JPHTestData();
        //HashMap<String, Object>  expectedDataMap=jphTestData.setupTestData();
    }
}
