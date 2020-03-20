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

   public class ListOfResouces {

       @Given("user executes API call to get list of resources {string},{string} and validate body's content type,header,status code")
       public void user_executes_API_call_to_get_list_of_resources_and_validate_body_s_content_type_header_status_code(String name, String color)throws URISyntaxException, IOException  {
           HttpClient client= HttpClientBuilder.create().build();
           URIBuilder uri=new URIBuilder();
           uri.setScheme("https");
           uri.setHost("reqres.in");
           uri.setPath("api/unknown");
           uri.setCustomQuery("per_page=12");
           HttpGet get=new HttpGet(uri.build());
           get.setHeader("Accept","application/json");

           HttpResponse response=client.execute(get);
           ObjectMapper objectMapper=new ObjectMapper();
           Map< String,Map<String,Object> >deserializedObject= objectMapper.readValue(response.getEntity().getContent(),
                   new TypeReference<Map<String,Object>>(){});
           String resource="cerulean";
           List <Map<String,Object> >value=(List <Map<String,Object> >)deserializedObject.get("data");

           for(int i=0;i<value.size();i++){
            Assert.assertEquals(name,value.get(i).get("name"));
               Assert.assertEquals(color,value.get(i).get("color"));
           }

           Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
           Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));

       }

   }
