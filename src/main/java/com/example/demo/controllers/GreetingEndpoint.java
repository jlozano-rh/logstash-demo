package com.example.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.logstash.logback.argument.StructuredArguments;

@RestController
public class GreetingEndpoint {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingEndpoint.class);

    @RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET, produces = { "application/json" })
    String greeting(@PathVariable @DefaultValue("World") String name) {

        Map<String, Object> additionalFields = new HashMap<String, Object>();
        additionalFields.put("app.call", "REST");
        additionalFields.put("app.protocol", "HTTP");
        additionalFields.put("app.endpoint", "greeting");
        additionalFields.put("app.type", "GET");
        additionalFields.put("app.step", "START");

        LOG.info("processing started", StructuredArguments.entries(additionalFields));

        final String message = String.format("Hola %s", name);
        additionalFields.put("app.step", "END");
        LOG.warn("processing completed", StructuredArguments.entries(additionalFields));

        return message;
    }
}
