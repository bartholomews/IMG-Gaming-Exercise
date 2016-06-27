/**
  *
  * @author federico.bartolomei
  */
object Parser {

  def parseEvent(bin: Int): Event = parseEvent(bin.toBinaryString)

  def parseEvent(bin: String) = new Event(
    parseMatchTime(bin),
    parseTeam1Points(bin),
    parseTeam2Points(bin),
    parseWhoScored(bin),
    parsePointsScored(bin)
  )

  def parsePointsScored(bin: String): Int = bin.takeRight(2).base(2)
  def parseWhoScored(bin: String): Int = bin.dropRight(2).takeRight(1).base(2)
  def parseTeam2Points(bin: String): Int = bin.dropRight(3).takeRight(8).base(2)
  def parseTeam1Points(bin: String): Int = bin.dropRight(11).takeRight(8).base(2)
  def parseMatchTime(bin: String): Int = bin.dropRight(19).takeRight(12).base(2)

  /**
    * @see http://stackoverflow.com/a/14642744
    */
  implicit class IntToBase(val digits: String) extends AnyVal {
    def base(b: Int) = Integer.parseInt(digits, b)
    def b = base(2)
    def o = base(8)
    def x = base(16)
  }

}
