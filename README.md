Examples to run the two smaller programs are below; best to use the script.


for MaxTemp.java

go to folder - /usr/local/hadoop/share/hadoop/mapreduce/mymaxtemp
 
$ export HADOOP_CLASSPATH=$(/usr/local/hadoop/bin/hadoop classpath)
 
save MaxTemo.Java here

create MaxTemp dir in /usr/local/hadoop/share/hadoop/mapreduce/mymaxtemp
 
javac -classpath ${HADOOP_CLASSPATH} -d MaxTemp/ MaxTemp.java
 
create input.txt file in home/input folder
within temps.txt type 
"Jan 20
Feb 28
March 30
April 40
Jan 26
Feb 17
March 32" 
 
cd (go to root dir)
/usr/local/hadoop/bin/hadoop jar /usr/local/hadoop/share/hadoop/mapreduce/mymaxtemp/MaxTemp.jar org.apache.hadoop.ramapo.MaxTemp ~/input/Temps.txt ~/MaxTempoutput



For WordCount.java

go to folder - /usr/local/hadoop/share/hadoop/mapreduce/mywordcount
 
$ export HADOOP_CLASSPATH=$(/usr/local/hadoop/bin/hadoop classpath)
 
javac ~classpath ${HADOOP_CLASSPATH} ~d WordCount/ WordCount.java
 
jar ~cvf WordCount.jar ~C WordCount/.
 
create input.txt file in home/input folder
within wordcount.txt type "Hello Hello World Big Big Data Ramapo"
  
cd
/usr/local/hadoop/bin/hadoop jar /usr/local/hadoop/share/hadoop/mapreduce/mywordcount/WordCount.jar org.apache.hadoop.examples.WordCount ~/input/WordCount.txt ~/WordCountoutput
