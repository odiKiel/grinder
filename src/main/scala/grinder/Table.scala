package grinder

object PlayerAction extends Enumeration {
  type PlayerAction = Value
  val Fold, Check, Bet, Call, Raise = Value
}

object TableStatus extends Enumeration {
  type TableStatus = Value
  val NewRound, Flop, Turn, River = Value
}

import TableStatus._


import PlayerAction._



//it is not possible to remove add player during a round
//after each round all players will be added again
class Table(id: Int, playersString: String, heroName: String) {

  //Position
  // linkedList of Players first Player is sb last player is dealer
  //je weiter vom dealer entfernt, desto geringer ist die advantage




  def isNewRound(d: TableStatus) = !(d == Flop || d == Turn || d == River)

  var hero: Hero = null
  var currentPot: Pot = null
  var currentStatus: TableStatus = TableStatus.NewRound

  //playersString: Name/%&^/Stack/%NP&/

  var communityCards: scala.collection.mutable.LinkedList[Int] = null
  val mood = 1//defines the overall mood of the players aggressive, loose, tight grinder should play the opposite table style

  def newRound(_players: String, heroHand: String) = {
    currentStatus = TableStatus.NewRound
    communityCards = null
    currentPot = new Pot(0.0)
    val playerStrings= _players.split("/%NP&/")
    playerStrings.foreach((ps: String) => {
      val p = ps.split("/%&^/")
      val cp = new Player(p(0), p(1).toDouble)
      if(p(0) == heroName) {
        hero.player = cp
        hero.hand = heroHand
      }
      currentPot.addPlayer(cp)
    })
  }

  //Card String example "As", "Ah", "2h"
  def addCommunityCards(cards: Array[String]) = {
    //currentStatus += 1
    if(cards.length == 3){currentStatus = TableStatus.Flop}
    else if(currentStatus == TableStatus.Flop){currentStatus = TableStatus.Turn}
    else{currentStatus = TableStatus.River}
    cards.map(communityCards :+ Eval.stringToHand(_))
  }

  def addPlayerAction(playerName: String, playerAction: PlayerAction, cash: Double) = {
    currentPot.addAction(playerName, playerAction, cash, currentStatus)
  }

  def requestHeroAction() = {
    hero.requestAction(this)
  }

}
