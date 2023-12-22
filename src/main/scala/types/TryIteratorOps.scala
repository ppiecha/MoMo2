package types

import util._

/**
  * Events as iterators
  */
object TryIteratorOps {

  def fromSeqOfEvents(events: Seq[Event]): TryIterator[Event] = Try(events.iterator)

  implicit class EventsOps(events: TryIterator[Event]) {
    def ++(newEvents: TryIterator[Event]): TryIterator[Event] = mergeEvents(Seq(events, newEvents))
    def ++(newEvents: Seq[Event]): TryIterator[Event] =
      mergeEvents(Seq(events, TryIteratorOps.fromSeqOfEvents(newEvents)))
  }

  def mergeEvents(events: Seq[TryIterator[Event]]): TryIterator[Event] =
    events.foldLeft[TryIterator[Event]](Try(Iterator.empty[NoteEvent])) {
      case (Failure(exception), _)           => Failure(exception)
      case (_, Failure(exception))           => Failure(exception)
      case (Success(accIter), Success(iter)) => Success(accIter ++ iter)
    }

}
