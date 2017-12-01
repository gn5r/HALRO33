#include <ESP8266WiFi.h>
#include <WiFiUdp.h>

const char *APSSID = "wroom001";
const char *APPASS = "r01vy.net";
unsigned int localPort = 8888;

WiFiUDP UDP;
char packetBuffer[255];

static const char *udpReturnAddr = "192.168.4.2";
static const int udpReturnPort = 1234;

void setup() {
    Serial.begin(115200);
    Serial.println();

    delay(1000);
    Serial.println("start WiFi");
    
    WiFi.softAP(APSSID, APPASS);
 
    IPAddress myIP = WiFi.softAPIP();
    Serial.print("AP IP address: ");
    Serial.println(myIP);
    UDP.begin(localPort);
}

void loop()
{
  int i = Serial.read();
  Serial.println(i);
  
  Serial.println("start connect");
  UDP.beginPacket(udpReturnAddr, udpReturnPort);
  UDP.write("Hello World");
  UDP.endPacket();
  delay(3000);
}
