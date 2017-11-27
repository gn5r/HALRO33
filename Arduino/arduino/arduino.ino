#include <SoftwareSerial.h>

SoftwareSerial mySerial(15, 14); // RX, TX

void setup() {
  
  Serial.begin(9600);
  
  while (!Serial) {
    ; 
  }

  mySerial.begin(115200);
  mySerial.println("Hello, world");
}

void loop() {
  mySerial.write("A");
  delay(3000);
}
