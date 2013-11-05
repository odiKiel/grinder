package grinder

class Hero() {
  var player: Player = null
  var hand: String = ""
  //stackSize at leat 100 bb

  //depending on currentPot active player
  //think about implied odds stack sizes must be included
  //create action with: handGroup/Expected Value, potSize, StackSizes, playerDependencies
  def requestAction(table: Table) = {
    table.currentStatus match {
      case(TableStatus.NewRound) => {
        val handGroup = HandRangeGroup.handToGroup(hand) //handGroup of my hand
        val handRanges = table.currentPot.handRanges(player) //handRanges of other players
        val playersToAct = table.currentPot.playersToAct(player) //list of players that have to act
        preFlop(handGroup, handRanges, playersToAct)
      }
      case(TableStatus.Flop) => {}
      case(TableStatus.Turn) => {}
      case(TableStatus.River) => {}
    }
  }

  //playersToAct is preflop only the players to the button small and big blind is not included (=> +2)
  //datastructure: automata?
  //think about amount to raise
  //include stackSizes
  def preFlop(handGroup: Integer, handRanges: List[Array[Int]], playersToAct: List[Player]) = {
    if(playersToAct ==0) {//big blind => tight aggressive
      //if my hand dominates the top handrange (the smallest handrange with the best hands) => raise
      //if pod odds are high enough => call
      //else check/fold
    }
    else if(playersToAct == 1) { //small blind => tight aggressive
      //if my hand dominates the top handrange (the smallest handrange with the best hands) => raise
      //if no hand range check if hand fullfills position criteria => raise | criteria should include playersToAct tendencies (broader or more narrow)
      //if pod odds are high enough => call
    }
    else if(playersToAct == 2) { //button => hyper aggressive 
      //if my hand dominates the top handrange (the smallest handrange with the best hands) => raise
      //if no hand range check if hand fullfills position criteria=> raise
      //if pod odds are high enough => call
    }
    else if(playersToAct == 3) { //cut off => still pretty aggressive
      //if my hand dominates the top handrange (the smallest handrange with the best hands) => raise
      //if no hand range check if hand fullfills position criteria=> raise
      //if pod odds are high enough => call
    }
    else if(playersToAct > 3 && playersToAct < 7) { //middle position => tight aggressive
      //if my hand dominates the top handrange (the smallest handrange with the best hands) => raise
      //if no hand range check if hand fullfills position criteria=> raise
      //if pod odds are high enough => call
    }
    else { // Under the gun pretty tight
      //if my hand dominates the top handrange (the smallest handrange with the best hands) => raise
      //if no hand range check if hand fullfills position criteria=> raise
      //if pod odds are high enough => call
    }
  }


  //think about pot control how big should the pot become at the end of the hand => what amount to bet
  def flop(handRanges: List[Array[Int]], playersToAct: List[Player]) = {
    //calculate expected value with handRanges
    //high expected value => bet/raise/checkraise
    //low expected value => check/fold/bluff

    //situation: there is a bet / there is no bet => playersToAct and their tendencies(callingstations, always bet, likes to raise) check previous players for check raise probability

    //think about position: bet into opponent, continuation bet, check raise
  }
  
  //instead of position players to act?

  //calculates the range that other players might perceive by the action the grinder took
  //Deck.parseCardMask("Ac Ad")

  //var hand: Long


}
