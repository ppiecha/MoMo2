package core

import types._

import scala.util.Try

object Interpreter {

  private val toolbox = locally {
    import scala.tools.reflect.ToolBox
    reflect.runtime.currentMirror.mkToolBox()
  }

  private def addImports(code: String) = Seq("import core.Pattern._", code).mkString("\n")

  private def parse(code: String): Try[InterpreterTree] = Try(toolbox.parse(addImports(code)))

  private def eval(tree: InterpreterTree) = Try(toolbox.eval(tree))

  def parseAndEval(code: String): Try[Any] = {
    for {
      parsed <- parse(code)
      value <- eval(parsed)
    } yield value
  }

}
