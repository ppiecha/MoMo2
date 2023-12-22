package core

import scala.io._
import scala.util._

object FileIO {

  private def source(fileName: String): BufferedSource =
    Source.fromFile(fileName)

  def contentAsString(fileName: String): Try[String] =
    Using(source(fileName))(src => src.mkString)

}
