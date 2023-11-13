#include <WiFiManager.h> // https://github.com/tzapu/WiFiManager
#include <PubSubClient.h>
#include "DHT.h"


#define DHTPIN 26     // Digital pin connected to the DHT sensor
#define DOOR_SENSOR_PIN  32
#define RELAY_PIN_KOTAO 25
#define RELAY_PIN_GARAGE_DOOR 14

#define DHTTYPE DHT22


int doorState;


//Enter your mqtt server configurations
const char* mqttServer = "";    //Enter Your mqttServer address
const int mqttPort = ;       //Port number
const char* mqttUser = ""; //User
const char* mqttPassword = ""; //Password
// END MQTT CONFIGURATION

WiFiClient espClient;
PubSubClient client(espClient);
DHT dht(DHTPIN, DHTTYPE);

void setup() {
  Serial.begin(9600);                     // initialize serial  
  delay(1000);

  pinMode(RELAY_PIN_GARAGE_DOOR, OUTPUT);
  digitalWrite(RELAY_PIN_GARAGE_DOOR, HIGH);

  pinMode(DOOR_SENSOR_PIN, INPUT_PULLUP); // set ESP32 pin to input pull-up mode
  digitalWrite(DOOR_SENSOR_PIN, HIGH);

  pinMode(RELAY_PIN_KOTAO, OUTPUT);
  digitalWrite(RELAY_PIN_KOTAO, LOW);


  dht.begin();
  DHT dht(DHTPIN, DHTTYPE);

  WiFi.mode(WIFI_STA);
  WiFiManager wm;
  bool res;
  res = wm.autoConnect("AutoConnectAP","password");
  if(!res) {
    Serial.println("Failed to connect");
  }
  else {
    Serial.println("connected...yeey :)");
  }
}



void connect() {
  client.setServer(mqttServer, mqttPort);
  client.setCallback(MQTTcallback);  

  while (!client.connected()) {
   Serial.println("Connecting to MQTT...");

   if (client.connect("ESP32-WROOM32", mqttUser, mqttPassword )) {
 
     Serial.println("connected");  
 
   } else {
 
    Serial.print("failed with state ");
    Serial.println(client.state());  //If you get state 5: mismatch in configuration
    delay(1000);
 
     }
     
  } 


  client.subscribe("SavkeHOME/garageOC");
  client.subscribe("SavkeHOME/kotao");
  client.subscribe("SavkeHOME/controlGarageDoor");
  client.publish("SavkeHOME/kotaoStatus",String("off").c_str());

}

void MQTTcallback(char* topic, byte* payload, unsigned int length) {
 
  if (strcmp(topic,"SavkeHOME/controlGarageDoor")==0){
    String controlGarageDoor;
    for (int i = 0; i < length; i++) {
      controlGarageDoor = controlGarageDoor + (char)payload[i];  //Conver *byte to String
    }

    if(controlGarageDoor == "press") {
      digitalWrite(RELAY_PIN_GARAGE_DOOR, LOW);
      delay(2000);
      digitalWrite(RELAY_PIN_GARAGE_DOOR, HIGH);
      }
  }

  if (strcmp(topic,"SavkeHOME/kotao")==0){
    String kotao;
    for (int i = 0; i < length; i++) {
      kotao = kotao + (char)payload[i];
    }

    client.publish("SavkeHOME/kotaoStatus",String(kotao).c_str());

    if (kotao=="off"){
      digitalWrite(RELAY_PIN_KOTAO, LOW);
    }
      else if (kotao=="on"){
        digitalWrite(RELAY_PIN_KOTAO, HIGH);
      } 
  }     

}



void publishTH(float T, float H) {
  client.publish("SavkeHOME/garageTemp",String(T).c_str());
  client.publish("SavkeHOME/garageHumidity",String(H).c_str());
}

void publishGarageDoorState(int doorState) {
  if (doorState == HIGH) {
    // Serial.println("The door is open");
    client.publish("SavkeHOME/garagestatus",String("open").c_str());
  } else {
    // Serial.println("The door is closed");
    client.publish("SavkeHOME/garagestatus",String("closed").c_str());  
  }
}

void loop() {
  client.loop();

  if(!client.connected()){
    Serial.println("MQTT client disconnected");
    connect();
  }

  delay(2000);
  doorState = digitalRead(DOOR_SENSOR_PIN); // read state

  publishGarageDoorState(doorState);
  publishTH(dht.readTemperature(),dht.readHumidity());

}
