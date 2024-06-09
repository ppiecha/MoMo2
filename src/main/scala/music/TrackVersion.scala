package music

import types.{Channel, Event, Ppq, Scale, TryIterator}

import javax.sound.midi.MidiEvent

case class TrackVersion(
    name: String,
    scale: Option[Scale],
    scaleNote: Option[String],
    midiNote: Option[String],
    timing: String,
    startAt: Option[Double],
    duration: String,
    velocity: Option[String] = None,
    active: Option[Boolean] = Option(true)
) {
  require(startAt.getOrElse(0.0) >= 0.0, s"$name. Start $startAt should not be negative")
  require(!name.isBlank, s"$name. Name can't be empty")
  require(!timing.isBlank, s"$name. Timing can't be empty")
  require(!duration.isBlank, s"$name. Duration can't be empty")

  def events(ppq: Ppq, channel: Channel): TryIterator[Event] = ???
  def midiEvents(ppq: Ppq, channel: Channel): TryIterator[MidiEvent] = ???

}
