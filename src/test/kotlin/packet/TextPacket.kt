package packet

import ru.kettuproj.packager.Packet
import ru.kettuproj.packager.annotation.Protocol

@Protocol(1)
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
