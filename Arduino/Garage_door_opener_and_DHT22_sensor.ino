/*
 *  ESP8266 NodeMCU MQTT Example
 *  Savke
 *  
 */
#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include <Adafruit_Sensor.h>
#include <DHT.h>
#include <Arduino.h>

#define DHTPIN 4     // Digital pin connected to the DHT sensor
#define DHTTYPE DHT22   // DHT 22 (AM2302)
DHT dht(DHTPIN, DHTTYPE);

// current temperature & humidity, updated in loop()
float t = 0.0;
float h = 0.0;

// pin for relay
const int relay = 5;


//Enter your wifi credentials
const char* ssid = "";  
const char* password =  "";

//Enter your mqtt server configurations
const char* mqttServer = "";    //Enter Your mqttServer address
const int mqttPort = 1883;       //Port number
const char* mqttUser = ""; //User
const char* mqttPassword = ""; //Password

WiFiClient espClient;
PubSubClient client(espClient);

void setup() {
  delay(1000);
  dht.begin();
  pinMode(relay, OUTPUT);
  digitalWrite(relay, HIGH); 
  Serial.begin(115200);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.println("Connecting to WiFi..");
  }
  Serial.print("Connected to WiFi :");
  Serial.println(WiFi.SSID());
  connect();
}  

void connect() {
  client.setServer(mqttServer, mqttPort);
  client.setCallback(MQTTcallback);  

  while (!client.connected()) {
   Serial.println("Connecting to MQTT...");

   if (client.connect("ESP8266", mqttUser, mqttPassword )) {
 
     Serial.println("connected");  
 
   } else {
 
    Serial.print("failed with state ");
    Serial.println(client.state());  //If you get state 5: mismatch in configuration
    delay(1000);
 
     }
  } 

  client.subscribe("Door");  
}

void MQTTcallback(char* topic, byte* payload, unsigned int length) {
 
  Serial.print("Message arrived in topic: ");
  Serial.println(topic);
 
  Serial.print("Message:");

  String message;
  for (int i = 0; i < length; i++) {
    message = message + (char)payload[i];  //Conver *byte to String
  }
   Serial.print(message);
  if(message == "press") {
    digitalWrite(relay, LOW);
    delay(2000);
    digitalWrite(relay, HIGH); 
    }
  
  if(message == "off") {digitalWrite(relay,LOW);} 
 
  Serial.println();
  Serial.println("-----------------------");  
}


void loop() {
  client.loop();
  
  t = dht.readTemperature();
  client.publish("topic",String(t).c_str());

  if(!client.connected()){
    Serial.println("MQTT client disconnected");
    connect();
    }

  delay(1000);    
  
}