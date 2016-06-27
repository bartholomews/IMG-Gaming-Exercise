
/**
  * @author federico.bartolomei
  */
class Match {

  var events: List[Event] = List()

  def addEvents(list: List[Int]) = {
    list.foreach(e => addEvent(Parser.parseEvent(e.toBinaryString)))
  }

  def addEvent(event: Event) = {
    if(event.pointsScored > 0) {
      events = insertEvent(event, events)
    }
  }

  /**
    * Insert an Event at the right place into a list of Events,
    * that is with ascending Match Time (mt) values.
    * A duplicate event (with same mt value) is not added to the list.
    *
    * @param event the Event to be added to the list
    * @param list a list of Events
    * @return a new List with the added Event
    */
  def insertEvent(event: Event, list: List[Event]): List[Event] = list match {
    case Nil => List(event)
    case h :: t => {
      // event is the latest: is added at the head
      if(event.mt > h.mt) event :: list
      // event is not the latest: recursively insert
      else if(event.mt < h.mt) h :: insertEvent(event, t)
      // another event with same match time is already in the list
      else list
    }
  }

  def getLastEvent = events.head

  def getEvents(n: Int): List[Event] = {
    def loop(list: List[Event], count: Int): List[Event] = list match {
      case Nil => List()
      case h :: t => h :: loop(t, count - 1)
    }
    if(n <= 0) List()
    else loop(events, n)
  }

  // all events: head of the list is the latest event
  def getAllEvents = events

  def prettyPrint() = {
    if (events.isEmpty) { println("NO EVENTS") }
    else events.foreach(e => e.prettyPrint())
  }

}
