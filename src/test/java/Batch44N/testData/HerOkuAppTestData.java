package Batch44N.testData;

import java.util.HashMap;

public class HerOkuAppTestData {
    /* https://restful-booker.herokuapp.com/booking/1
     {
        "firstname": "Sally", "lastname": "Smith", "totalprice": 208, "depositpaid": false, "bookingdates": {
        "checkin": "2016-09-09",
        "checkout": "2017-09-21" }
        }
        mapi cagirabilmemiz icin, once metod ve 2 map olusturalim
     */

    public HashMap<String ,Object> setupTestData(){
        //icteki map
        HashMap<String, Object> bookingDatesMap=new HashMap<String,Object>();
        bookingDatesMap.put("checkin","2016-09-28");
        bookingDatesMap.put("checkout","2018-01-28");
          //distaki 2.map
        HashMap<String ,Object> expectedData=new HashMap<String, Object>();
        expectedData.put("firstname","Mark");
        expectedData.put("lastname","Ericsson");
        expectedData.put("totalprice",341);
        expectedData.put("depositpaid",false);
        expectedData.put("bookingdates",bookingDatesMap);

        //distaki mapi dondururum
        return expectedData;
    }

    public void setupTestandRequestData(){

    }
}
