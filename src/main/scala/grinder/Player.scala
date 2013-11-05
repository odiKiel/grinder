package grinder

import TableStatus._
import PlayerAction._
//position number 1 is SB position number 10 is Button. It is possible, that a position is empty
class Player(val name: String, var stackSize: Double) {
 
  var handRange: List[Array[Int]] = List(Array(0))

  //returns the range that the player represents by his actions

  var currentHand: Int = 0
  def getNextHand(): Array[Int] = {
    val hand = handRange(currentHand)
    currentHand = (currentHand + 1) % handRange.length
    hand
  }

  def addAction(action: PlayerAction, cash: Double, potSize: Double, playersToAct: Int, tableStatus: TableStatus) = {
    this.stackSize -= cash
    val betSize = cash / potSize
    //depending on the action, tableStatus, playersToAct and betSize calculate range
  }

  // Returns the minimum amount we have to bet to get the player to lay down his hand
  def getBluffAmount(pot: Pot): scala.math.BigDecimal = {
    123.56
  }

  // Returns the maximum amount we have to bet so the player will call us
  def getCallAmount(pot: Pot): scala.math.BigDecimal = {
    33.5
  }

  //Handle Pokertracker 4 database queries
  //Define Playing Style
}
