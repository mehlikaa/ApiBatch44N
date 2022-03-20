package Batch44N.pojos;

public class BookingPojo {
/*     "firstname": "Selim",
       "lastname": "Ak",
       "totalprice": 15000,
       "depositpaid": true,
       "bookingdates": {
                    "checkin": "2020-09-09",
                    "checkout": "2020-09-21"  } */

    //  1- keyvaluenun, key private  --  initialize yapmiyoruz
    //  2- bunlara ulasabilecek   public getter setter metodlar olusturalim
    //  3- parametreli(ilk deger atamasi icin) ve parametresiz constructor
    // 4- toString metodu olustur

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDatesPojo bookingdates;      //Daha once olusturdugumuz Pojo kalibini datatype
                                                // olarak belirterek yeni pojonun icine gomdum.
    //=== Getter Setter
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingDatesPojo getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingDatesPojo bookingdates) {
        this.bookingdates = bookingdates;
    }

    //======= Constructors

    public BookingPojo() {
    }

    public BookingPojo(String firstname, String lastname, int totalprice, boolean depositpaid, BookingDatesPojo bookingdates) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
    }
    //==== toString
    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                '}';
    }
}
