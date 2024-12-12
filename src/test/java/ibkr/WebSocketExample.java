package ibkr;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.junit.jupiter.api.Test;

import java.net.URI;

public class WebSocketExample {

    // @Test
    public void runSocket() {
        String serverUri = "wss://localhost:5000/v1/api/ws";
        String sessionId = "57a3cbbc6fd37968cdb668a4666e104b";

        WebSocketClient webSocketClient = new WebSocketClient(URI.create(serverUri)) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                // WebSocket connection is established.
                // Send authorization message.
                System.out.println("WebSocket connection working 3.");
                String authorizationMessage = "{\"session\": \"" + sessionId + "\"}";
                send(authorizationMessage);
            }

            @Override
            public void onMessage(String message) {
                // Handle WebSocket messages or events here.
                System.out.println("Received message: " + message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                // WebSocket connection closed.
                System.out.println("WebSocket closed. Code: " + code + ", Reason: " + reason);
            }

            @Override
            public void onError(Exception ex) {
                // Handle WebSocket errors here.
                System.out.println("WebSocket connection working 4.");
                ex.printStackTrace();
            }
        };

        // Connect to the WebSocket server.
        System.out.println("WebSocket connection working 1.");
        webSocketClient.connect();
        System.out.println("WebSocket connection working 2.");
    }
}
