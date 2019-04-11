package sometest.nettyserver;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

/**
 * Created by gentle-hu on 2018/8/24 1:10.
 * Email:me@gentlehu.com
 */
public class ChatServer {

    public static void main(String[] args) throws Exception  {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("chatevent",ChatObject.class, (client, data, ackRequest) -> {
                server.getBroadcastOperations().sendEvent("chatevent", data);
            }
        );
        server.start();
        Thread.sleep(Integer.MAX_VALUE);
        server.stop();

    }
}
