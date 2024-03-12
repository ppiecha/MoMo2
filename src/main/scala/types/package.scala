import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.{Interval, Positive}

import javax.sound.midi.MidiEvent
import scala.language.implicitConversions
import scala.util.Try

package object types {

  type Channel = Int Refined Interval.ClosedOpen[0, 16]
  type Bpm = Int Refined Positive
  type Ppq = Int Refined Positive
  type CompositionLengthLimit = Int Refined Positive
  type Midi = Int Refined Interval.ClosedOpen[0, 256]

  type TryIterator[A] = Try[Iterator[A]]
  type InterpreterTree = scala.reflect.runtime.universe.Tree
  type TrackName = String
  type TrackIterator = TryIterator[MidiEvent]
  type TrackComputation = () => TrackIterator

  implicit def intToMidi(int: Int): MidiValue = MidiValue(int)
  implicit def midiToInt(midiValue: MidiValue): Int = midiValue.value

}
