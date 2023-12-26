package com.fshahy.app;

import io.helidon.microprofile.testing.junit5.HelidonTest;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.WebTarget;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * Unit test for {@link HelloWorldResource}.
 */
@HelidonTest
class ImplicitHelloWorldTest {
    private final WebTarget target;

    @Inject
    ImplicitHelloWorldTest(WebTarget target) {
        this.target = target;
    }
    @Test
    void testJsonResource() {
        JsonObject jsonObject = target
                .path("/helloworld/unit")
                .request()
                .get(JsonObject.class);

        assertAll("JSON fields must match expected injection values",
                  () -> assertThat("Name from request", jsonObject.getString("name"), is("unit")),
                  () -> assertThat("Request id from CDI provider", jsonObject.getInt("requestId"), is(1)),
                  () -> assertThat("App name from config", jsonObject.getString("appName"), is("Helidon Base App")),
                  () -> assertThat("Logger name", jsonObject.getString("logger"), is(HelloWorldResource.class.getName()))
        );

    }
}
