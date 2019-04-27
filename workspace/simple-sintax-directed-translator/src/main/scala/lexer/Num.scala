package lexer

case class Num(value: Int) extends Token(Tag.Num)