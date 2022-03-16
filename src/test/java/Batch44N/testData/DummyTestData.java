package Batch44N.testData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {

    public HashMap<String, Object> setupDumTestData(){

        List<Integer> ageList=new ArrayList<>();
        ageList.add(40);
        ageList.add(21);
        ageList.add(19);

        HashMap<String, Object> onbirinciDataMap=new HashMap<>();
        onbirinciDataMap.put("id",11);
        onbirinciDataMap.put("employee_name","Jena Gaines");
        onbirinciDataMap.put("employee_Salary",90560);
        onbirinciDataMap.put("employee_age",30);
        onbirinciDataMap.put("profile_image","");

        HashMap<String, Object> expectedDataMap=new HashMap<String, Object>();
        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("besinciEleman","Airu Satou");
        expectedDataMap.put("calisanSayisi",24);
        expectedDataMap.put("sondanIkinciCalisan",106450);
        expectedDataMap.put("arananYaslar",ageList);
        expectedDataMap.put("onbirinciCalisan",onbirinciDataMap);

        return expectedDataMap;
    }
}
