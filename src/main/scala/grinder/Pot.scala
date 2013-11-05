package grinder

class Pot(var potSize: Double){
  val players = scala.collection.mutable.ArrayBuffer[Player]()
  //Calculate ranges for players

  //actionList Seq[(Player, Action)] action: Check, Bet, Raise, Fold

  //Remove a player from the pot

  import PlayerAction._
  import TableStatus._

  //returns the number of players that have to act after players action
  def playersToAct(player: Player): Int = {
    players.length - players.indexOf(player) - 1
  }

  def handRanges(except: Player = null): List[Array[Int]] = {
    players.filter(_ != except).map(_.handRange).toList
  }

  def removePlayer(playerName: String) = {
    players.remove(players.indexWhere(_.name == playerName))
  }

  def addPlayer(player: Player) = {
    players.append(player)
  }

  //Adds an amount to the pot and adds the action to the player so he can calculate a new handrange
  def addAction(playerName: String, playerAction: PlayerAction, cash: Double, tableStatus: TableStatus) = {
    if(playerAction == Fold) {removePlayer(playerName)}
    else {
      val playerIndex = players.indexWhere(_.name == playerName)
      val player = players(playerIndex)
      player.addAction(playerAction, cash, this.potSize, players.length - playerIndex - 1, tableStatus)
      this.potSize = this.potSize + cash
    }
  }
}
