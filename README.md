[![](https://jitpack.io/v/QwertyMo/Packager.svg)](https://jitpack.io/#QwertyMo/Packager)

# Packager
Packet byte array system

# How to use it
First of all, you need to create packet. Example of packet, contains string 


```
@Protocol(1) //ID of packet
class TextPacket : Packet {

    var name: String = ""
    constructor(buf: ByteBuf) : super(buf){
        name = readString() //Read string 
    }

    constructor(
        name: String
    ) : super(){
        writeString(name) //Put string
    }
}
```

Annotation @Protocol indicates, that this packet has ID 1. This ID writes to first bytes in packet

Then, we need to initialize our packet manager and this packet 

```
private val manager: PacketManager = PacketManager()

init {
    manager.registerPacket(TextPacket::class)
}
```

After that, we can write our packets...

```
val string = "Test string"
val packetOut = TextPacket(string)
```

...and read this packet

```
val packetIn  = manager.readPacket(packetOut.toByteBuff()) as TextPacket
```

So, we create our packet at variable **packetOut**. We can send it via websocket as ByteBuf, and receive it in **packetIn**. Now, we can get all data, contains in this packet

# Implementation

Step 1. Add the JitPack repository to your build file

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```
dependencies {
    implementation 'com.github.QwertyMo:Packager:{version}'
}
```
