package com.fshahy.app;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;

/**
 * Resource showing all possible configuration injections.
 */
@Path("another")
@RequestScoped
public class AnotherResource {
    @Inject
    @ConfigProperty(name = "app.nonExistent", defaultValue = "145")
    private int defaultValue;

    @Inject
    @ConfigProperty(name = "app.nonExistent")
    private Optional<String> empty;

    @Inject
    @ConfigProperty(name = "app.uri")
    private Optional<URI> full;

    @Inject
    @ConfigProperty(name = "app.someInt")
    private Provider<Integer> provider;

    @Inject
    @ConfigProperty(name = "app.ints")
    private List<Integer> ints;

    @Inject
    @ConfigProperty(name = "app.ints")
    private Optional<List<Integer>> optionalInts;

    @Inject
    @ConfigProperty(name = "app.ints")
    private Provider<List<Integer>> providedInts;

    @Inject
    @ConfigProperty(name = "app.ints")
    private int[] intsArray;

    @Inject
    @ConfigProperty(name = "app")
    private Map<String, String> detached;

    @Inject
    private Config mpConfig;

    @Inject
    private io.helidon.config.Config helidonConfig;

    /**
     * Get method to validate that all injections worked.
     *
     * @return data from all fields of this class
     */
    @GET
    public String get() {
        return toString();
    }

    @Override
    public String toString() {
        return "AnotherResource{"
                + "defaultValue=" + defaultValue
                + ", empty=" + empty
                + ", full=" + full
                + ", provider=" + provider + "(" + provider.get() + ")"
                + ", ints=" + ints
                + ", optionalInts=" + optionalInts
                + ", providedInts=" + providedInts + "(" + providedInts.get() + ")"
                + ", detached=" + detached
                + ", microprofileConfig=" + mpConfig
                + ", helidonConfig=" + helidonConfig
                + ", intsArray=" + Arrays.toString(intsArray)
                + '}';
    }
}
