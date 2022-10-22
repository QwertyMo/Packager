package packet

import ru.kettuproj.packager.Packet
import io.netty.buffer.ByteBuf
import ru.kettuproj.packager.annotation.Protocol

@Protocol(1)
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
