package actors

import akka.actor.typed.{ActorRef, Behavior}
import akka.actor.typed.scaladsl.Behaviors
import akka.util.Timeout

import scala.concurrent.duration.SECONDS
import scala.util.{Failure, Success}

object Console {

  sealed trait Command
  final case class Start(args: List[String]) extends Command
  final case class LogError(message: String) extends Command
  final case class LogMessage(message: String) extends Command
  private final case class WorkerDoneAdapter(response: Validator.Response) extends Command

  def apply(): Behavior[Command] =
    Behaviors.setup { context =>
      implicit val timeout: Timeout = Timeout(3, SECONDS)
      val validator: ActorRef[Validator.Command] = context.spawn(Validator(), "Validator")
      var state: Validator.State = Validator.State(None, None, None, None)
      Behaviors.receiveMessage {
        case Start(args) =>
          context.ask(validator, Validator.Validate(args, state, _)) {
            case Failure(exception) => ???
            case Success(response) =>
              response match {
                case Validator.State(yamlFile, soundFile, composition, sequence) => ???
                case Validator.Error(exception)                                  => ???
              }
          }
          Behaviors.same
      }
    }

}
