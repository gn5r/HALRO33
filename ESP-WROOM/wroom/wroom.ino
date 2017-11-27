#include <ESP8266WiFi.h>

const char* ssid = "hoge00";
const char* password = "1234";

WiFiServer server(5555);
WiFiClient client;

void connectWiFi(const char* ssid ,const char* password) {
   
  WiFi.disconnect();
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid ,password);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(100);
    Serial.print(".");
  }
  Serial.println();
  Serial.println("WiFi connected");
  Serial.print("IP address: ");
  Serial.println(WiFi.localIP());
}

void setup() {
  Serial.begin(115200);
  connectWiFi(ssid ,password);
  server.begin();
  client = server.available();
 
}

void loop() {
   
  while ((WiFi.status() != WL_CONNECTED)) {
    connectWiFi(ssid ,password);
  }

  while (!client) {
    client = server.available();
    delay(10);
  }
  
  while (!client.available()) {
    delay(10);
  }
  Serial.println("client connected");
  
  String res = client.readStringUntil('\n');
  Serial.println(res);
  client.flush();
  client.stop();
}
