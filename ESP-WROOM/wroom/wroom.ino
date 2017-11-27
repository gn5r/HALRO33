#include <ESP8266WiFi.h>

#include <WiFiUdp.h>


const char *APSSID = "WROOM_001";
const char *APPASS = "123abc";
unsigned int localPort = 5555;

WiFiUDP UDP;
char packetBuffer[255];

static const char *udpReturnAddr = "";
static const int udpReturnPort = 5555;

void setup() {
    Serial.begin(115200);
    Serial.println();
 
    WiFi.softAP(APSSID, APPASS);
 
    IPAddress myIP = WiFi.softAPIP();
    Serial.print("AP IP address: ");
    Serial.println(myIP);
    UDP.begin(localPort);
}

void loop()
{
  UDP.beginPacket(udpReturnAddr, udpReturnPort);
  UDP.write("Hello World");
  UDP.endPacket();
}
