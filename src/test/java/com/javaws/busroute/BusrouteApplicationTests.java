package com.javaws.busroute;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.http.Body;
import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@SpringBootTest
class BusrouteApplicationTests {

//	initializing wiremock server with application port
	static WireMockServer wireMockServer = new WireMockServer(WireMockConfiguration.options().port(3000));

	@BeforeAll
	public static void setup() {
		wireMockServer.start();

//      configuring for localhost to use the same as application server port
		configureFor("localhost", 3000);

//		stubbing the same request that was used in test case
		stubFor(get(urlEqualTo("/buses/123"))
				.willReturn(aResponse()
						.withStatus(200)
						.withBody("Controller functionality working!!")));
	}

//	stop wiremock server after usage
	@AfterAll
	public static void stopServer() {
		wireMockServer.stop();
	}

	@Karate.Test
	Karate testBusController() {
		return Karate.run("classpath:BusController.feature").relativeTo(BusrouteApplicationTests.class);
	}

}
