package music

import types.Midi

sealed trait MusicItem

sealed trait Pitch extends MusicItem
case class Note(midi: Midi) extends Pitch
case class Chord(notes: Note*) extends Pitch
// Pause
case object p extends Pitch
case class ScalePosition() extends Pitch

case class Duration()
