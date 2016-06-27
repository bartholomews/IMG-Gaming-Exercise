import java.time.LocalTime

import org.scalatest.{FlatSpec, MustMatchers}

/**
  * @author federico.bartolomei
  */
class MatchTest extends FlatSpec with MustMatchers {
  val test1 = new Match

  val sample1: List[Int] = List(
    0x801002,
    0xf81016,
    0x1d8102f,
    0x248202a,
    0x2e0203e,
    0x348204e,
    0x3b8384b,
    0x468385e,
    0x5304059,
    0x640406e,
    0x6d8506a,
    0x760606a,
    0x838607e,
    0x8e8707a,
    0x930708e,
    0x9f0709e,
    0xad070a5,
    0xb7880a2,
    0xbf880b6,
    0xc9080c6,
    0xd2090c2,
    0xdd090d6,
    0xed0a8d3,
    0xf98a8e6,
    0x10a8b8e2,
    0x1178b8ed,
    0x1228c8ea,
    0x12b0d8ea
  )

  val sample2: List[Int] = List(
    0x781002,
    0xe01016,
    0x1081014,
    0x1e0102f,
    0x258202a,
    0x308203e,
    0x388204e,
    0x388204e,
    0x3d0384b,
    0x478385e,
    0x618406e,
    0x5404059,
    0x6b8506a,
    0x750706c,
    0x7d8507e,
    0x938608e,
    0x8b8607a,
    0xa10609e,
    0xb8870a2,
    0xc4870b6,
    0xcc070c6,
    0x2ee74753,
    0xd5080c2,
    0xdf080d6,
    0xe4098d3,
    0xec098f6,
    0xfc8a8e2,
    0x10a8a8ed,
    0x1180b8ea,
    0x1218c8ea
  )

  "Match class" should "get the last event received" in {
    test1.addEvent(new Event(10, 2, 0, 0, 2))
    test1.getLastEvent.matchTime mustBe LocalTime.ofSecondOfDay(10)
  }

  it should "get `n` events" in {
    test1.getEvents(0).size mustBe 0
    test1.getEvents(1).size mustBe 1
    // list has only one element
    test1.getEvents(2).size mustBe 1
  }

  it should "sort two received events by match time" in {
    // a later event is received first
    test1.addEvent(new Event(20, 6, 0, 0, 2))
    test1.addEvent(new Event(5, 4, 0, 0, 2))
    test1.getLastEvent.matchTime mustBe LocalTime.ofSecondOfDay(20)
  }

  it should "not add a duplicate (with same match time) Event" in {
    test1.getAllEvents.size mustBe 3
    // event already added in previous test
    test1.addEvent(new Event(5, 4, 0, 0, 2))
    test1.getAllEvents.size mustBe 3
  }

  it should "not add an Event with 0 points scored" in {
    test1.getAllEvents.size mustBe 3
    test1.addEvent(new Event(25, 6, 0, 0, 0))
    test1.getAllEvents.size mustBe 3
  }

  "The Match time of a list of Events" should "be increasing over time" in {
    val newMatch = new Match
    newMatch.addEvents(sample2)
    val original = newMatch.getAllEvents
    val sorted = original.sortWith((a, b) => a.mt > b.mt)
    original.zip(sorted).forall { case (x, y) => x == y } mustBe true
  }

  it should "ads" in {

  }

}
