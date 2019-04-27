import parser.Parser

object Postfix extends App {
  val parse = new Parser
  parse.expr()

  println()
}