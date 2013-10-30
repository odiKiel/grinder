package grinder

import PlayerAction._

//should manage the tables can be a singleton
//each player has its own grinder
object Grinder {

  var tables: Map[Int, Table] = Map[Int, Table]()
  var site: String = ""
  var limit: Int = 0

  def startSession = {
    //start ocr with GrinderId, input engine, start PokerTracker, start PokerApp, select tables, arrange tables
  }

  def newTable(tableId: Int, playersString: String, heroName: String) = {
    val table = new Table(tableId, playersString, heroName)
    tables += (tableId -> table)
  }

  def removeTable(tableId: Int) = {
    //tables.delete(tableId)
  }

  def addTableAction(tableId: Int, cards: Array[String]) = {
    tables(tableId).addCommunityCards(cards)
  }

  def newRound(tableId: Int, players: String) = {
    tables(tableId).newRound(players)
  }

  def addPlayerAction(tableId: Int, playerName: String, playerAction: PlayerAction, cash: scala.math.BigDecimal) = {
    tables(tableId).addPlayerAction(playerName, playerAction, cash)
    //every player action changes the range that the player represents 
    //always calculate the likelihood of the hands in the handrange of the player
  }

  def requestHeroAction(tableId: Int) = {
    //decide if hero checks, calls, bets, raises and how much
  }

  def readDatabase = {
    //OCR only adds actions to the database if hero is still in the pot
    //grinderId, tableId, action, string
    //depending on action use switch case parse string
  }
  def main = {
    // take PokerSite, NL20-1000, username / pass as parameters
    //run startSession
    //communicate with database? 
  }

}
