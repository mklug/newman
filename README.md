### Newman ###

This simple program gives an implementation of the algorithm for solving the word problem
in torsion one relator groups that results from Newman's Spelling Theorem.  A fun read
on the history of this theorem was written up by [Nyberg-Brodda](https://arxiv.org/abs/2004.01484).
A nice proof (using iterated HNN extensions and Britton's lemma) can by found in this 
[paper](McCool&Schupp) by McCool and Schupp.  There is a more general result of 
[Magnus](Magnus) (see also the aforementioned paper of McCool and Schupp) 
that the word problem for one relator groups is solvable, however the resulting
algorithm is much slower than the algorithm resulting from the Spelling Theorem.  

## setup ##

The program is  written in java and thus requires a java virtual machine to run. The program
can be compiled with the following command run from within the directory containing the java files

```
> javac . -d *.java
``` 


## usage ##

There are three different modes that the program can be run in.  The first is just a one-time
run of the algorithm.  The command
```
> java newman/Newman [relation] [power] [word]
```
Determines if the word is trivial in the torsion one relator group with the given relation.  

For example, the command
```
> java newman/Newman abab 2 ababBAbabaAaABABababab
```
returns
```
The word is trivial.
```
whereas the commmand
```
java newman/Newman abab 2 AababBAbabaAaABABabababa
```
returns
```
The word is not trivial.
```
since the respective words are trivial and not trivial in the group <a,b | (abab)^2 >.


Just entering
```
> java newman/Newman
```
enters into a session where a new relator is given each time.  The command
```
> java newman/Newman [relation] [power]
```
enters a session where varying words can be examined for a fixed group. 

Finally, a word or two on some conventions.  Capital letters denote the inverse of the respective
lower-case letters (e.g., 'A' is the inverse of 'a').  In any mode, when the relation is entered, 
the group then has as generators all the letters _up to_ the letters in the given relation (e.g.,
if the relation given is 'aabaBd', then the group will have generators 'a,b,c,d'). 


## copyright ##

This program is released under the terms of the GNU GPL version 3.0. See the attached license file.

[https://www.cambridge.org/core/services/aop-cambridge-core/content/view/A51E086AF79B5D32E0F2A590B01BF779/S1446788700014300a.pdf/div-class-title-on-one-relator-groups-and-span-class-italic-hnn-span-extensions-div.pdf]:(McCool&Schupp)

[https://link.springer.com/article/10.1007/BF01455888]:(Magnus)
