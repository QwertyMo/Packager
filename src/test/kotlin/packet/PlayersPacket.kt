package packet

import model.Player
import ru.kettuproj.packager.Packet
import ru.kettuproj.packager.annotation.Protocol

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