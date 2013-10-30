package grinder

import java.io.ObjectInputStream;
import java.io.FileInputStream;

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


  //Todo villianHandRanges with probabilities
  //binary representation of handranges => range1 & range2 = all hands that they have in common
  //169 possible hands
  def evaluate(heroCards: (Int, Int), villiansHandRanges: Array[List[(Int, Int)]], boardCards: Array[Int], deadCards: Array[Int]): scala.math.BigDecimal = {
    //calculate handValue of board, 
    //allcards - boardCards - deadCards - heroCards
    val numberOfRandCards = 5-boardCards.length
    val boardValue = handValue(boardCards)
    2.8
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
