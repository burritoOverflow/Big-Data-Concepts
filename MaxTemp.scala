// given a comma seperated list of locations and temps (one per line) find the maximum for each location

val inputFile = sc.textFile("Temps.txt")
val splitValues = inputFile.map(x => x.split(","))
val keyVal = splitValues.map(x => (x(0), x(1)))
val maxTemp = keyVal.reduceByKey{case(a,b) => if (a.toInt > b.toInt) {a} else {b}}
maxTemp.saveAsTextFile("TempOutput")
