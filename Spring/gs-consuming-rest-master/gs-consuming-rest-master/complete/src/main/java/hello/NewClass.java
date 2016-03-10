/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hello;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author oscar
 */
public class NewClass {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> var = new HashMap<String, String>();
        
        var.put("name","kk");
        Greeting quote = restTemplate.getForObject("http://localhost:8080/greeting/{name}", Greeting.class,var );
        System.out.println(quote.getContent());
    }
    
}
