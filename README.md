# CYK
This is a console application that demonstrates the working of CYK algorithm.
(https://en.wikipedia.org/wiki/CYK_algorithm)
### Input

   - nterms - a set of nonterminal symbols,
   - terms - a set of terminal symbols,
   - starts - a set of start symbols,
   - productions - a set of production of the grammar,
   - word - an input sequence.
   
Example input file: 

```
    {
      "nterms":["S","S1","S2", "S3","S4","Y","X","Y1","X1"],
      "starts":["S"],
      "terms":["a","b", "c", "y"],
      "productions":["S->S3+S1",
                  "X->S3+Y","X->X1+Y",
                  "Y->S3+Y","Y->X1+Y","Y->Y1+Y1",
                  "S1->X+S2", "S1->S4+X", "S1->y",
                  "S2->S4+X", "S2->y",
                  "S3->a",
                  "S4->y",
                  "X1->b",
                  "Y1->c"],
      "word":"aaccy"
    }
```


 The result of the algorithm
    
```
String is member of language
┌──────────────┬──────────────┬──────────────┬──────────────┬──────────────┐
│ S S1         │              │              │              │              │
├──────────────┼──────────────┼──────────────┼──────────────┼──────────────┤
│ Y X          │ S1           │              │              │              │
├──────────────┼──────────────┼──────────────┼──────────────┼──────────────┤
│              │ Y X          │              │              │              │
├──────────────┼──────────────┼──────────────┼──────────────┼──────────────┤
│              │              │ Y            │              │              │
├──────────────┼──────────────┼──────────────┼──────────────┼──────────────┤
│ S3           │ S3           │ Y1           │ Y1           │ S1 S2 S4     │
├──────────────┼──────────────┼──────────────┼──────────────┼──────────────┤
│ a            │ a            │ c            │ c            │ y            │
└──────────────┴──────────────┴──────────────┴──────────────┴──────────────┘

```

### Build
 - If you don't have maven, install mvn
```sh
$ sudo apt-get install maven
```

 - Change directory on "CYK"
 - Install project
```sh
$ mvn install
```

### Run
 - Change directory on "target"
 - run CYK-1.0-SNAPSHOT-jar-with-dependencies.jar
 
 ```sh
 $ java -jar  CYK-1.0-SNAPSHOT-jar-with-dependencies.jar filepath
 ```
