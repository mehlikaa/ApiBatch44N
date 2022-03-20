package Batch44N.pojos;
                                    // encapsulation, data hiding yontemidir,.
public class TodosPojoJPH {  //pojo class - encapsulation (yontemi) kullanilan bir class olacak
    //  1- keyvaluenun, key private  --  initialize yapmiyoruz
    //  2- bunlara ulasabilecek   public getter setter metodlar olusturalim
    //  3- parametreli(ilk deger atamasi icin) ve parametresiz constructor
    // 4- toString metodu olustur

        //  1- keyvaluenun, key private  --  initialize yapmiyoruz
    private int userId;
    private int id;
    private String title;
    private boolean completed;


    //  2- bunlara ulasabilecek   public getter setter metodlar olusturalim
    //sag tik yada Code --> Generate --> Getter and Setter sec ve hepsini secebilirsin
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


         //  3- parametreli parametresiz constructor
        //parametresiz constructor -> Generate/Constructor/class ismine tiklanmali
    public TodosPojoJPH() {
    }
        //Generate/Constructor -> parametreleri secip parametreli constructor olusturduk
    public TodosPojoJPH(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

        // 4- toString metodu olustur -->Generate
    @Override
    public String toString() {         //map gibi bir key value degerler olusturdum.
        return "TodosPojoJPH{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}

