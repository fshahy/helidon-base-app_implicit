package com.fshahy.app.cdi;

import java.util.concurrent.atomic.AtomicInteger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

/**
 * Producer for various resources required by this example.
 */
@ApplicationScoped
public class ResourceProducer {
    private static final AtomicInteger COUNTER = new AtomicInteger();

    /**
     * Each injection will increase the COUNTER.
     *
     * @return increased COUNTER value
     */
    @Produces
    @RequestId
    public int produceRequestId() {
        return COUNTER.incrementAndGet();
    }

    /**
     * Create/get a logger instance for the class that the logger is being injected into.
     *
     * @param injectionPoint injection point
     * @return a logger instance
     */
    @Produces
    @LoggerQualifier
    public java.util.logging.Logger produceLogger(final InjectionPoint injectionPoint) {
        return java.util.logging.Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
