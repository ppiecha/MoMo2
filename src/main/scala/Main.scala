import akka.actor.typed.ActorSystem
import actors.Console

object Main extends App {

  val console: ActorSystem[Console.Command] = ActorSystem(Console(), "Console")

  console ! Console.Start(args.toList)
}
