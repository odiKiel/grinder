package grinder
import org.specs2.mutable._

object HandRangeGroupSpec extends Specification {
  "HandRangeGroup" should {
    "return the hand group of a hand" in {
      HandRangeGroup.handToGroup("AsAh") mustEqual 0
      HandRangeGroup.handToGroup("AdJd") mustEqual 1
      HandRangeGroup.handToGroup("JsTs") mustEqual 2
      HandRangeGroup.handToGroup("KsQh") mustEqual 3
      HandRangeGroup.handToGroup("9d7d") mustEqual 4
      HandRangeGroup.handToGroup("6d6s") mustEqual 5
      HandRangeGroup.handToGroup("Js9c") mustEqual 6
      HandRangeGroup.handToGroup("Ah9s") mustEqual 7
      HandRangeGroup.handToGroup("Jd2d") mustEqual 8
    }
  }
}
