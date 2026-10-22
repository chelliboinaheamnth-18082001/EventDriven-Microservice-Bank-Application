
package com.example.hbankbom.config;

import com.thoughtworks.xstream.XStream;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.xml.XStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Bean("eventSerializer")
    public Serializer eventSerializer() {
        XStream xStream = new XStream();

        xStream.allowTypesByWildcard(new String[]{
                "com.example.hbankbom.event.**",
                "com.HBank.**"
        });

        System.out.println("Custom Axon event serializer configured");

        return XStreamSerializer.builder()
                .xStream(xStream)
                .build();
    }
}
