package grinder

class Pot(players: scala.collection.mutable.ArrayBuffer[Player], var potSize: scala.math.BigDecimal){
  //Calculate ranges for players

  //actionList Seq[(Player, Action)] action: Check, Bet, Raise, Fold

  //Remove a player from the pot

  object Action extends Enumeration {
    type Action = Value
    val Fold, Check, Call, Raise = Value
  }

  import Action._

  var players = collection.mutable.ListBuffer[Double]() 

  def removePlayer(player: Player) = {
  }

  //Adds an amount to the pot
  def addAction(action: Action, cash: scala.math.BigDecimal, player: Player, playersToAct: Int) = {
    player.decreaseStack(cash)
    this.potSize = this.potSize + cash
    //calculate new player range add the end of the betting round?
    //depending on players to act calculate hand strength
  }
}
