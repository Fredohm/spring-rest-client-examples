package guru.springframework.springrestclientexamples;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RestTemplatesExamples {

    public static final String API_ROOT = "https://api.predic8.de/shop/";

    @Test
     public void getCategories() throws Exception {
        String apiUrl = API_ROOT + "/categories/";

        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode = restTemplate.getForObject(apiUrl, JsonNode.class);

        System.out.println("Response");
        assert jsonNode != null;
        System.out.println(jsonNode.toString());
    }

    @Test
    public void getCustomers() throws Exception {
        String apiUrl = API_ROOT + "/customers/";

        RestTemplate restTemplate = new RestTemplate();

        JsonNode jsonNode = restTemplate.getForObject(apiUrl, JsonNode.class);

        System.out.println("Response");
        assert jsonNode != null;
        System.out.println(jsonNode.toString());
    }

    @Test
    public void createCustomer() throws Exception {
        String apiUrl = API_ROOT + "/customers/";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> postMap = new HashMap<>();
        postMap.put("firstname", "Joe");
        postMap.put("lastname", "Buck");

        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

        System.out.println("Response");
        assert jsonNode != null;
        System.out.println(jsonNode.toString());
    }

    @Test
    public void updateCustomer() throws Exception {
        String apiUrl = API_ROOT + "/customers/";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> postMap = new HashMap<>();
        postMap.put("firstname", "Michael");
        postMap.put("lastname", "Weston");

        JsonNode jsonNode = restTemplate.postForObject(apiUrl, postMap, JsonNode.class);

        System.out.println("Response");
        assert jsonNode != null;
        System.out.println(jsonNode.toString());

        String customerUrl = jsonNode.get("customer_url").textValue();

        String id = customerUrl.split("/")[3];

        System.out.println("Created customer id : " + id);

        postMap.put("firstname", "Mika");
        postMap.put("lastname", "Veston");

        restTemplate.put(apiUrl + id, postMap);

        JsonNode updateNode = restTemplate.getForObject(apiUrl + id, JsonNode.class);

        System.out.println(updateNode.toString());


    }

    @Test(expected = ResourceAccessException.class)
    public void updateCustomerUsingPatchSunHHttp() throws Exception {

    }

    @Test
    public void updateCustomerUsingPatch() throws Exception {

    }

    @Test(expected = HttpClientErrorException.class)
    public void deleteCustomer() throws Exception {

    }
}
