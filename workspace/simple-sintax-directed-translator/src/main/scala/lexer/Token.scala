package lexer

case class Token(tag: Int) {
  override def toString: String = tag.toChar.toString
}