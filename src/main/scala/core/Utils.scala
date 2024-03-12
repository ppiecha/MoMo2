package core

import types.{Bpm, Ppq}

object Utils {

  def durToTick(dur: Double, ppq: Ppq): Long = dur match {
    case x if x == 0.0 => 0
    case _             => ((ppq.value.toDouble * 4) / dur).toLong
  }

  def tickPerSecond(ppq: Ppq, bpm: Bpm): Double = ppq.value * (bpm.value / 60.0)

  def tickToSecond(tick: Long, ppq: Ppq, bpm: Bpm): Double = tick / tickPerSecond(ppq, bpm)

}
