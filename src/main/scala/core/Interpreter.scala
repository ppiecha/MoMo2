package core

import types._

import scala.util.{Failure, Success, Try}

object Interpreter {

  private val toolbox = locally {
    import scala.tools.reflect.ToolBox
    reflect.runtime.currentMirror.mkToolBox()
  }

  private def addImports(code: String) =
    Seq(
      "import core.Pattern._",
      "import music._",
      "import eu.timepit.refined.auto._",
      code
    ).mkString("\n")

  private def parse(code: String): Try[InterpreterTree] = Try(toolbox.parse(addImports(code)))

  private def eval(tree: InterpreterTree) = Try(toolbox.eval(tree))

  def parseAndEval[A](code: String): Try[A] = {
    for {
      parsed <- parse(code)
      value <- eval(parsed)
    } yield value.asInstanceOf[A]
  }

  def interpretIterator[A](code: String)(implicit versionName: String): TryIterator[A] = {
    Interpreter.parseAndEval[Iterator[A]](code) match {
      case Failure(exception) =>
        Failure(
          new RuntimeException(s"Version ===== $versionName ===== ${exception.getMessage}")
        )
      case Success(iter) => Success(iter)
    }
  }

}
