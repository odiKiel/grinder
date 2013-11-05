package grinder
import org.specs2.mutable._

object EvalSpec extends Specification {
  "Eval" should {
    val heroHand = Eval.stringToHand("JhTh") 
    "calculate the expected value for a headsup on the flop with known hands" in {
      val villainFirst = new Player("villain", 200.0)
      villainFirst.handRange = List(Eval.stringToHand("9d9c"))
      Eval.evaluate(heroHand, Array(villainFirst), Array(Eval.stringToCard("Qh"), Eval.stringToCard("9h"), Eval.stringToCard("8s"))) mustEqual 0.7045454545454546
    }

    "calculate the expected value for a headsup on the flop with a handrange of three" in {
      val villainSecond = new Player("villain", 200.0)
      villainSecond.handRange = List(Eval.stringToHand("9d9c"), Eval.stringToHand("AhAd"), Eval.stringToHand("AhKh"))
      Eval.evaluate(heroHand, Array(villainSecond), Array(Eval.stringToCard("Qh"), Eval.stringToCard("9h"), Eval.stringToCard("8s"))) mustEqual 0.802020202020202
    }

    "calculate the expected value against three villains on the flop with a handrange of two times three and one time two"  in {
      val villainThird = new Player("villain", 200.0)
      villainThird.handRange = List(Eval.stringToHand("9d9c"), Eval.stringToHand("AhAd"), Eval.stringToHand("AhKh"))
      val villain2 = new Player("villain2", 200.0)
      villain2.handRange = List(Eval.stringToHand("AhAd"), Eval.stringToHand("TsJs"))
      val villain3 = new Player("villain3", 200.0)
      villain3.handRange = List(Eval.stringToHand("2h2d"), Eval.stringToHand("QdQs"), Eval.stringToHand("9s8h"))
      Eval.evaluate(heroHand, Array(villainThird, villain2, villain3), Array(Eval.stringToCard("Qh"), Eval.stringToCard("9h"), Eval.stringToCard("8s"))) mustEqual 0.5008384146341464

    }
  }
}
