package packet

import model.Team
import ru.kettuproj.packager.Packet
import ru.kettuproj.packager.annotation.Protocol

@Protocol(6)
class TeamPacket : Packet {
    var team: List<Team> = emptyList()
    constructor(buf: ByteArray) : super(buf){
        team = readPackableList()
    }

    constructor(
        team: List<Team>
    ) : super(){
        this.team = team
        writeList(team)
    }
}