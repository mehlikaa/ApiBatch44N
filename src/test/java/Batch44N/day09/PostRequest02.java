package Batch44N.day09;

public class PostRequest02 {
    // type belirtmedigimiz icin Type casting gerekmiyor.
    // request gonderilirken, body icerisinde toString
    /*  {https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
        "firstname": "Selim",
        "lastname": "Ak",
        "totalprice": 11111,
        "depositpaid": true,
        "bookingdates": {
                "checkin": "2020-09-09",
                "checkout": "2020-09-21" }}  gönderildiğinde,
                Status kodun 200 olduğunu ve dönen response body nin ,
        "booking": {
                "firstname": " Selim ",
                "lastname": " Ak ",
                "totalprice": 11111,
                "depositpaid": true,
                "bookingdates": {
                "checkin": "2020-09-01",
                "checkout": " 2020-09-21”   }, }    olduğunu test edin    */
}
