import java.io.{ByteArrayInputStream, ByteArrayOutputStream}

import org.scalatest.FunSuite

class ParserTest extends FunSuite {
  test("Parser.parse") {
    val input = new ByteArrayInputStream("5+6-9-1".getBytes)
    val output = new ByteArrayOutputStream()

    val parser = new Parser(input, output)
    parser.expr()

    output.toByteArray sameElements "56+9-1-".getBytes
  }

  test("Parser.parse failover") {
    val input = new ByteArrayInputStream("5+asd6".getBytes)
    val output = new ByteArrayOutputStream()


    val err = intercept[Error] {
      val parser = new Parser(input, output)
      parser.expr()
    }

    err.getMessage contains "syntax error"
  }
}
