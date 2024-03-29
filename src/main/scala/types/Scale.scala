package types

import scala.math.abs

case class Scale(degrees: String, rootMidiNote: Midi) {

  private val scaleDegrees = degrees.split(Array(' ', ',', ';')).map(_.trim.toInt).toSeq

  def toMidiValue(step: Int): MidiValue = {
    val degrees = scaleDegrees
    val pitchesPerOctave = 12
    val numOfSteps = degrees.length + 1
    val octaves = step / numOfSteps
    val normStep = step % numOfSteps
    val scale = 0 +: degrees :+ 0
    val transformed =
      if (normStep >= 0) rootMidiNote.value + (octaves * pitchesPerOctave) + scale(normStep)
      else rootMidiNote.value + ((octaves - 1) * pitchesPerOctave) + scale.reverse(abs(normStep))
    transformed
  }
}

//object Scale {
//  def apply(degrees: Seq[Int], rootMidiNote: Int, pitchesPerOctave: Int = 12) =
//    Scale(degrees.map(Positive[Int](_)), MidiValue(rootMidiNote), Positive(pitchesPerOctave))
//}
