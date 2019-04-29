package com.twinero.banking;

import com.twinero.banking.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BankingApplicationTests extends JPAHibernateTest {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    private Map<String, String> mockBalanceCustormer;
    private HttpHeaders headers;


    @Before
    public void setup() {
        mockBalanceCustormer = new HashMap<>();
        mockBalanceCustormer.put("balance", "1408");
        mockBalanceCustormer.put("user", "TestUser");
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void should_be_able_to_sign_up() {
        Customer customer = new Customer("TestSignUp", "xxxxxx");
        HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/banking/signup/"), HttpMethod.POST, entity, String.class);
        assertThat(response.getStatusCodeValue(), is(200));
    }

    @Test
    public void should_be_able_to_deposit_money() {
        Map<String, String> body = new HashMap<>();
        body.put("amount", "123.12");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/banking/deposit/1"), HttpMethod.POST, entity, String.class);
        assertThat(response.getStatusCodeValue(), is(200));
    }

    @Test
    public void should_be_able_to_withdraw_money() {
        Map<String, String> body = new HashMap<>();
        body.put("withdraw", "123.15");
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/banking/withdraw/1"), HttpMethod.POST, entity, String.class);
        assertThat(response.getStatusCodeValue(), is(200));
    }

    @Test
    public void should_be_able_to_see_acount_balance() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(createURLWithPort("/banking/balance/1")).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        JSONAssert.assertEquals(mockBalanceCustormer.toString(), result.getResponse().getContentAsString(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
