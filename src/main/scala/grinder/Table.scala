package grinder

object PlayerAction extends Enumeration {
  type PlayerAction = Value
  val Fold, Check, Bet, Call, Raise = Value
}

import PlayerAction._



//it is not possible to remove add player during a round
//after each round all players will be added again
class Table(id: Int, playersString: String, heroName: String) {
  val heroId = "##GrinderHero##"

  //Position
  // linkedList of Players first Player is sb last player is dealer
  //je weiter vom dealer entfernt, desto geringer ist die advantage

  object TableStatus extends Enumeration {
    type TableStatus = Value
    val NewRound, Flop, Turn, River = Value
  }

  import TableStatus._



  def isNewRound(d: TableStatus) = !(d == Flop || d == Turn || d == River)

  var hero: Hero = null
  var currentPot: Pot = null
  var currentStatus: TableStatus = TableStatus.NewRound
  var players: Map[String, Player] = Map[String, Player]()

  //playersString: Name/%&^/Stack/%NP&/
  def apply = {
    hero = new Hero(players(heroName))
    playersString.split("%NP&")
  }

  val communityCards: scala.collection.mutable.LinkedList[Int] = null
  val mood = 1//defines the overall mood of the players aggressive, loose, tight grinder should play the opposite table style

  def newRound(_players: String) = {
    //players = _players probably a playerString see apply
    currentStatus = TableStatus.NewRound
    //currentPot = new Pot(players.toList, 0.0)
  }

  //Card String example "As", "Ah", "2h"
  def addCommunityCards(cards: Array[String]) = {
    //currentStatus += 1
    cards.map(communityCards :+ Eval.stringToHand(_))
  }

  def addPlayerAction(playerName: String, playerAction: PlayerAction, cash: scala.math.BigDecimal) = {
    //players.addAction(playerAction, cash)
    //currentPot.addAction()
  }

}
