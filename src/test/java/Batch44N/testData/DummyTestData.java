package Batch44N.testData;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {

    public HashMap<String, Object> setupDumTestData() {

        List<Integer> ageList = new ArrayList<>();
        ageList.add(40);
        ageList.add(21);
        ageList.add(19);

        HashMap<String, Object> onbirinciDataMap = new HashMap<>();
        onbirinciDataMap.put("id", 11);
        onbirinciDataMap.put("employee_name", "Jena Gaines");
        onbirinciDataMap.put("employee_salary", 90560);
        onbirinciDataMap.put("employee_age", 30);
        onbirinciDataMap.put("profile_image", "");

        HashMap<String, Object> expectedDataMap = new HashMap<String, Object>();
        expectedDataMap.put("statusCode", 200);
        expectedDataMap.put("besinciCalisan", "Airi Satou");
        expectedDataMap.put("calisanSayisi", 24);
        expectedDataMap.put("sondanIkinciCalisan", 106450);
        expectedDataMap.put("arananYaslar", ageList);
        expectedDataMap.put("onbirinciCalisan", onbirinciDataMap);

        return expectedDataMap;
    }
    /*
http://dummy.restapiexample.com/api/v1/employees url ine
bir istek gönderildiğinde Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000 olduğunu test edin.
     */

    public HashMap<String, Integer> setupTestData2() {

        HashMap<String,Integer> expectedDataMap=new HashMap<>();
        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("enYuksekMaas",725000);
        expectedDataMap.put("enKucukYas",19);
        expectedDataMap.put("ikinciEnYuksekMaas",675000);
        System.out.println("expectedDataMap = " + expectedDataMap);

        return expectedDataMap;
    }


}
