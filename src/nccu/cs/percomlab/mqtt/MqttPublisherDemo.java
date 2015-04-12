package nccu.cs.percomlab.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * MqttPublisherDemo
 * 
 * @author Chun-Feng Liao (2015/4/12)
 */
public class MqttPublisherDemo
{

    public static void main(String[] args)
    {
        String clientId = "cfliao";
        MemoryPersistence persistence = new MemoryPersistence();
        try
        {
            // connect
            MqttClient sampleClient = new MqttClient("tcp://localhost:1883", clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);
            
            // prepare a message
            String content = "Message from MqttPublishSample";
            System.out.println("Publishing message: " + content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(0);// qos set to 0
            
            // publish a message to a Topic: "testtopic"
            sampleClient.publish("testtopic", message);
            System.out.println("Message published");
            
            // disconnect
            sampleClient.disconnect();
            System.out.println("Disconnected");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
