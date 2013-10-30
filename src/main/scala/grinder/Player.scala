package grinder

//position number 1 is SB position number 10 is Button. It is possible, that a position is empty
class Player(name: String, var stackSize: scala.math.BigDecimal, position: Int) {
 
  //var handRange: BeliefVector

  //returns the range that the player represents by his actions
  def getRange(pot: Pot): String = {
    "AA"
  }

  // Returns the minimum amount we have to bet to get the player to lay down his hand
  def getBluffAmount(pot: Pot): scala.math.BigDecimal = {
    123.56
  }

  // Returns the maximum amount we have to bet so the player will call us
  def getCallAmount(pot: Pot): scala.math.BigDecimal = {
    33.5
  }

  def decreaseStack(cash: scala.math.BigDecimal) = {
    this.stackSize = this.stackSize - cash
  }
  //Handle Pokertracker 4 database queries
  //Define Playing Style
}
