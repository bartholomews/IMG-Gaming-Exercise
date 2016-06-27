import org.scalatest.{FlatSpec, MustMatchers}

/**
  * @author federico.bartolomei
  */
class ParserTest extends FlatSpec with MustMatchers {

  // after 15 seconds, Team 1 scores 2 points
  val binHex = 0x781002.toBinaryString

  "Parser" should "correctly parse an hex received" in {
    Parser.parseMatchTime(binHex) mustBe 15
    Parser.parseTeam1Points(binHex) mustBe 2
    Parser.parseTeam2Points(binHex) mustBe 0
    Parser.parseWhoScored(binHex) mustBe 0
    Parser.parsePointsScored(binHex) mustBe 2
  }


}
