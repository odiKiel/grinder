package grinder

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import scala.collection.mutable.ArrayBuffer

object Eval {

  val ois = new ObjectInputStream(new FileInputStream("handRanks"));
  val handRanks = ois.readObject().asInstanceOf[Array[Int]];


  def stringToHand(handString: String): Int = {
    val suit = "cdhs"
    val value = "23456789TJQKA"
    value.indexOf(handString(0))*4+(suit.indexOf(handString(1))+1)
  }

  //a complete hand has 7 cards!
  def handValue(cards: Array[Int], value: Int = 53): Int = {
    var resultValue = value
    cards.foreach((i: Int) => resultValue = handRanks(resultValue+i))
    resultValue
  }

  //should not use this method otherwise multiple calculations of same hand
  def strongerThan(hand1: Array[Int], hand2: Array[Int]) = {
    val handValue1 = handValue(hand1)
    val handValue2 = handValue(hand2)
    handValue1.compare(handValue2)
  }
	/*
	 * oneBasedRankFirst
	 * Card to integer conversions:
	 * 2c =  1	2d =  2	2h =  3	2s =  4
	 * 3c =  5	3d =  6	3h =  7	3s =  8
	 * 4c =  9	4d = 10	4h = 11	4s = 12
	 * 5c = 13	5d = 14	5h = 15	5s = 16
	 * 6c = 17	6d = 18	6h = 19	6s = 20
	 * 7c = 21	7d = 22	7h = 23	7s = 24
	 * 8c = 25	8d = 26	8h = 27	8s = 28
	 * 9c = 29	9d = 30	9h = 31	9s = 32
	 * Tc = 33	Td = 34	Th = 35	Ts = 36
	 * Jc = 37	Jd = 38	Jh = 39	Js = 40
	 * Qc = 41	Qd = 42	Qh = 43	Qs = 44
	 * Kc = 45	Kd = 46	Kh = 47	Ks = 48
	 * Ac = 49	Ad = 50	Ah = 51	As = 52
	 */


  //Todo villianHandRanges with probabilities, boardCards cannot be part of villains hand
  //binary representation of handranges => range1 & range2 = all hands that they have in common
  //169 possible hands
  def evaluate(heroCards: Array[Int], villainsHandRanges: Array[Array[Array[Int]]], boardCards: Array[Int], deadCards: Array[Int] = Array(0)): scala.math.BigDecimal = {
    //calculate handValue of board, 
    //allcards - boardCards - deadCards - heroCards
    var allCards = ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52)
    allCards --= (heroCards ++ boardCards ++ deadCards)

    val numberOfPlayers = villainsHandRanges.length
    val numberOfRandCards = 5-boardCards.length
    val boardValue = handValue(boardCards)

    //currently using 0 as second card by turn test not so good
    val allPlusOneBoards = allCards.map((card: Int) => ((card, 0) -> Eval.handValue(Array(card), boardValue)))
    val finalBoards = if(numberOfRandCards == 2) {
      allPlusOneBoards.flatMap({case ((turn, zero), board) => {
        (allCards -= turn).map((card: Int) => ((turn, card) -> Eval.handValue(Array(card), board)))
      }})//((Int, Int), Int): ((Turn, River), BoardValue)  2652 Boards!
    } else {allPlusOneBoards}
    //calculate handvalue for all possible final boards: for 5 cards => each hand needs only two lookups
    // HashMap with (Int, Int) => boardValue, (Int, Int) turn, river card
    // thus compare villians hand with turn and river card if one of the cards is included discard board

    //at first against one villain
    //villainsHandRanges[0].map((hand: (Int, Int)) => allPlusTwoboards.filter((board: ((Int, Int), Int)) => handContains(hand, board._1)).map((board: ((Int, Int), Int)) => handValue(heroCards, board._2).compare(handValue(hand, board._2))))

    var winner: scala.math.BigDecimal = 0.0
    var playedBoards: scala.math.BigDecimal = 0.0
    for(hand: Array[Int] <- villainsHandRanges(0);
        board: ((Int, Int), Int) <- finalBoards.filter((board: ((Int, Int), Int)) => !handContains(hand, board._1))) {
        val heroHandValue = handValue(heroCards, board._2)
        val villainHandValue = handValue(hand, board._2)
        println("turn: "+board._1._1+ "river: "+board._1._2+" hero: "+handCategory(heroHandValue)+" villain: "+handCategory(villainHandValue))
        if(heroHandValue > villainHandValue) {winner += 1}
        else if(heroHandValue == villainHandValue) {winner += (1 / numberOfPlayers)}
        playedBoards += 1
    }
    println(playedBoards+" played boards, win percentage: "+winner/playedBoards)
    winner / playedBoards

  }

  def handContains(hand: Array[Int], turnRiver: (Int, Int)) = {
    hand(0) == turnRiver._1 || hand(0) == turnRiver._2 || hand(1) == turnRiver._1 || hand(1) == turnRiver._2
  }

  def handCategory(handValue: Int): String = {
    val hands = Array("Invalid Hand", "High Card", "One Pair", "Two Pair", "Three of a Kind", "Straight", "Flush", "Full House", "Four of a Kind", "Straight Flush")
    hands(handValue >> 12)
  }

  def rankInCategory(handValue: Int): Int = {
     handValue & 0x00000FFF
  }
  //with handvalue of 5 cards add two more cards in all combinations to calc all hand strengths

  //calculate win percentage against multiple opponents!
}
