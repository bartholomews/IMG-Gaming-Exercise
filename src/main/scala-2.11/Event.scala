import java.time.LocalTime

/**
  * @author federico.bartolomei
  */
class Event(val mt: Long, val team1: Int, val team2: Int, ts: Int, val pointsScored: Int) {

  val matchTime = LocalTime.ofSecondOfDay(mt)

  val teamScored: Team = ts match {
    case 0 => Team1
    case 1 => Team2
    case x => throw new Exception(x + " does not extend Team")
  }

  def prettyPrint() = {
    println("=" * 8 + "EVENT" + "=" * 8)
    println("TIME: " + matchTime)
    println(teamScored + " SCORED " + pointsScored + " POINTS")
    println(Team1.toString + ": " + team1)
    println(Team2.toString + ": " + team2)
  }

}