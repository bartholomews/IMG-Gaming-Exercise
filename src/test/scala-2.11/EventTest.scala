import java.time.LocalTime

import org.scalatest.{FlatSpec, MustMatchers}

/**
  * @author federico.bartolomei
  */
class EventTest extends FlatSpec with MustMatchers {

  // after 15 seconds, Team 1 scores 2 points
  val hex1 = 0x781002

  "Event" should "correctly decode an hex received" in {
    val event1 = Parser.parseEvent(hex1)
    event1.matchTime mustBe LocalTime.ofSecondOfDay(15)
    event1.team1 mustBe 2
    event1.team2 mustBe 0
    event1.teamScored mustBe Team1
    event1.pointsScored mustBe 2
  }

  "An Event" should "get the proper match time" in {
      val event = new Event(426, 0, 2, 1, 0)
      event.matchTime mustBe LocalTime.of(0, 7, 6)
    }

  it should "throw an exception if the team scored field is neither 0 or 1" in {
    val thrown = intercept[Exception] {
      // `who score` has value 2, it's not valid
      new Event(426, 0, 2, 2, 2)
    }
    assert(thrown.getMessage === "2 does not extend Team")
  }

}
