[![](https://jitpack.io/v/QwertyMo/Packager.svg)](https://jitpack.io/#QwertyMo/Packager)

# Packager
Packet byte array system

# How to use it
First of all, you need to create packet. Example of packet, contains string 


```kotlin
@Protocol(1) //ID of packet
class TextPacket : Packet {

    var name: String = ""
    constructor(buf: ByteArray) : super(buf){
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

```kotlin
private val manager: PacketManager = PacketManager()

init {
    manager.registerPacket(TextPacket::class)
}
```

After that, we can write our packets...

```kotlin
val string = "Test string"
val packetOut = TextPacket(string)
```

...and read this packet

```kotlin
val packetIn  = manager.readPacket(packetOut.toByteArray()) as TextPacket
```

So, we create our packet at variable **packetOut**. We can send it via websocket as ByteBuf, and receive it in **packetIn**. Now, we can get all data, contains in this packet

# Packaging custom objects

Sometimes, you need to send array of custom object. For this you can use **Packable** class

```kotlin
class Player : Packable {
    var name: String = ""
    var maxHP: Int = -1
    constructor(buf: ByteArray) : super(buf){
        name = readString()
        maxHP = readInt()
    }

    constructor(
        name: String,
        maxHP: Int,
    ) : super(){
        writeString(name)
        writeInt(maxHP)
    }
}
```
Now, we have our custom class, which we can apply to package

```kotlin
@Protocol(4)
class PlayersPacket : Packet {
    var players: List<Player> = emptyList()
    constructor(buf: ByteArray) : super(buf){
        players = readPackableList()
    }

    constructor(
        players: List<Player>
    ) : super(){
        writeList(players)
    }
}
```
And we can send this packet as before


# Implementation

Step 1. Add the JitPack repository to your build file

```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency

```gradle
dependencies {
    implementation 'com.github.QwertyMo:Packager:{version}'
}
```
