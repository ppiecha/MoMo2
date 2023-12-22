package core

import cats.syntax.either._
import io.circe._
import io.circe.generic.auto._
import io.circe.yaml.parser
import model.Composition

import scala.util.Try

object Yaml {
  def toComposition(yamlString: String): Try[Composition] = {
    val json = parser.parse(yamlString)
    Try {
      json
        .leftMap(err => err: Error)
        .flatMap(_.as[Composition])
        .valueOr(throw _)
    }
  }
}
