#include <Max_LCD.h>
#include <Usb.h>
#include <Max3421e.h>
#include <Max3421e_constants.h>
#include <ch9.h>

#include <AndroidAccessory.h>


AndroidAccessory acc("HAL ",          //第1引数:組織名
"Sample",                                 //第2引数:存在しないアプリのタイトル名
"このAndroidはADKが使えます ADK Connection Test",       //第3引数:ダイアログ表示メッセージ
"1.0",                                               //第4引数:バージョン
"http://accessories.android.com/",                   //第5引数:ジャンプ先URL
"0000000012345678");   

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  acc.powerOn();
}

void loop() {
  // put your main code here, to run repeatedly:
  if (acc.isConnected()) { //Androidを起動・接続する命令を送る(3)
    //communicate with Android application(4)
    Serial.println("Hello");    
  } else {
    //set the accessory to its default state(5)
  }
  delay(1000);
}
