// Takes a text file as input; counts num occurances of each word and filters those below 4.

val inputfile = sc.textFile("VariousWords.txt")

// split file by spaces
val words = inputfile.flatMap(x => x.split(" "))

// increment count for each word
val count = words.map(x => (x, 1)).reduceByKey{case(a,b) => a + b}

// Convert the index for the word's count to an integer for comparison purposes
val convert = count.map(x => (x._1, x._2.toInt))

// result is only those with a count of 4 or greater
val numLarge = convert.collect{case(a,b) if b >= 4 => a}

// creates a directory with the result of the output
count.saveAsTextFile("VariousWords")
