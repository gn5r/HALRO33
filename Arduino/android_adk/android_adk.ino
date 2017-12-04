#include <Max3421e.h>
#include <Usb.h>
#include <AndroidAccessory.h>

AndroidAccessory acc (
  "RO33",
  "RO33Projects",
  "Hello World",
  "0.0.1",
  "http://accessories.android.com/",
  "00000000012345678" );


void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  Serial.print("\r\nStart");
  acc.powerOn();
}

void loop() {
 String text = "Hello World";
  // put your main code here, to run repeated  ly:
  if (acc.isConnected()) {
    acc.write(&text,sizeof(text));
  } else {
  }
  delay(10);
}
