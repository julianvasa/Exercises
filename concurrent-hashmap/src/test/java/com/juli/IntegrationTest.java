package com.juli;

import com.juli.models.Statistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void RestAPIs() {
        restTemplate.delete("http://localhost:" + port + "/transactions", String.class);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneOffset.UTC);
        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
        now = now.minusSeconds(10);

        String request = "{" +
            "\"amount\": \"133.20\"," +
            "\"timestamp\": \""+now.format(dateTimeFormatter)+"\"" +
            "}";

        HttpStatus expected = HttpStatus.CREATED;
        assertThat(restTemplate.postForEntity("http://localhost:" + port + "/transactions", request, String.class).getStatusCode()).isEqualTo(expected);

        now = now.minusSeconds(5);
        request = "{" +
            "\"amount\": \"11.50\"," +
            "\"timestamp\": \""+now.format(dateTimeFormatter)+"\"" +
            "}";

        expected = HttpStatus.CREATED;
        assertThat(restTemplate.postForEntity("http://localhost:" + port + "/transactions", request, String.class).getStatusCode()).isEqualTo(expected);

        now = now.minusSeconds(2);
        request = "{" +
            "\"amount\": \"1363.60\"," +
            "\"timestamp\": \""+now.format(dateTimeFormatter)+"\"" +
            "}";

        expected = HttpStatus.CREATED;
        assertThat(restTemplate.postForEntity("http://localhost:" + port + "/transactions", request, String.class).getStatusCode()).isEqualTo(expected);

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/statistics", Statistics.class).getSum()).isEqualTo("1508.30");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/statistics", Statistics.class).getAvg()).isEqualTo("502.77");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/statistics", Statistics.class).getMax()).isEqualTo("1363.60");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/statistics", Statistics.class).getMin()).isEqualTo("11.50");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/statistics", Statistics.class).getCount()).isEqualTo(3);


        try {
            Thread.sleep(60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/statistics", Statistics.class).getSum()).isEqualTo("0.00");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/statistics", Statistics.class).getAvg()).isEqualTo("0.00");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/statistics", Statistics.class).getMax()).isEqualTo("0.00");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/statistics", Statistics.class).getMin()).isEqualTo("0.00");
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/statistics", Statistics.class).getCount()).isEqualTo(0);

        now = now.minusMinutes(20);
        request = "{" +
            "\"amount\": \"1363.60\"," +
            "\"timestamp\": \""+now.format(dateTimeFormatter)+"\"" +
            "}";

        expected = HttpStatus.NO_CONTENT;
        assertThat(restTemplate.postForEntity("http://localhost:" + port + "/transactions", request, String.class).getStatusCode()).isEqualTo(expected);

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/statistics", Statistics.class).getCount()).isEqualTo(0);

        restTemplate.delete("http://localhost:" + port + "/transactions", String.class);

    }

}
