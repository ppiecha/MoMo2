package model

import types.TryIteratorOps.merge
import types._

import javax.sound.midi.{MidiEvent, Sequence}

case class CmpConfig()

//case class Composition(
//    name: Option[String],
//    BPM: Int,
//    lengthLimitInSeconds: Option[Double],
//    midiFilePath: Option[String],
//    soundFontPath: Option[String],
//    tracks: List[Track],
//    startAt: Option[Double], // todo
//    author: Option[String],
//    description: Option[String],
//    comment: Option[String],
//    PPQ: Option[Int]
//) {
//
//  private val resolution: Int = if (PPQ.isEmpty) DEFAULT_PPQ else PPQ.get
//
//  private val lengthLimit: Double = if (lengthLimitInSeconds.isEmpty) DEFAULT_LENGTH_LIMIT else lengthLimitInSeconds.get
//
//  val offset: Double = if (startAt.isEmpty) 0.0 else startAt.get
//
//  val activeTracks: List[Track] = tracks.filter(_.active.getOrElse(true))
//
//  val tracksToCompute: Map[TrackName, TrackComputation] =
//    activeTracks.map(track => (track.name, () => track.midiEvents(resolution))).toMap
//
//  def sequence(computedTracks: Map[TrackName, TrackIterator]): Sequence = {
//    val seq = new Sequence(Sequence.PPQ, resolution)
//    computedTracks
//      .map {
//        case (_, tryIterator) =>
//          val track = seq.createTrack()
//          tryIterator.map(iter => iter.foreach(track.add))
//      }
//    seq
//  }
//
//  lazy val tracksWithEvents: List[TryIterator[Event]] = activeTracks.map(_.events(resolution))
//
//  lazy val tracksWithMidiEvents: List[TryIterator[MidiEvent]] = activeTracks.map(_.midiEvents(resolution))
//
//  lazy val events: TryIterator[Event] = merge(tracksWithEvents)
//
//  lazy val midiEvents: TryIterator[MidiEvent] = merge(tracksWithMidiEvents)

//  def getNoteEvents(implicit opt: PlayOptions, channel: Int = 0): Events[Event] = {
//    val compositionEvents = tracks.filter(_.active.getOrElse(true)).map(t => t.getNoteEvents)
//    Events.mergeEvents(compositionEvents)
//  }

//def playOptions(): PlayOptions = PlayOptions(soundFontPath.map(new File(_)), resolution, BPM, lengthLimit)

//}
