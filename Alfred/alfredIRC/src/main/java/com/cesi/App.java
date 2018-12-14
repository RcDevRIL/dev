package com.cesi;
import java.nio.charset.StandardCharsets;
import io.nats.jnats.Connection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Connection nc = Nats.connect("nats://dev.lookingfora.name:4222");
        nc.publish("irc.message.send", "{\"channel\":\"##cesi\",\"message\":\"Hello world\"}".getBytes(StandardCharsets.UTF_8));
    }
}
