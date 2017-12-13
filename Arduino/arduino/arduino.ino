#include <SoftwareSerial.h>

SoftwareSerial mySerial(15, 14); // RX, TX

void setup() {
   //　ボタン初期設定
  pinMode(13,OUTPUT);//ボタンのLED(赤+・白-)
  pinMode(12,INPUT_PULLUP);//受け取り側 (青+・黒-)
  pinMode(11,OUTPUT);//(黄色+抵抗220Ω・緑-)
  digitalWrite(11,HIGH);//IsolaterのLEDをつける
  
  Serial.begin(9600);
  
  while (!Serial) {
    ; 
  }
  mySerial.begin(115200);
}

void loop() {
  Serial.print(digitalRead(12));
  if(digitalRead(12) == HIGH){
    digitalWrite(13,LOW);
    mySerial.write("left");
  }else{  
    digitalWrite(13,HIGH);
  }
}
