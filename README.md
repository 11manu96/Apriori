# Data-Mining-and-Warehousing

NAME
     Apriori -- runs Apriori Algorith on stastical Data sets

SYNOPSIS
     ls [-v][-s1s2n]

DESCRIPTION
     For the Support Threshold (s1: for pass 1  and s2: for pass 2), number of transactions entered by the user, the algorithm outputs 1,2 degree item sets which pass the support value, it also outputs the confidence value for the itemsets passing the support value.


     The following options are available:

     -v      Run in Debug Mode. Usage : java Apriori -v [-s1s2nf]

     -s1     Set Support threshold for first pass. i.e. support pass for 1 - item itemsets.

     -s2     Set Support threshold for Second pass. i.e. support pass for 2 - item itemsets.

     -n      Set the number of Transactions on which the algorithm should run on.

     -f      Speficiy the file you want the algorithm to run on
