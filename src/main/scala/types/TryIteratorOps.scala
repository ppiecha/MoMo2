package types

import util._

object TryIteratorOps {

  def fromSeq[A](seq: Seq[A]): TryIterator[A] = Try(seq.iterator)

  implicit class EventsOps[A](events: TryIterator[A]) {
    def ++(newEvents: TryIterator[A]): TryIterator[A] = merge(Seq(events, newEvents))
    def ++(newEvents: Seq[A]): TryIterator[A] =
      merge(Seq(events, TryIteratorOps.fromSeq(newEvents)))
  }

  def merge[A](events: Seq[TryIterator[A]]): TryIterator[A] =
    events.foldLeft[TryIterator[A]](Try(Iterator.empty[A])) {
      case (Failure(exception), _)           => Failure(exception)
      case (_, Failure(exception))           => Failure(exception)
      case (Success(accIter), Success(iter)) => Success(accIter ++ iter)
    }

}
