import model.ConfigState
import akka.actor.typed.ActorSystem
import actors.Console
import com.typesafe.config.ConfigFactory
import pureconfig.{CamelCase, ConfigFieldMapping, ConfigSource}
import pureconfig.generic.ProductHint
import pureconfig.generic.auto._
import eu.timepit.refined.pureconfig._
import scala.annotation.tailrec

object Main extends App {

  implicit def productHint[A]: ProductHint[A] =
    ProductHint(ConfigFieldMapping(CamelCase, CamelCase))

  //implicit val appStateReader: ConfigReader[AppState] = ConfigReader[CommonFiles].map(cf => AppState(cf, None))

  @tailrec
  private def mainLoop(): Unit = {
    val args = scala.io.StdIn.readLine().split(" ").map(_.trim)
    if (!args.headOption.map(_.toLowerCase()).contains("q")) {
      console ! Console.Args(args.toList)
      mainLoop()
    }
  }

  //val cfg = ConfigFactory.parseFile(new java.io.File("data/met.conf"))
  //val config = ConfigSource.fromConfig(cfg).loadOrThrow[AppState]
  private val baseConfig = ConfigFactory.load()
  val appState = ConfigSource.fromConfig(baseConfig).loadOrThrow[ConfigState]
  //val config = ConfigFactory.parseFile(new java.io.File("examples\\metronome.conf")).withFallback(baseConfig)
  println(appState)
  val console: ActorSystem[Console.Command] = ActorSystem(Console(appState), "Console")
  console ! Console.Args(args.toList)
  mainLoop()
  console.terminate()

}
