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

public class SingleUser {


    @Given("the user execute the API call to get single user info and validate content-type,status code,parameters")
    public void the_user_execute_the_API_call_to_get_single_user_info_and_validate_content_type_status_code_parameters(Map<String,String> expected) throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users/2");

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");

        HttpResponse httpResponse = httpClient.execute(httpGet);


        ObjectMapper objectMapper=new ObjectMapper();
        Map<String,Map<String,Object>> deserializedObject= objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String,Object>>(){});
        Assert.assertEquals(deserializedObject.get("data").get("first_name"),expected.get("firstname"));
        Assert.assertEquals(deserializedObject.get("data").get("last_name"),expected.get("lastname"));
        Assert.assertTrue(httpResponse.getEntity().getContentType().getValue().contains("application/json"));
        Assert.assertEquals( HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());


        System.out.println("Content type response is " + httpResponse.getEntity().getContentType().getValue());

        System.out.println("Status code of my  api call is : " + httpResponse.getStatusLine().getStatusCode());


    }

    }





