package lexer

import scala.collection.mutable

class Lexer {
  var line = 1

  private var peek = ' '
  private val words = mutable.HashMap.empty[String, Word]

  def reserve(t: Word): Option[Word] = words.put(t.lexeme, t)

  def scan(): Token = {
    skipWhiteSpaces()

    if (Character.isDigit(peek)) {
      var v = 0
      do {
        v = 10 * v + Character.digit(peek, 10)
        peek = System.in.read().toChar
      } while (Character.isDigit(peek))
      return Num(v)
    }

    if (Character.isLetter(peek)) {
      val b = new StringBuffer()
      do {
        b.append(peek)
        peek = System.in.read().toChar
      } while (Character.isLetterOrDigit(peek))

      val s = b.toString
      val w = words.get(s)
      if (w.isDefined) return w.get

      val newWord = Word(Tag.Id, s)
      words.put(s, newWord)
      return newWord
    }

    val t = Token(peek)
    peek = ' '
    t
  }


  private def skipWhiteSpaces(): Unit = {
    var next = true
    while (next && (peek = System.in.read().toChar) != -1) {
      peek match {
        case ' ' && '\t' => _
        case '\n' => line = line + 1
        case _ => next = false
      }
    }
  }
}


object Lexer {
  def apply: Lexer = {
    val lexer = new Lexer()
    lexer.reserve(Word(Tag.True, "true"))
    lexer.reserve(Word(Tag.False, "false"))
    lexer
  }
}


