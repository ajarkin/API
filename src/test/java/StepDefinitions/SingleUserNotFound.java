package StepDefinitions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
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

public class SingleUserNotFound {

    @Given("user executes Api call to verify single user not found")
    public void user_executes_Api_call_to_verify_single_user_not_found() throws URISyntaxException, IOException {


        HttpClient client= HttpClientBuilder.create().build();
        URIBuilder uri=new URIBuilder();
        uri.setScheme("https");
        uri.setHost("reqres.in");
        uri.setPath("api/users/15");
        HttpGet get=new HttpGet(uri.build());
        get.setHeader("Accept","application/json");

        HttpResponse response=client.execute(get);
        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Object> deserializedObject= objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String,Object>>(){});

        Assert.assertTrue(deserializedObject.isEmpty());
        Assert.assertEquals(HttpStatus.SC_NOT_FOUND,response.getStatusLine().getStatusCode());
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));





    }



}
