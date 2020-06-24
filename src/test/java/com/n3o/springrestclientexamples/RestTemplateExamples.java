package com.n3o.springrestclientexamples;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RestTemplateExamples {
    public static final String API_ROOT = "https://api.predic8.de:443/shop";

    @Test
    void getCategories() {
        String apiUrl = API_ROOT + "/categories/";
        RestTemplate restTemplate = new RestTemplate();
        JsonNode jsonNode = restTemplate.getForObject(apiUrl,JsonNode.class);
        //response
        System.out.println("Response");
        System.out.println(jsonNode.toString());
    }

    @Test
    void getCustomers() {
        String apiUrl = API_ROOT + "/customers/";
        RestTemplate restTemplate = new RestTemplate();
        JsonNode jsonNode = restTemplate.getForObject(apiUrl,JsonNode.class);
        //response
        System.out.println("Response");
        System.out.println(jsonNode.toString());
    }

    @Test
    void createCustomers() {
        String apiUrl = API_ROOT + "/customers/";
        RestTemplate restTemplate = new RestTemplate();
        //Java Object to parse to JSON
        Map<String, Object> postMap = new HashMap<>();
        //post data
        postMap.put("firstname","Nilesh");
        postMap.put("lastname","Choudhary");
        JsonNode jsonNode = restTemplate.postForObject(apiUrl,postMap,JsonNode.class);
        //response
        System.out.println("Response");
        System.out.println(jsonNode.toString());
    }

    @Test
    void updateCustomers() {

        /* Creating the object*/
        String apiUrl = API_ROOT + "/customers/";
        RestTemplate restTemplate = new RestTemplate();
        //Java Object to parse to JSON
        Map<String, Object> postMap = new HashMap<>();
        //post data
        postMap.put("firstname","Nilesh");
        postMap.put("lastname","Choudhary");
        JsonNode jsonNode = restTemplate.postForObject(apiUrl,postMap,JsonNode.class);
        //response
        System.out.println("Response");
        System.out.println(jsonNode.toString());

        /*Updating the object*/
        String customerUrl = jsonNode.get("customer_url").textValue();
        String id = customerUrl.split("/")[3];
        System.out.println("Created Customer Id:" + id);
        //Updated post data
        postMap.put("firstname","N3O");
        postMap.put("lastname","idk");
        restTemplate.put(apiUrl + id,postMap);
        JsonNode updatedNode = restTemplate.getForObject(apiUrl + id,JsonNode.class);
        //response
        System.out.println("Response");
        System.out.println(jsonNode.toString());
    }

    @Test
    void updateCustomersUsingPatchSunHttp() {
        Assert.assertThrows(ResourceAccessException.class,()->
        {
        //create customer to update
        String apiUrl = API_ROOT + "/customers/";
        RestTemplate restTemplate = new RestTemplate();

        //Java object to parse to JSON
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("firstname", "Nilesh");
        postMap.put("lastname", "Choudhary");
        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);
        //response
        System.out.println("Response");
        System.out.println(jsonNode.toString());
        //patching
        String customerUrl = jsonNode.get("customer_url").textValue();
        String id = customerUrl.split("/")[3];
        System.out.println("Created customer id: " + id);
        postMap.put("firstname", "Sam 2");
        postMap.put("lastname", "Axe 2");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(postMap, headers);

        //fails due to sun.net.www.protocol.http.HttpURLConnection not supporting patch
        JsonNode updatedNode = restTemplate.patchForObject(apiUrl + id, entity, JsonNode.class);

        System.out.println(updatedNode.toString());
        }
        );
    }

    @Test
    void updateCustomersUsingPatch() {
        //create customer to update
        String apiUrl = API_ROOT + "/customers/";

        // Use Apache HTTP client factory
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        //Java object to parse to JSON
        Map<String, Object> postMap = new HashMap<>();
        postMap.put("firstname", "N3O");
        postMap.put("lastname", "idk");

        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

        System.out.println("Response");
        System.out.println(jsonNode.toString());

        String customerUrl = jsonNode.get("customer_url").textValue();

        String id = customerUrl.split("/")[3];

        System.out.println("Created customer id: " + id);

        postMap.put("firstname", "N3O7");
        postMap.put("lastname", "lul");

        //example of setting headers
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(postMap, headers);

        JsonNode updatedNode = restTemplate.patchForObject(apiUrl + id, entity, JsonNode.class);

        System.out.println(updatedNode.toString());
    }

    @Test
    public void deleteCustomer(){
        Assert.assertThrows(HttpClientErrorException.class,()->
        {
            //create customer to update
            String apiUrl = API_ROOT + "/customers/";
            RestTemplate restTemplate = new RestTemplate();
            //Java object to parse to JSON
            Map<String, Object> postMap = new HashMap<>();
            postMap.put("firstname", "Les");
            postMap.put("lastname", "Claypool");
            JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);
            //response
            System.out.println("Response");
            System.out.println(jsonNode.toString());

            //delete
            String customerUrl = jsonNode.get("customer_url").textValue();
            String id = customerUrl.split("/")[3];
            System.out.println("Created customer id: " + id);
            restTemplate.delete(apiUrl + id); //expects 200 status
            //response
            System.out.println("Customer deleted");
            //This will throw error404, because file is deleted obviously lulll
            restTemplate.getForObject(apiUrl + id, JsonNode.class);
        });

    }
}
