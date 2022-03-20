package Batch44N.pojos;

public class BookingDatesPojo {
    /*
       "checkin": "2020-09-09",
       "checkout": "2020-09-21"
     */
//private degiskenler
    private String checkin;
    private String checkout;

//getter setter metodlar   -->Generate
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

//constructorlar  -->Generate
    public BookingDatesPojo() {
    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

//toString metodu ile kalibi olustur     --> Generate
    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
