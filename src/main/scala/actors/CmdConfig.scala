package actors

import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import scopt.{OEffect, OParser}

import java.lang

object CmdConfig {

  sealed trait Command
  final case class Args(args: List[String], replyTo: ActorRef[Response]) extends Command

  sealed trait Response
  final case class Config(cmdConfig: core.CmdConfig) extends Response
  final case class Error(error: Throwable) extends Response

  def apply(): Behavior[Command] =
    Behaviors.receive { (context, message) =>
      message match {
        case Args(args, replyTo) =>
          def log(msg: String): Unit = replyTo ! Error(new scala.Error(msg))
          context.log.info(args.toString)
          OParser.runParser(core.CmdOptions.parser, args, core.CmdConfig()) match {
            case (Some(config), _) =>
              replyTo ! Config(config)
              Behaviors.same
            case (_, effects) =>
              effects.foreach {
                case OEffect.DisplayToOut(msg)  => log(msg)
                case OEffect.DisplayToErr(msg)  => log(msg)
                case OEffect.ReportError(msg)   => log(msg)
                case OEffect.ReportWarning(msg) => log(msg)
                case OEffect.Terminate(_)       => ()
              }
              //replyTo ! Error(new scala.Error("Error when parsing args"))
              Behaviors.same
          }
      }
    }

}
