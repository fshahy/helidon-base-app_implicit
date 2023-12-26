package com.fshahy.app;

import java.net.URI;
import java.util.Collections;
import java.util.logging.Logger;

import io.helidon.config.Config;
import com.fshahy.app.cdi.LoggerQualifier;
import com.fshahy.app.cdi.RequestId;
import com.fshahy.app.cdi.ResourceProducer;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Resource for hello world example.
 */
@Path("helloworld")
@RequestScoped
public class HelloWorldResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    private final Config config;
    private final Logger logger;
    private final int requestId;
    private final String applicationName;
    private final URI applicationUri;
    private BeanManager beanManager;

    /**
     * Using constructor injection for field values.
     *
     * @param config      configuration instance
     * @param logger      logger (from {@link ResourceProducer}
     * @param requestId   requestId (from {@link ResourceProducer}
     * @param appName     name from configuration (app.name)
     * @param appUri      URI from configuration (app.uri)
     * @param beanManager bean manager (injected automatically by CDI)
     */
    @Inject
    public HelloWorldResource(Config config,
                              @LoggerQualifier Logger logger,
                              @RequestId int requestId,
                              @ConfigProperty(name = "app.name") String appName,
                              @ConfigProperty(name = "app.uri") URI appUri,
                              BeanManager beanManager) {
        this.config = config;
        this.logger = logger;
        this.requestId = requestId;
        this.applicationName = appName;
        this.applicationUri = appUri;
        this.beanManager = beanManager;
    }

    /**
     * Get method for this resource, shows logger and request id.
     *
     * @return hello world
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String message() {
        return "Hello World: " + logger + ", request: " + requestId + ", appName: " + applicationName;
    }

    /**
     * Get method for this resource, returning JSON.
     *
     * @param name name to add to response
     * @return JSON structure with injected fields
     */
    @Path("/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getHello(@PathParam("name") String name) {
        return JSON.createObjectBuilder()
                .add("name", name)
                .add("requestId", requestId)
                .add("appName", applicationName)
                .add("appUri", String.valueOf(applicationUri))
                .add("config", config.get("server.port").asInt().get())
                .add("beanManager", beanManager.toString())
                .add("logger", logger.getName())
                .build();
    }
}
