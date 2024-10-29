package com.objectway.codemotion.suitability;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import io.camunda.zeebe.client.ZeebeClient;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public abstract class AbstractSuitabilityTest {
	
	@MockBean
    private ZeebeClient zeebeClient;

}
