package actors

import akka.actor.typed.{ActorRef, Behavior}
import core.CmdConfig
//import model.Composition

import java.io.File
import javax.sound.midi.Sequence
import scala.util._

object Validator {

  sealed trait Command
  final case class Validate(args: List[String], state: State, replyTo: ActorRef[Validator.Response]) extends Command

  sealed trait Response
  final case class State(
      yamlFile: Option[File],
      soundFile: Option[File],
      //composition: Option[Composition],
      sequence: Option[Sequence]
  ) extends Response
  final case class Error(exception: Throwable) extends Response

//  def apply(): Behavior[Command] = {
//    ???
//  }

  //def validateArgs(args: List[String]): Try[CmdConfig] = ???

  //def validateYaml(config: CmdConfig): Try[Composition] = ???

  //def validateComposition(composition: Composition): Try[State] = ???

}
