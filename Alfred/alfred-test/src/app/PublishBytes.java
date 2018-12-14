package app;

import java.nio.charset.StandardCharsets;

import io.nats.client.Connection;
import io.nats.client.Nats;

public class PublishBytes {

    public static void publish() {

        try {
            // [begin publish_bytes]
            Connection nc = Nats.connect("nats://dev.lookingfora.name:4222");

            nc.publish("irc.message.send", "{\"channel\":\"##cesi\",\"message\":\"HELLO FUCKIN' WORLD!!!\"}".getBytes(StandardCharsets.UTF_8));
            nc.wait(500);
            nc.close();
            // [end publish_bytes]
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}