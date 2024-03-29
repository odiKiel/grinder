/*
   Note that this file has two #if .. #endif sections -- one for 
   StdDeck macros to prevent double-inclusion, and one to define the 
   generic Rules_ macros if RULES_STANDARD is defined 
*/

#ifndef __RULES_JOKER_H__
#define __RULES_JOKER_H__

#define JokerRules_HandType_NOPAIR    0
#define JokerRules_HandType_ONEPAIR   1
#define JokerRules_HandType_TWOPAIR   2
#define JokerRules_HandType_TRIPS     3
#define JokerRules_HandType_STRAIGHT  4
#define JokerRules_HandType_FLUSH     5
#define JokerRules_HandType_FULLHOUSE 6
#define JokerRules_HandType_QUADS     7
#define JokerRules_HandType_STFLUSH   8
#define JokerRules_HandType_QUINTS    9
#define JokerRules_HandType_FIRST     JokerRules_HandType_NOPAIR
#define JokerRules_HandType_LAST      JokerRules_HandType_QUINTS
#define JokerRules_HandType_COUNT     10

#define JokerRules_FIVE_STRAIGHT \
 ((1 << StdDeck_Rank_ACE ) \
  | (1 << StdDeck_Rank_2 ) \
  | (1 << StdDeck_Rank_3 ) \
  | (1 << StdDeck_Rank_4 ) \
  | (1 << StdDeck_Rank_5 ))

extern const char *JokerRules_handTypeNames[JokerRules_HandType_LAST+1];
extern const char *JokerRules_handTypeNamesPadded[JokerRules_HandType_LAST+1];

extern int JokerRules_nSigCards[JokerRules_HandType_LAST+1];

extern int JokerRules_HandVal_toString(HandVal handval, char *outString); 
extern int JokerRules_HandVal_print(HandVal handval);

#endif

#ifdef RULES_JOKER

#if defined(HandType_COUNT)
#include "rules_undef.h"
#endif

#define HandType_NOPAIR    JokerRules_HandType_NOPAIR    
#define HandType_ONEPAIR   JokerRules_HandType_ONEPAIR   
#define HandType_TWOPAIR   JokerRules_HandType_TWOPAIR   
#define HandType_TRIPS     JokerRules_HandType_TRIPS     
#define HandType_STRAIGHT  JokerRules_HandType_STRAIGHT  
#define HandType_FLUSH     JokerRules_HandType_FLUSH     
#define HandType_FULLHOUSE JokerRules_HandType_FULLHOUSE 
#define HandType_QUADS     JokerRules_HandType_QUADS     
#define HandType_STFLUSH   JokerRules_HandType_STFLUSH    
#define HandType_QUINTS    JokerRules_HandType_QUINTS    
#define HandType_FIRST     JokerRules_HandType_FIRST  
#define HandType_COUNT     JokerRules_HandType_COUNT     
#define HandType_LAST      JokerRules_HandType_LAST

#define handTypeNames        JokerRules_handTypeNames
#define handTypeNamesPadded  JokerRules_handTypeNamesPadded
#define nSigCards            JokerRules_nSigCards
#define HandVal_print        JokerRules_HandVal_print
#define HandVal_toString     JokerRules_HandVal_toString

#endif

