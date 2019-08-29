package com.juli;

import com.juli.models.Statistics;
import com.juli.services.MyService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitTest {

    @Autowired
    MyService service;

    @Test
    @Order(1)
    public void getStatistics() {
        Statistics expected = new Statistics("0.00", "0.00", "0.00", "0.00", 0);
        Statistics actual = service.getStatistics();
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    public void postTransaction() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneOffset.UTC);
        LocalDateTime now = LocalDateTime.now(Clock.systemUTC());
        now = now.minusSeconds(30);

        String request = "{" +
            "\"amount\": \"133.20\"," +
            "\"timestamp\": \""+now.format(dateTimeFormatter)+"\"" +
            "}";

        HttpStatus actualStatus = service.postTransactions(request);
        HttpStatus expectedStatus = HttpStatus.CREATED;
        assertEquals(actualStatus, expectedStatus);

        now = now.minusSeconds(61);
        request = "{" +
            "\"amount\": \"133.20\"," +
            "\"timestamp\": \""+now.format(dateTimeFormatter)+"\"" +
            "}";
        actualStatus = service.postTransactions(request);
        expectedStatus = HttpStatus.NO_CONTENT;
        assertEquals(actualStatus, expectedStatus);

        request = "{" +
            "\"amount\": \"133.20\"," +
            "\"timestamp\": \"4/23/2018 11:32 PM\"" +
            "}";
        actualStatus = service.postTransactions(request);
        expectedStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        assertEquals(actualStatus, expectedStatus);

        now = now.minusSeconds(1);
        request = "{" +
            "\"amount\": \"One hundred\"," +
            "\"timestamp\": \""+now.format(dateTimeFormatter)+"\"" +
            "}";
        actualStatus = service.postTransactions(request);
        expectedStatus = HttpStatus.UNPROCESSABLE_ENTITY;
        assertEquals(actualStatus, expectedStatus);

        request = "Hello World!";
        actualStatus = service.postTransactions(request);
        expectedStatus = HttpStatus.BAD_REQUEST;
        assertEquals(actualStatus, expectedStatus);

        request = "{" +
            "\"amount\": \"One hundred\" " +
            "\"timestamp\": \""+now.format(dateTimeFormatter)+"\"" +
            "}";
        actualStatus = service.postTransactions(request);
        expectedStatus = HttpStatus.BAD_REQUEST;
        assertEquals(actualStatus, expectedStatus);

    }

    @Test
    @Order(3)
    public void deleteTransactions() {
        HttpStatus actualStatus = service.deleteTransactions();
        HttpStatus expectedStatus = HttpStatus.NO_CONTENT;
        assertEquals(actualStatus, expectedStatus);
    }

}
