package actors

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors

object Logger {

  sealed trait Command
  final case class Info(message: String) extends Command
  final case class Error(error: Throwable) extends Command

  def apply(): Behavior[Command] =
    Behaviors.receive { (context, message) =>
      message match {
        case Info(message) => context.log.info(message)
        case Error(error)  => context.log.error(error.toString)
      }
      Behaviors.same
    }

}
