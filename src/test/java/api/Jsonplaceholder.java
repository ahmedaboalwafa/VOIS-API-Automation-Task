package api;

import io.restassured.response.Response;
import org.apache.commons.lang3.RandomUtils;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class Jsonplaceholder {
    private static final String apiURL="https://jsonplaceholder.typicode.com/users/";
    private static int userID;
    public static Response getRandomUserData(){
        return given().get(apiURL+ userID);
    }

    public static void getRandomUserID(){
        userID= new RandomUtils().nextInt(1,11);
    }

    public static int getUserID(){
        return userID ;
    }
    public static Response getUserPosts(){
        return given().get(apiURL+userID+"/posts");
    }

    public static Response postWithRandomUser(int userId, int id, String title, String body){
        HashMap postData=new HashMap<>();
        postData.put("userId",userId);
        postData.put("id",id);
        postData.put("title",title);
        postData.put("body",body);
        return given().contentType("application/json").body(postData).post(apiURL+userID+"/posts");
    }

}