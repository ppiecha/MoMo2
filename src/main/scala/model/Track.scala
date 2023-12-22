package model

import types._

case class Track(
    name: String,
    channel: Int,
    bank: Int,
    instrument: Int,
    versions: List[TrackVersion],
    active: Option[Boolean]
) extends Channel {
  require((0 until 256) contains instrument, s"Instrument midi value $instrument not in (0, 255) range")

//  def getNoteEvents(implicit opt: PlayOptions, channel: Int = 0): TryIterator[Event] = {
//    val trackEvents = versions.filter(_.active.getOrElse(true)).map(_.getNoteEvents(opt, this.channel))
//    val programEvent = ProgramEvent(ProgramMessage(this.channel, MidiValue(bank), MidiValue(instrument)), 0)
//    val programEvents = Events.fromSeqOfEvents(Seq(programEvent))
//    //println(programEvent)
//    Events.mergeEvents(programEvents +: trackEvents)
//  }

}
