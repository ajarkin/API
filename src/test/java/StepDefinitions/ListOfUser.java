package StepDefinitions;


import Utils.ConfigReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Then;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class ListOfUser {



    @Then("the user execute the the API call to list the users")
    public void the_user_execute_the_the_API_call_to_list_the_users() throws IOException, URISyntaxException {
        HttpClient client= HttpClientBuilder.create().build();
        URIBuilder uri=new URIBuilder();
        uri.setScheme("https");
        uri.setHost("reqres.in");
        uri.setPath("api/users");
        uri.setCustomQuery("per_page=12");
        HttpGet get=new HttpGet(uri.build());
        get.setHeader("Accept","application/json");

        HttpResponse response=client.execute(get);
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> deserializedObject= objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String,Object>>(){});

        List<Object> listOfObjects=(List<Object>)deserializedObject.get("data");

        for(int i=0;i<listOfObjects.size();i++){
            Map<String,Object> userData=( Map<String,Object> )listOfObjects.get(i);
            System.out.println(userData.get("first_name") + " "+userData.get("last_name"));

        }

        Assert.assertEquals(deserializedObject.get("per_page"),listOfObjects.size());
        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));

    }



}
