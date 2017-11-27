
#include<WiFiUdp.h>
#include <SerialESP8266wifi.h>

const char *APSSID = "hoge00";
const char *APPASS = "1234";
unsigned int localPort = 5555;

WiFiUDP UDP;
char packetBuffer[255];

static const char *udpReturnAddr = "192.168.4.2";
static const int udpReturnPort = 5555;

void setup() {
    pinMode(13,OUTPUT);//ボタンのLED(+赤・-白)
    pinMode(12,INPUT_PULLUP);//受け取り側 (+青・-黒)
    pinMode(11,OUTPUT);   //+黄色・-緑
    digitalWrite(11,HIGH);//IsolaterのLEDをつける

    Serial.begin(115200);
    Serial.println();
 
    WiFi.softAP(APSSID, APPASS);
 
    IPAddress myIP = WiFi.softAPIP();
    Serial.print("AP IP address: ");
    Serial.println(myIP);
    UDP.begin(localPort)
}

void loop()
{
  if(digitalRead(12) == HIGH){
        digitalWrite(13,LOW);
        UDP.beginPacket(udpReturnAddr, udpReturnPort);
        UDP.write("b1");
        UDP.endPacket();
  }else {
    digitalWrite(13,HIGH);
  }
}
