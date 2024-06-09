package core

import core.Interpreter.{interpretIterator, parseAndEval}
import music._
import org.scalatest.TryValues.convertTryToSuccessOrFailure
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import types.MidiValue
import eu.timepit.refined.auto._

class TestInterpreter extends AnyFlatSpec with Matchers{

  "Eval" should "interpret and add two numbers returning success" in {
    val sum = parseAndEval[Int]("2 + 2")
    sum.success.value shouldBe 4
  }

  "Eval" should "return failure when parsing invalid code" in {
    val sum = parseAndEval[Int]("a + 2")
    sum.isFailure shouldBe true
  }

  "interpretIterator" should "return primitive types" in {
    val tryIter = interpretIterator[Pitch]("seq(Note(1), p)")("testIterator")
    val iter = tryIter.success.value
    iter.take(2).toSeq shouldEqual Seq(Note(1), p)
  }

}
