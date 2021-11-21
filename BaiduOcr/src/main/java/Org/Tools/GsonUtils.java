package Org.Tools;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
 
import java.lang.reflect.Type;

public class GsonUtils {


	 private static Gson gson = new GsonBuilder().create();
	 
	    public static String toJson(Object value) {
	        return gson.toJson(value);
	    }
	 
	    public static <T> T fromJson(String json, Class<T> classOfT) { 
	        return gson.fromJson(json, classOfT);
	    }
	 
	    public static <T> T fromJson(String json, Type typeOfT) {
	        return (T) gson.fromJson(json, typeOfT);
	    }

}
