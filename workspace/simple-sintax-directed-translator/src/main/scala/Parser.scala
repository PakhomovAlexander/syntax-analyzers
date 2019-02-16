import java.io.{InputStream, OutputStream}

/**
  * Parse infix statements to postfix
  * @param in
  * @param out
  */
class Parser(in: InputStream = System.in, out: OutputStream = System.out) {
  val Plus = '+'
  val Minus = '-'

  var lookahead: Int = in.read()

  def expr() {
    term(); rest()
  }

  def rest() {
    lookahead match {
      case Plus => next(Plus); term(); out.write(Plus); rest()
      case Minus => next(Minus); term(); out.write(Minus); rest()
      case _ => //nop
    }
  }

  def term() {
    if (Character.isDigit(lookahead)) {
      out.write(lookahead)
      next(lookahead)
    } else throw new Error(s"syntax error near with $lookahead")
  }

  def next(t: Int) {
    if (lookahead == t)
      lookahead = in.read()
    else throw new Error(s"syntax error near with $lookahead")
  }
}