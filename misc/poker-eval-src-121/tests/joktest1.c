/* $Id: joktest1.c,v 1.1 2002/04/05 08:41:07 mjmaurer Exp $
   joktest1.c -- compare joker evaluator with standard evaluator for those
   hands that do not include the joker
*/

#include	<stdio.h>
#include	<stdlib.h>
#include	"poker_defs.h"
#include        "inlines/eval.h"
#include        "inlines/eval_low.h"
#include        "inlines/eval_low8.h"
#include	"inlines/eval_joker.h"
#include	"inlines/eval_joker_low.h"
#include	"inlines/eval_joker_low8.h"

int main(void)
{
  JokerDeck_CardMask jCards;
  JokerDeck_CardMask dead;
  StdDeck_CardMask sCards;
  HandVal hival1, hival2;
  LowHandVal loval1, loval2;
  LowHandVal lo8val1, lo8val2;

  JokerDeck_CardMask_RESET(dead);
  JokerDeck_CardMask_SET(dead, JokerDeck_JOKER);

  DECK_ENUMERATE_5_CARDS_D(JokerDeck, jCards, dead, 
    {
      JokerDeck_CardMask_toStd(jCards, sCards);
      hival1 = StdDeck_StdRules_EVAL_N(sCards, 5);
      hival2 = JokerDeck_JokerRules_EVAL_N(jCards, 5);

      loval1 = StdDeck_Lowball_EVAL(sCards, 5);
      loval2 = JokerDeck_Lowball_EVAL(jCards, 5);

      lo8val1 = StdDeck_Lowball8_EVAL(sCards, 5);
      lo8val2 = JokerDeck_Lowball8_EVAL(jCards, 5);

      if (hival1 != hival2 ||
          loval1 != loval2 ||
          lo8val1 != lo8val2) {
        fprintf(stderr, "Standard and Joker eval disagree: %s",
               DmaskString(JokerDeck, jCards));
        fprintf(stderr, "\n%s: StdDeck HI: %d ", DmaskString(StdDeck, sCards), hival1);
        StdRules_HandVal_print(hival1);
        fprintf(stderr, "\n%s: JokerDeck HI: %d ", DmaskString(JokerDeck, jCards), hival2);
        JokerRules_HandVal_print(hival2);

        fprintf(stderr, "\n%s: StdDeck LO: %d ", DmaskString(StdDeck, sCards), loval1);
        LowHandVal_print(loval1);
        fprintf(stderr, "\n%s: JokerDeck LO: %d ", DmaskString(JokerDeck, jCards), loval2);
        LowHandVal_print(loval2);

        fprintf(stderr, "\n%s: StdDeck LO8: %d ", DmaskString(StdDeck, sCards), lo8val1);
        LowHandVal_print(lo8val1);
        fprintf(stderr, "\n%s: JokerDeck LO8: %d ", DmaskString(JokerDeck, jCards), lo8val2);
        LowHandVal_print(lo8val2);
        fprintf(stderr, "\n");
        return 1;
      }
    });
  printf("joktest1: all tests passed\n");
  return 0;
}


