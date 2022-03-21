package Batch44N.day12;

import Batch44N.pojos.BookingDatesPojo;
import Batch44N.pojos.BookingPojo;
import Batch44N.pojos.BookingResponsePojo;
import Batch44N.testBase.HerOkuAppTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

public class PostRequestWithPojo02 extends HerOkuAppTestBase {
    /*https://restful-booker.herokuapp.com/booking
    url’ine aşağıdaki request body {
                                     "firstname": "Selim",
                                     "lastname": "Ak",
                                     "totalprice": 15000,
                                     "depositpaid": true,
                                     "bookingdates": {
                                        "checkin": "2020-09-09",
                                        "checkout": "2020-09-21" }   }
                                 gönderildiğinde,
   status kodun 200 ve dönen response ‘un
{    "bookingid": 11,
     "booking": {
               "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 15000,
               "depositpaid": true,
               "bookingdates": {
                    "checkin": "2020-09-09",
                    "checkout": "2020-09-21"  }  }  }    olduğunu test edin            */
//--> 3 parantez acilip kapanmis , olusturmaya en ic yapiyi olusturarark baslayalim.

    @Test
    public void test01(){
        //URL olustur
        specHOA.pathParam("param1","booking");

        BookingDatesPojo bookingdatesPj=new BookingDatesPojo("2020-09-09","2020-09-21");

        // bunu request data olarak gonderebilirim
        BookingPojo bookingPj=new BookingPojo("Selim","Ak",15000,true, bookingdatesPj);

        Response response=given()
                .contentType(ContentType.JSON)
                .spec(specHOA)
                //.auth().basic("admin","password123")
                .body(bookingPj)            //Pojo 'de toString ile bilgieleri aldigimiz icin, toString kullanmiyoruz.
                .post("/{param1}");

        response.prettyPrint();

        BookingResponsePojo actualData=response.as(BookingResponsePojo.class);

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(bookingPj.getFirstname(),actualData.getBooking().getFirstname());
        Assert.assertEquals(bookingPj.getLastname(),actualData.getBooking().getLastname());
        Assert.assertEquals(bookingPj.getTotalprice(),actualData.getBooking().getTotalprice());
        Assert.assertEquals(bookingPj.isDepositpaid(),actualData.getBooking().isDepositpaid());
        Assert.assertEquals(bookingPj.getBookingdates().getCheckin(),
                                actualData.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(bookingPj.getBookingdates().getCheckout(),
                                actualData.getBooking().getBookingdates().getCheckout());


    }

}
