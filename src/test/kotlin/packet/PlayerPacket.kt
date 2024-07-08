package packet

import model.Player
import ru.kettuproj.packager.Packet
import ru.kettuproj.packager.annotation.Protocol

@Protocol(3)
class PlayerPacket : Packet {
    var player: Player? = null
    var s = ""
    constructor(buf: ByteArray) : super(buf){
        player = readPackable()
        s = readString()
    }

    constructor(
        player: Player?,
        s: String
    ) : super(){
        writePackable(player)
        writeString(s)
    }
}