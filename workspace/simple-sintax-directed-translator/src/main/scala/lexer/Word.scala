package lexer

case class Word(t: Int, lexeme: String) extends Token(t)