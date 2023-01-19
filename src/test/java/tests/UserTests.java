package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;

import static api.Jsonplaceholder.*;

public class UserTests {
    @BeforeClass
    public void setup(){
        getRandomUserID();
    }

    @Test
    public void printRandomUserEmail(){
        Response response = getRandomUserData();
        response.then().assertThat().statusCode(200);
        String email=response.then().extract().path("email");
        System.out.println("User Email: "+email);
    }

    @Test
    public void validateUserPostsIDs(){
        Response response=getUserPosts();
        response.then().assertThat().statusCode(200);
        ArrayList<Integer> postIDs=response.then().extract().path("id");
        for (Integer id:postIDs) {
            if(id>100 || id<1)
                Assert.assertTrue(false,"Invalid Post ID exist");
        }
    }

    @Test
    public void testCreatePost(){
        boolean Post_API_worked = false;
        int userId = getUserID();
        int id = 22;
        String title="Test";
        String body="Body";
        Response response=postWithRandomUser(userId,id,title,body);
        response.then().assertThat().statusCode(201);
        Response getResponse = getUserPosts();
        ArrayList<String> Title = getResponse.then().extract().path("title");
        for(String titles : Title) {
            if (titles == "Test") {
                Post_API_worked = true;
            }
        }
        Assert.assertTrue(Post_API_worked);
    }
}
