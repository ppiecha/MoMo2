package model

import actors._
import akka.actor.typed.ActorRef
import types._

final case class ConfigState(
    bpm: Option[Bpm],
    ppq: Option[Ppq],
    compositionLengthLimit: Option[CompositionLengthLimit],
    soundFontFile: Option[String],
    compositionFile: Option[String]
)

final case class Adapters(
    cmdConfigAdapter: ActorRef[CmdConfig.Response]
)
final case class Actors(
    loggerActor: ActorRef[Logger.Command],
    cmdConfigActor: ActorRef[CmdConfig.Command]
)

sealed trait AppState {
  def configState: ConfigState
}

final case class InitState(configState: ConfigState) extends AppState
final case class StateWithActors(configState: ConfigState, actors: Actors) extends AppState
final case class StateWithActorsDetails(configState: ConfigState, actors: Actors, adapters: Adapters) extends AppState
