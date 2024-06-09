package music

import types.TryIteratorOps.merge
import types._

import javax.sound.midi.MidiEvent

case class Track(
    name: String,
    channel: Channel,
    bank: Midi,
    instrument: Midi,
    versions: List[TrackVersion],
    active: Option[Boolean]
) {

  val activeVersions: List[TrackVersion] = versions.filter(_.active.getOrElse(true))

  def events(ppq: Ppq): TryIterator[Event] = merge(activeVersions.map(_.events(ppq, channel)))

  def midiEvents(ppq: Ppq): TryIterator[MidiEvent] = merge(activeVersions.map(_.midiEvents(ppq, channel)))

//  def getNoteEvents(implicit opt: PlayOptions, channel: Int = 0): TryIterator[Event] = {
//    val trackEvents = versions.filter(_.active.getOrElse(true)).map(_.getNoteEvents(opt, this.channel))
//    val programEvent = ProgramEvent(ProgramMessage(this.channel, MidiValue(bank), MidiValue(instrument)), 0)
//    val programEvents = Events.fromSeqOfEvents(Seq(programEvent))
//    //println(programEvent)
//    Events.mergeEvents(programEvents +: trackEvents)
//  }

}
