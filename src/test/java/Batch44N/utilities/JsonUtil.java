package Batch44N.utilities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {
//Object Mapper
// Json datalarını Java objelerine çevirmek için utilities altında bir reusable method içeren bir class oluşturabiliriz...

        //codehaus.jackson kutuphanesinden import
        private static ObjectMapper mapper;

        //static metod herseyden once calisirdi.
            static{
            mapper=new ObjectMapper();
                    }
        public static <T> T convertJsonToJava(String json,Class<T> cls){
            T javaResult= null;

            try {
                javaResult = mapper.readValue(json, cls);
            } catch (IOException e) {
                System.err.println("json datası javaya dönüştürülemedi"); }
                return javaResult;
            }

    }


