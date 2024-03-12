package actors

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import model._

import javax.sound.midi

object Console {

  sealed trait Command
  final case class Args(args: List[String]) extends Command
  private final case class CmdConfigWrapper(cmdConfig: CmdConfig.Response) extends Command
  final case class Composition(cmpConfig: CmpConfig) extends Command
  final case class Sequence(sequence: midi.Sequence) extends Command
  final case class Error(error: Throwable) extends Command

  def apply(configState: ConfigState): Behavior[Command] =
    Behaviors.setup { context =>
      val adapters = Adapters(
        cmdConfigAdapter = context.messageAdapter[CmdConfig.Response] { resp =>
          CmdConfigWrapper(resp)
        }
      )
      val actors = Actors(
        loggerActor = context.spawn(Logger(), "loggerActor"),
        cmdConfigActor = context.spawn(CmdConfig(), "cmdConfigActor")
      )
      ready(StateWithActorsDetails(configState, actors, adapters))
    }

  private def ready(appState: StateWithActorsDetails): Behavior[Command] =
    Behaviors.receiveMessage[Command] {
      case Args(args) =>
        appState.actors.cmdConfigActor ! CmdConfig.Args(args, appState.adapters.cmdConfigAdapter)
        busy(appState)
      case Error(error) =>
        appState.actors.loggerActor ! Logger.Error(error)
        Behaviors.same
    }

  private def busy(appState: StateWithActorsDetails): Behavior[Command] =
    Behaviors.receiveMessage {
      case Args(_) =>
        appState.actors.loggerActor ! Logger.Info("Busy...")
        Behaviors.same
      case CmdConfigWrapper(resp) =>
        resp match {
          case CmdConfig.Config(cmdConfig) =>
            appState.actors.loggerActor ! Logger.Info(cmdConfig.toString)
            ready(appState)
          case CmdConfig.Error(error) =>
            appState.actors.loggerActor ! Logger.Error(error)
            ready(appState)
        }
    }

}
