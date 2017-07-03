///////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////// SCALA FOUNDAMENTALS ///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////




object LearningScala {
  // VALUES are immutable constants. You can't change them once defined.
 val hello: String = "Hello!"                     //> hello  : String = Hello!
 println(hello)                                   //> Hello!

 // Notice how Scala defines things backwards from other languages - you declare the
 // name, then the type.

 // VARIABLES are mutable
 var helloThere: String = hello                   //> helloThere  : String = Hello!
 helloThere = hello + " There!"
 println(helloThere)                              //> Hello! There!


 // One key objective of functional programming is to use immutable objects as often as possible.
 // Try to use operations that transform immutable objects into a new immutable object.
 // For example, we could have done the same thing like this:
 val immutableHelloThere = hello + "There!"       //> immutableHelloThere  : String = Hello!There!
 println(immutableHelloThere)                     //> Hello!There!

 // Some other types
 val numberOne : Int = 1                          //> numberOne  : Int = 1
 val truth : Boolean = true                       //> truth  : Boolean = true
 val letterA : Char = 'a'                         //> letterA  : Char = a
 val pi : Double = 3.14159265                     //> pi  : Double = 3.14159265
 val piSinglePrecision : Float = 3.14159265f      //> piSinglePrecision  : Float = 3.1415927
 val bigNumber : Long = 1234567890l               //> bigNumber  : Long = 1234567890
 val smallNumber : Byte = 127                     //> smallNumber  : Byte = 127

 // String printing tricks
 // Concatenating stuff with +:
 println("Here is a mess: " + numberOne + truth + letterA + pi + bigNumber)
                                                  //> Here is a mess: 1truea3.141592651234567890


 // printf style:
 println(f"Pi is about $piSinglePrecision%.3f")   //> Pi is about 3.142
 println(f"Zero padding on the left: $numberOne%05d")
                                                  //> Zero padding on the left: 00001


 // Substituting in variables:
 println(s"I can use the s prefix to use variables like $numberOne $truth $letterA")
                                                  //> I can use the s prefix to use variables like 1 true a

 // Substituting expressions (with curly brackets):
 println(s"The s prefix isn't limited to variables; I can include any expression. Like ${1+2}")
                                                  //> The s prefix isn't limited to variables; I can include any expression. Like
                                                  //|  3


 // Using regular expressions:
 val theUltimateAnswer: String = "To life, the universe, and everything is 42."
                                                  //> theUltimateAnswer  : String = To life, the universe, and everything is 42.


 val pattern = """.* ([\d]+).*""".r               //> pattern  : scala.util.matching.Regex = .* ([\d]+).*
 val pattern(answerString) = theUltimateAnswer    //> answerString  : String = 42
 val answer = answerString.toInt                  //> answer  : Int = 42
 println(answer)                                  //> 42

 // Dealing with booleans
 val isGreater = 1 > 2                            //> isGreater  : Boolean = false
 val isLesser = 1 < 2                             //> isLesser  : Boolean = true
 val impossible = isGreater & isLesser            //> impossible  : Boolean = false
 val anotherWay = isGreater && isLesser           //> anotherWay  : Boolean = false

 val picard: String = "Picard"                    //> picard  : String = Picard
 val bestCaptain: String = "Picard"               //> bestCaptain  : String = Picard
 val isBest: Boolean = picard == bestCaptain      //> isBest  : Boolean = true

 // EXERCISE
 // Write some code that takes the value of pi, doubles it, and then prints it within a string with
 // three decimal places of precision to the right.
 val doublePi: Double = 2*pi                      //> doublePi  : Double = 6.2831853
 println(f"This is doublePi: $doublePi%.3f")      //> This is doublePi: 6.283
 
 ///////////////////////////////////////////////////////////////////////////////////
 //////////////////////////////////// FLOW CONTROL /////////////////////////////////
 ///////////////////////////////////////////////////////////////////////////////////
  
  // If / else syntax
  if (1 > 3) println("Impossible!") else println("The world makes sense.")
                                                  //> The world makes sense.
  val max = if (3 > 4) 3 else 4                   //> max  : Int = 4
  if (1 > 3) {
  	println("Impossible!")
  } else {
  	println("The world makes sense.")
  }                                               //> The world makes sense.
  
  // Matching - like switch in other languages:
  val number = 3                                  //> number  : Int = 3
  number match {
  	case 1 => println("One")
  	case 2 => println("Two")
  	case 3 => println("Three")
  	case _ => println("Something else")
 	}                                         //> Three
 	
 	// For loops
 	for (x <- 1 to 4) {
 		val squared = x * x
 		println(squared)
 	}                                         //> 1
                                                  //| 4
                                                  //| 9
                                                  //| 16
                                                  
  // While loops
  var x = 10                                      //> x  : Int = 10
  while (x >= 0) {
  	println(x)
  	x -= 1
  }                                               //> 10
                                                  //| 9
                                                  //| 8
                                                  //| 7
                                                  //| 6
                                                  //| 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
                                                  
  x = 0
  do { println(x); x+=1 } while (x <= 10)         //> 0
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
                                                  //| 5
                                                  //| 6
                                                  //| 7
                                                  //| 8
                                                  //| 9
                                                  //| 10
                                                  
   // Expressions
   // "Returns" the final value in a block automatically
   
	{val x = 10; x + 20}                      //> res0: Int = 30
                                                
	println({val x = 10; x + 20})             //> 30
	 
	 // EXERCISE
	 // Write some code that prints out the first 10 values of the Fibonacci sequence.
	 // This is the sequence where every number is the sum of the two numbers before it.
	 // So, the result should be 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
	var a: Int = 0                            //> a  : Int = 0
	var b: Int = 1                            //> b  : Int = 1
	for (i <- 1 to 10) {
		var c: Int = a + b
		a = b
		b = c
		println(c)
		}                                 //> 1
                                                  //| 2
                                                  //| 3
                                                  //| 5
                                                  //| 8
                                                  //| 13
                                                  //| 21
                                                  //| 34
                                                  //| 55
                                                  //| 89
	/////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////// FUNCTIONS //////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
  
  // Format is def <function name>(parameter name: type...) : return type = { expression }
  // Don't forget the = before the expression!
  def squareIt(x: Int) : Int = {
  	x * x
  }                                               //> squareIt: (x: Int)Int
  
  def cubeIt(x: Int): Int = {x * x * x}           //> cubeIt: (x: Int)Int
  
  println(squareIt(2))                            //> 4
  
  println(cubeIt(2))                              //> 8
  
  // Functions can take other functions as parameters
  
  def transformInt(x: Int, f: Int => Int) : Int = {
  	f(x)
  }                                               //> transformInt: (x: Int, f: Int => Int)Int
  
  val result = transformInt(2, cubeIt)            //> result  : Int = 8
  println (result)                                //> 8
  
  // "Lambda functions", "anonymous functions", "function literals"
  // You can declare functions inline without even giving them a name
  // This happens a lot in Spark.
  transformInt(3, x => x * x * x)                 //> res1: Int = 27
  
  transformInt(10, x => x / 2)                    //> res2: Int = 5
  
	transformInt(2, x => {val y = x * 2; y * y})
                                                  //> res3: Int = 16
  
  // This is really important!
  
  // EXERCISE
  // Strings have a built-in .toUpperCase method. For example, "foo".toUpperCase gives you back FOO.
  // Write a function that converts a string to upper-case, and use that function of a few test strings.
  // Then, do the same thing using a function literal instead of a separate, named function.
  def convertToUpperCase(x: String): String = {
  	x.toUpperCase
  }                                               //> convertToUpperCase: (x: String)String
  convertToUpperCase("Lol")                       //> res4: String = LOL
  def applyFunctionToString(x: String, f: String => String): String = {
  	f(x)
  }                                               //> applyFunctionToString: (x: String, f: String => String)String
  applyFunctionToString("Lol", x => x.toUpperCase)//> res5: String = LOL
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// DATA STRUCTURES ///////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
  
  // Tuples (Also really common with Spark!!)
  // Immutable lists
  // Often thought of as database fields, or columns.
  // Useful for passing around entire rows of data.
  
  val captainStuff = ("Picard", "Enterprise-D", "NCC-1701-D")
                                                  //> captainStuff  : (String, String, String) = (Picard,Enterprise-D,NCC-1701-D)
                                                  //| 
  println(captainStuff)                           //> (Picard,Enterprise-D,NCC-1701-D)
  
  // You refer to individual fields with their ONE-BASED index:
  println(captainStuff._1)                        //> Picard
  println(captainStuff._2)                        //> Enterprise-D
  println(captainStuff._3)                        //> NCC-1701-D
 
 // You can create a key/value pair with ->
 val picardsShip = "Picard" -> "Enterprise-D"     //> picardsShip  : (String, String) = (Picard,Enterprise-D)
 println(picardsShip._2)                          //> Enterprise-D
 
 // You can mix different types in a tuple
 val aBunchOfStuff = ("Kirk", 1964, true)         //> aBunchOfStuff  : (String, Int, Boolean) = (Kirk,1964,true)
 
 
 
 // Lists
 // Like a tuple, but it's an actual Collection object that has more functionality.
 // Also, it cannot hold items of different types.
 // It's a singly-linked list under the hood.
 
 val shipList = List("Enterprise", "Defiant", "Voyager", "Deep Space Nine")
                                                  //> shipList  : List[String] = List(Enterprise, Defiant, Voyager, Deep Space Ni
                                                  //| ne)
 val empty: List[Nothing] = List()                //> empty  : List[Nothing] = List()

// The list type is covariant, this means that for each pair of types S and T, if S is a subtype of T then List[S] is a subtype of List[T].
// Note that the empty list has type List[Nothing], but Nothing is at the bottom of the Scala hierarchy, so List[Nothing] is a subtype of List[T] for every T.
// So the empty list object can also be seen as an object of every other list type of form List[T], for example:
var xs: List[String] = List()                     //> xs  : List[String] = List()


 
 // Access individual members using () with ZERO-BASED index (confused yet?)
 println(shipList(1))                             //> Defiant
 
 // head and tail give you the first item, and the remaining ones.
 println(shipList.head)                           //> Enterprise
 println(shipList.tail)                           //> List(Defiant, Voyager, Deep Space Nine)
 
 
 // Iterating though a list
 for (ship <- shipList) {println(ship)}           //> Enterprise
                                                  //| Defiant
                                                  //| Voyager
                                                  //| Deep Space Nine
 
 // Let's apply a function literal to a list! map() can be used to apply any function to every item in a collection.
val backwardShips = shipList.map( (ship: String) => {ship.reverse})
                                                  //> backwardShips  : List[String] = List(esirpretnE, tnaifeD, regayoV, eniN eca
                                                  //| pS peeD)
 for (ship <- backwardShips) {println(ship)}      //> esirpretnE
                                                  //| tnaifeD
                                                  //| regayoV
                                                  //| eniN ecapS peeD
                                                  
// reduce() can be used to combine together all the items in a collection using some function.
val numberList = List(1, 2, 3, 4, 5)              //> numberList  : List[Int] = List(1, 2, 3, 4, 5)
val sum = numberList.reduce( (x: Int, y: Int) => x + y)
                                                  //> sum  : Int = 15
println(sum)                                      //> 15

// filter() can remove stuff you don't want. Here we'll introduce wildcard syntax while we're at it.
val iHateFives = numberList.filter( (x: Int) => x != 5)
                                                  //> iHateFives  : List[Int] = List(1, 2, 3, 4)
val iHateThrees = numberList.filter(_ != 3)       //> iHateThrees  : List[Int] = List(1, 2, 4, 5)

// Note that Spark has its own map, reduce, and filter functions that can distribute these operations. But they work the same way!
// Also, you understand MapReduce now :)

// Concatenating lists
val moreNumbers = List(6, 7, 8)                   //> moreNumbers  : List[Int] = List(6, 7, 8)
val lotsOfNumbers = numberList ++ moreNumbers     //> lotsOfNumbers  : List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8)

// More list fun
val reversed = numberList.reverse                 //> reversed  : List[Int] = List(5, 4, 3, 2, 1)
val sorted = reversed.sorted                      //> sorted  : List[Int] = List(1, 2, 3, 4, 5)
val lotsOfDuplicates = numberList ++ numberList   //> lotsOfDuplicates  : List[Int] = List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
val distinctValues = lotsOfDuplicates.distinct    //> distinctValues  : List[Int] = List(1, 2, 3, 4, 5)
val maxValue = numberList.max                     //> maxValue  : Int = 5
val total = numberList.sum                        //> total  : Int = 15
val hasThree = iHateThrees.contains(3)            //> hasThree  : Boolean = false

// Nil is the null list:
val nullList = Nil                                //> nullList  : scala.collection.immutable.Nil.type = List()
val OneList = 1 :: Nil                            //> OneList  : List[Int] = List(1)
nullList.isEmpty                                  //> res6: Boolean = true
OneList.isEmpty                                   //> res7: Boolean = false
val RealTotal = total :: OneList                  //> RealTotal  : List[Int] = List(15, 1)

// Zipping List
val zipped = List("a","b","c","d","e") zip List(1,2,3)
                                                  //> zipped  : List[(String, Int)] = List((a,1), (b,2), (c,3))
List("a","b","c","d","e").zipWithIndex            //> res8: List[(String, Int)] = List((a,0), (b,1), (c,2), (d,3), (e,4))
zipped.unzip                                      //> res9: (List[String], List[Int]) = (List(a, b, c),List(1, 2, 3))

//Higher order methods on class List
List(1,2,3) map(_+1)                              //> res10: List[Int] = List(2, 3, 4)
List(1,2,3).map(_+1)                              //> res11: List[Int] = List(2, 3, 4)
List.range(1, 5).flatMap (
	i => List.range(1,i).map(j => (i,j))
	)                                         //> res12: List[(Int, Int)] = List((2,1), (3,1), (3,2), (4,1), (4,2), (4,3))
List(1,2,3).foreach(println)                      //> 1
                                                  //| 2
                                                  //| 3
List(1,23).filter(_!=1)                           //> res13: List[Int] = List(23)

// The find method is similar to filter but it returns the first element satisfying the given condition.
List(1,5,10).find(_ > 3)                          //> res14: Option[Int] = Some(5)
// If there's returns Some(element) otherwise None
val range_1_10 = List.range(1,10,3)               //> range_1_10  : List[Int] = List(1, 4, 7)
val tab = List.tabulate(5)(x => x * x)            //> tab  : List[Int] = List(0, 1, 4, 9, 16)

// Processing multiple lists together
(List(1,3), List(2,5,10)).zipped.map(_ + _)       //> res15: List[Int] = List(3, 8)


// Maps
// Useful for key/value lookups on distinct keys
// Like dictionaries in other languages

val shipMap = Map("Kirk" -> "Enterprise", "Picard" -> "Enterprise-D", "Sisko" -> "Deep Space Nine", "Janeway" -> "Voyager")
                                                  //> shipMap  : scala.collection.immutable.Map[String,String] = Map(Kirk -> Ente
                                                  //| rprise, Picard -> Enterprise-D, Sisko -> Deep Space Nine, Janeway -> Voyage
                                                  //| r)
println(shipMap("Janeway"))                       //> Voyager

// Dealing with missing keys
println(shipMap.contains("Archer"))               //> false

val archersShip = util.Try(shipMap("Archer")) getOrElse "Unknown"
                                                  //> archersShip  : String = Unknown
println(archersShip)                              //> Unknown

// Set, for default it is immutable
var jetSet = Set("Boeing", "Airbus")              //> jetSet  : scala.collection.immutable.Set[String] = Set(Boeing, Airbus)
jetSet += "Lear"
println(jetSet.contains("Cessna"))                //> false
println(jetSet)                                   //> Set(Boeing, Airbus, Lear)
// The Set is immutable the command += simply create a new jetSet object with the three items, if we want the mutable one we have to make an import:
import scala.collection.mutable.Set
val movieSet = Set("Hitch", "Poltergeist")        //> movieSet  : scala.collection.mutable.Set[String] = Set(Poltergeist, Hitch)
                                                  //| 
movieSet += "Shrek"                               //> res16: LearningScala.movieSet.type = Set(Poltergeist, Shrek, Hitch)
println(movieSet)                                 //> Set(Poltergeist, Shrek, Hitch)


// Class
class Accumulator {
	private val b:Int = 5
	val a = 10
	def add(x: Int): Int = {a + x*b}
}
// Private val cannot be accessed from outside the class
var acc = new Accumulator                         //> acc  : LearningScala.Accumulator = LearningScala$$anonfun$main$1$Accumulat
                                                  //| or$2@7803e4a4
acc.a                                             //> res17: Int = 10
//acc.b
acc.add(10)                                       //> res18: Int = 60

// Singleton Objects

object Accumulator {
	private val lol = "lol"
}
// When a singleton object shares the same name with a class, it is called that class’s companion object.
// You must define both the class and its companion object in the same source file. The class is called the companion class of the singleton object.
// A class and its companion object can access each other’s private members.
// A singleton object that does not share the same name with a companion class is called a standalone object. You can use standalone objects for many purposes, including collecting related utility methods together, or defining an entry point to a Scala application.

// To run a Scala program, you must supply the name of a standalone singleton object with a main method that takes one parameter, an Array[String], and has a result type of Unit . Any standalone object with a main method of the proper signature can be used as the entry point into an application.

class Division(a: Double, b: Double) {
	val c = a/b
	}
val d = new Division(1.0,2.0)                     //> d  : LearningScala.Division = LearningScala$$anonfun$main$1$Division$1@4e5
                                                  //| 8c0be
d.c                                               //> res19: Double = 0.5


// Arrays

// Arrays allow you to hold a sequence of elements and efficiently access an element at an arbitrary position, both to get or update the element with a zero-based index.
val FiveInts = new Array[Int](5) // arrays that you know the type and the length nothing more
                                                  //> FiveInts  : Array[Int] = Array(0, 0, 0, 0, 0)
val fiveToOne = Array(5,4,3,2,1)                  //> fiveToOne  : Array[Int] = Array(5, 4, 3, 2, 1)
FiveInts(0) = fiveToOne(4) // accessing and updating an array
FiveInts.update(1, 5)
FiveInts                                          //> res20: Array[Int] = Array(1, 5, 0, 0, 0)

// List buffers

// Class List provides fast access to the head of the list but not the end. Thus when you need to build a list by appending to the end, you should consider building the list backwards and then calling reverse on the list.
List(1,2,3) ::: List(4)                          // Note that this operation has a complexity of O(n). If you need this operation frequently, or for long lists, consider using another data type (e.g. a ListBuffer).
                                                  //> res21: List[Int] = List(1, 2, 3, 4)
(5 :: List(1,2,3)).reverse                        //> res22: List[Int] = List(3, 2, 1, 5)

// Another alteLIST BUFFERSrnative is to use ListBuffer, that is a mutable object which can help to build lists more efficiently when there's the need to append elements.
import scala.collection.mutable.ListBuffer
val buf = new ListBuffer[Int]                     //> buf  : scala.collection.mutable.ListBuffer[Int] = ListBuffer()
buf += 1                                          //> res23: LearningScala.buf.type = ListBuffer(1)
buf += 2                                          //> res24: LearningScala.buf.type = ListBuffer(1, 2)
buf                                               //> res25: scala.collection.mutable.ListBuffer[Int] = ListBuffer(1, 2)
3 +=: buf                                         //> res26: LearningScala.buf.type = ListBuffer(3, 1, 2)
buf.toList                                        //> res27: List[Int] = List(3, 1, 2)

// Array buffers

// An ArrayBuffer is like an Array, except that you can additionally add and remove elements from the beginning and the ending of the sequence. All Array operations are available but they are slower.

import scala.collection.mutable.ArrayBuffer
val abuf = new ArrayBuffer[Int]()                 //> abuf  : scala.collection.mutable.ArrayBuffer[Int] = ArrayBuffer()
abuf += 12                                        //> res28: LearningScala.abuf.type = ArrayBuffer(12)
abuf.length                                       //> res29: Int = 1
3 +=: abuf                                        //> res30: LearningScala.abuf.type = ArrayBuffer(3, 12)

// Sets and Maps

// For default sets and maps are immutable, if you want the mutable variant you have to explicit import it:
import scala.collection.mutable
val mutaSet = mutable.Set(1,2,3)                  //> mutaSet  : scala.collection.mutable.Set[Int] = Set(1, 2, 3)
val mutableMap = mutable.Map.empty[String, Int]   //> mutableMap  : scala.collection.mutable.Map[String,Int] = Map()
mutableMap("hello") = 1
mutableMap("lol") = 2

// Sorted sets and maps

// On occasion you may need a set or a map whose iterator returns elements in a particular order. So there are SortedSet and SortedMap.These traits are implemented by classes TreeSet and TreeMap which use a red-black tree to keep elements (Set) or kerys (Map) in order.
import scala.collection.immutable.TreeSet
val ts = TreeSet(9,2,4,5,67)                      //> ts  : scala.collection.immutable.TreeSet[Int] = TreeSet(2, 4, 5, 9, 67)
val cs = TreeSet("A","ZZ", "BB", "AA")            //> cs  : scala.collection.immutable.TreeSet[String] = TreeSet(A, AA, BB, ZZ)
import scala.collection.immutable.TreeMap
var tm = TreeMap(3 -> "three", 4 -> "four")       //> tm  : scala.collection.immutable.TreeMap[Int,String] = Map(3 -> three, 4 -
                                                  //| > four)
tm += (2 -> "two")
tm                                                //> res31: scala.collection.immutable.TreeMap[Int,String] = Map(2 -> two, 3 ->
                                                  //|  three, 4 -> four)

// In most cases you can simply leave to the compiler the task to identify the type of collection, but if you want to add a different type you have to say it specifically:
val stuff = mutable.Set[Any](42)                  //> stuff  : scala.collection.mutable.Set[Any] = Set(42)
stuff += "abacadabra"                             //> res32: LearningScala.stuff.type = Set(42, abacadabra)

// Converting a collection to an array o to a list
// Just use toList or toArray for every other collection
stuff.toList                                      //> res33: List[Any] = List(42, abacadabra)
stuff.toArray                                     //> res34: Array[Any] = Array(42, abacadabra)

// Different converting
"""
mkString List(24, 99, 104).mkString(", ") Renders a collection to a Set using the given delimiters.

toBuffer List('f', 't').toBuffer Converts an immutable collection to a mutable one.

toList Map("a" -> 1, "b" -> 2).toList Converts a collection to a List .

toMap Set(1 -> true, 3 -> true).toMap Converts a collection of 2-arity (length) tuples to a Map .

toSet List(2, 5, 5, 3, 2).toSet Converts a collection to a Set .

toString List(2, 5, 5, 3, 2).toString

Renders a collection to a String , including the collection’s type.
"""                                               //> res35: String("\nmkString List(24, 99, 104).mkString(\", \") Renders a col
                                                  //| lection to a Set using the given delimiters.\n\ntoBuffer List(\'f\', \'t\'
                                                  //| ).toBuffer Converts an immutable collection to a mutable one.\n\ntoList Ma
                                                  //| p(\"a\" -> 1, \"b\" -> 2).toList Converts a collection to a List .\n\ntoMa
                                                  //| p Set(1 -> true, 3 -> true).toMap Converts a collection of 2-arity (length
                                                  //| ) tuples to a Map .\n\ntoSet List(2, 5, 5, 3, 2).toSet Converts a collecti
                                                  //| on to a Set .\n\ntoString List(2, 5, 5, 3, 2).toString\n\nRenders a collec
                                                  //| tion to a String , including the collection’s type.\n") = "
                                                  //| mkString List(24, 99, 104).mkString(", ") Renders a collection to a Set us
                                                  //| ing the given delimiters.
                                                  //| 
                                                  //| toBuffer List('f', 't').toBuffer Converts an immutable collection to a mut
                                                  //| able one.
                                                  //| 
                                                  //| toList Map("a" -> 1, "b" -> 2).toList Converts a collection to a List .
                                                  //| 
                                                  //| toMap Set(1 -> true, 3 -> true).toMap Converts a col
                                                  //| Output exceeds cutoff limit.
// Arrays
"""
An Array is a fixed-size, mutable, indexed collection. It’s not officially a collection, be‐
cause it isn’t in the “scala.collections” package and doesn’t extend from the root Itera
ble type (although it has all of the Iterable operations like map and filter ). The Array
type is actually just a wrapper around Java’s array type with an advanced feature called
an implicit class allowing it to be used like a sequence. Scala provides the Array type for
compatibility with JVM libraries and Java code and as a backing store for indexed col‐
lections, which really require an array to be useful.
"""                                               //> res36: String("\nAn Array is a fixed-size, mutable, indexed collection. It
                                                  //| ’s not officially a collection, be‐\ncause it isn’t in the “scala.
                                                  //| collections” package and doesn’t extend from the root Itera\nble type 
                                                  //| (although it has all of the Iterable operations like map and filter ). The
                                                  //|  Array\ntype is actually just a wrapper around Java’s array type with an
                                                  //|  advanced feature called\nan implicit class allowing it to be used like a 
                                                  //| sequence. Scala provides the Array type for\ncompatibility with JVM librar
                                                  //| ies and Java code and as a backing store for indexed col‐\nlections, whi
                                                  //| ch really require an array to be useful.\n") = "
                                                  //| An Array is a fixed-size, mutable, indexed collection. It’s not official
                                                  //| ly a collection, be‐
                                                  //| cause it isn’t in the “scala.collections” package and doesn’t exte
                                                  //| nd from the root Itera
                                                  //| ble type (although it has all of the Iterable operations like map and filt
                                                  //| er ). The Array

val colors = Array("red", "green", "blue")        //> colors  : Array[String] = Array(red, green, blue)
colors(0) = "purple"
println("very purple: " + colors)                 //> very purple: [Ljava.lang.String;@2224df87

"""
Use a zero-based index to replace any item in an Array .
The Scala REPL knows how to print an Array ...
... but not println() , which can only call a type’s toString() method.
"""                                               //> res37: String("\nUse a zero-based index to replace any item in an Array .\
                                                  //| nThe Scala REPL knows how to print an Array ...\n... but not println() , w
                                                  //| hich can only call a type’s toString() method.\n") = "
                                                  //| Use a zero-based index to replace any item in an Array .
                                                  //| The Scala REPL knows how to print an Array ...
                                                  //| ... but not println() , which can only call a type’s toString() method.
                                                  //| "

// Seq and Sequences
"""
Seq is the root type of all sequences, including linked lists like List and indexed (direct-
access) lists like Vector . The Array type, if it were a collection, could be considered an
indexed sequence because its elements are directly accessible without traversal. As a
root type, Seq itself cannot be instantiated, but you can invoke it as a shortcut for creating
a List :
"""                                               //> res38: String("\nSeq is the root type of all sequences, including linked l
                                                  //| ists like List and indexed (direct-\naccess) lists like Vector . The Array
                                                  //|  type, if it were a collection, could be considered an\nindexed sequence b
                                                  //| ecause its elements are directly accessible without traversal. As a\nroot 
                                                  //| type, Seq itself cannot be instantiated, but you can invoke it as a shortc
                                                  //| ut for creating\na List :\n") = "
                                                  //| Seq is the root type of all sequences, including linked lists like List an
                                                  //| d indexed (direct-
                                                  //| access) lists like Vector . The Array type, if it were a collection, could
                                                  //|  be considered an
                                                  //| indexed sequence because its elements are directly accessible without trav
                                                  //| ersal. As a
                                                  //| root type, Seq itself cannot be instantiated, but you can invoke it as a s
                                                  //| hortcut for creating
                                                  //| a List :
                                                  //| "
val inks = Seq('C','M','Y','K')                   //> inks  : Seq[Char] = List(C, M, Y, K)

// The Seq hierarchy of sequence collections appears in the following figure:
// /home/antonio/Dropbox/Data Analysis/Scala/SequenceCollectionsHierarchy.png

"""
Seq - The root of all sequences. Shortcut for List() .

IndexedSeq - The root of indexed sequences. Shortcut for Vector() .

Vector - A list backed by an Array instance for indexed access.

Range - A range of integers. Generates its data on-the-fly.

LinearSeq - The root of linear (linked-list) sequences.

List - A singly linked list of elements.

Queue - A first-in-last-out (FIFO) list.

Stack - A last-in-first-out (LIFO) list.

Stream - A lazy list. Elements are added as they are accessed.

String - A collection of characters.
"""                                               //> res39: String("\nSeq - The root of all sequences. Shortcut for List() .\n\
                                                  //| nIndexedSeq - The root of indexed sequences. Shortcut for Vector() .\n\nVe
                                                  //| ctor - A list backed by an Array instance for indexed access.\n\nRange - A
                                                  //|  range of integers. Generates its data on-the-fly.\n\nLinearSeq - The root
                                                  //|  of linear (linked-list) sequences.\n\nList - A singly linked list of elem
                                                  //| ents.\n\nQueue - A first-in-last-out (FIFO) list.\n\nStack - A last-in-fir
                                                  //| st-out (LIFO) list.\n\nStream - A lazy list. Elements are added as they ar
                                                  //| e accessed.\n\nString - A collection of characters.\n") = "
                                                  //| Seq - The root of all sequences. Shortcut for List() .
                                                  //| 
                                                  //| IndexedSeq - The root of indexed sequences. Shortcut for Vector() .
                                                  //| 
                                                  //| Vector - A list backed by an Array instance for indexed access.
                                                  //| 
                                                  //| Range - A range of integers. Generates its data on-the-fly.
                                                  //| 
                                                  //| LinearSeq - The root of linear (linked-list) sequences.
                                                  //| 
                                                  //| Output exceeds cutoff limit.

// Streams
"""
The Stream type is a lazy collection, generated from one or more starting elements and
a recursive function. Elements are added to the collection only when they are accessed
for the first time, in constrast to other immutable collections that receive 100% of their
contents at instantiation time. The elements that a stream generates are cached for later
retrieval, ensuring that each element is only generated once. Streams can be unbounded,
theoretically infinite collections where elements are only realized upon access. They can
also be terminated with Stream.Empty , a counterpart to List.Nil .
Streams, like lists, are recursive data structures consisting of a head (the current element)
and a tail (the rest of the collection). They can be built with a function that returns a
new stream containing the head element and a recursive invocation of that function to
build the tail. You can use Stream.cons to construct a new stream with the head
and tail.
"""                                               //> res40: String("\nThe Stream type is a lazy collection, generated from one 
                                                  //| or more starting elements and\na recursive function. Elements are added to
                                                  //|  the collection only when they are accessed\nfor the first time, in constr
                                                  //| ast to other immutable collections that receive 100% of their\ncontents at
                                                  //|  instantiation time. The elements that a stream generates are cached for l
                                                  //| ater\nretrieval, ensuring that each element is only generated once. Stream
                                                  //| s can be unbounded,\ntheoretically infinite collections where elements are
                                                  //|  only realized upon access. They can\nalso be terminated with Stream.Empty
                                                  //|  , a counterpart to List.Nil .\nStreams, like lists, are recursive data st
                                                  //| ructures consisting of a head (the current element)\nand a tail (the rest 
                                                  //| of the collection). They can be built with a function that returns a\nnew 
                                                  //| stream containing the head element and a recursive invocation of that func
                                                  //| tion to\nbuild the tai
                                                  //| Output exceeds cutoff limit.
def inc(i: Int): Stream[Int] = Stream.cons(i, inc(i+1))
                                                  //> inc: (i: Int)Stream[Int]
val s = inc(1)                                    //> s  : Stream[Int] = Stream(1, ?)
"""
We have our stream but it only contains our starting value (1) and a promise of future
values (?). Let’s force it to build out the next four elements by “taking” them and re‐
trieving the contents as a list:
"""                                               //> res41: String("\nWe have our stream but it only contains our starting valu
                                                  //| e (1) and a promise of future\nvalues (?). Let’s force it to build out t
                                                  //| he next four elements by “taking” them and re‐\ntrieving the content
                                                  //| s as a list:\n") = "
                                                  //| We have our stream but it only contains our starting value (1) and a promi
                                                  //| se of future
                                                  //| values (?). Let’s force it to build out the next four elements by “tak
                                                  //| ing” them and re‐
                                                  //| trieving the contents as a list:
                                                  //| "
val l = s.take(5).toList                          //> l  : List[Int] = List(1, 2, 3, 4, 5)
s                                                 //> res42: Stream[Int] = Stream(1, 2, 3, 4, 5, ?)
"""
We took the first five elements and retrieved them as a plain old list. Printing out the
original stream instance shows that it now contains five elements and is ready to generate
more. We could follow this up by taking 20, or 200, or 2,000 elements. The stream
contains a recursive function call (specifically, a function value) that it can use to gen‐
erate new elements without end.
"""                                               //> res43: String("\nWe took the first five elements and retrieved them as a p
                                                  //| lain old list. Printing out the\noriginal stream instance shows that it no
                                                  //| w contains five elements and is ready to generate\nmore. We could follow t
                                                  //| his up by taking 20, or 200, or 2,000 elements. The stream\ncontains a rec
                                                  //| ursive function call (specifically, a function value) that it can use to g
                                                  //| en‐\nerate new elements without end.\n") = "
                                                  //| We took the first five elements and retrieved them as a plain old list. Pr
                                                  //| inting out the
                                                  //| original stream instance shows that it now contains five elements and is r
                                                  //| eady to generate
                                                  //| more. We could follow this up by taking 20, or 200, or 2,000 elements. The
                                                  //|  stream
                                                  //| contains a recursive function call (specifically, a function value) that i
                                                  //| t can use to gen‐
                                                  //| erate new elements without end.
                                                  //| "


// MODANIC COLLECTIONS
"""
The last set of collections we’ll discuss are the monadic ones, which
support transformative operations like the ones in Iterable but can contain no more
than one element. The term “monadic” applies in its Greek origins to mean a single
unit, and in the category theory sense of a single link in a chain of operations.
"""                                               //> res44: String("\nThe last set of collections we’ll discuss are the monad
                                                  //| ic ones, which\nsupport transformative operations like the ones in Iterabl
                                                  //| e but can contain no more\nthan one element. The term “monadic” applie
                                                  //| s in its Greek origins to mean a single\nunit, and in the category theory 
                                                  //| sense of a single link in a chain of operations.\n") = "
                                                  //| The last set of collections we’ll discuss are the monadic ones, which
                                                  //| support transformative operations like the ones in Iterable but can contai
                                                  //| n no more
                                                  //| than one element. The term “monadic” applies in its Greek origins to m
                                                  //| ean a single
                                                  //| unit, and in the category theory sense of a single link in a chain of oper
                                                  //| ations.
                                                  //| "

// Option Collections
"""
As a collection whose size will never be larger than one, the Option type represents the
presence or absence of a single value. This potentially missing value (e.g., it was never
initialized, or could not be calculated) can thus be wrapped in an Option collection and
have its potential absence clearly advertised.
Some developers see Option as a safe replacement for null values, notifying users that
the value may be missing and reducing the likelihood that its use will trigger a Null
PointerException. Others see it as a safer way to build chains of operations, ensuring
that only valid values will persist for the duration of the chain.
The Option type is itself unimplemented but relies on two subtypes for the implemen‐
tation: Some, a type-parameterized collection of one element; and None, an empty col‐
lection. The None type has no type parameters because it never contains contents. You
can work with these types directly, or invoke Option() to detect null values and let it
choose the appropriate subtype.
"""                                               //> res45: String("\nAs a collection whose size will never be larger than one,
                                                  //|  the Option type represents the\npresence or absence of a single value. Th
                                                  //| is potentially missing value (e.g., it was never\ninitialized, or could no
                                                  //| t be calculated) can thus be wrapped in an Option collection and\nhave its
                                                  //|  potential absence clearly advertised.\nSome developers see Option as a sa
                                                  //| fe replacement for null values, notifying users that\nthe value may be mis
                                                  //| sing and reducing the likelihood that its use will trigger a Null\nPointer
                                                  //| Exception. Others see it as a safer way to build chains of operations, ens
                                                  //| uring\nthat only valid values will persist for the duration of the chain.\
                                                  //| nThe Option type is itself unimplemented but relies on two subtypes for th
                                                  //| e implemen‐\ntation: Some, a type-parameterized collection of one elemen
                                                  //| t; and None, an empty col‐\nlection. The None type has no type parameter
                                                  //| s because it never contain
                                                  //| Output exceeds cutoff limit.
var p: String = "Indeed"                          //> p  : String = Indeed
var z = Option(p)                                 //> z  : Option[String] = Some(Indeed)
var q = null                                      //> q  : Null = null
var r = Option(q)                                 //> r  : Option[Null] = None
"""
You can use isDefined and isEmpty to check if a given Option is Some or None , respec‐
tively:
"""                                               //> res46: String("\nYou can use isDefined and isEmpty to check if a given Opt
                                                  //| ion is Some or None , respec‐\ntively:\n") = "
                                                  //| You can use isDefined and isEmpty to check if a given Option is Some or No
                                                  //| ne , respec‐
                                                  //| tively:
                                                  //| "
println(s"z is defined? ${z.isDefined}")          //> z is defined? true
println(s"q is not defined? ${r.isEmpty}")        //> q is not defined? true

def divide(amt: Double, divisor: Double): Option[Double] = {
if (divisor == 0) None
else Option(amt / divisor)
}                                                 //> divide: (amt: Double, divisor: Double)Option[Double]
val legit = divide(5, 2)                          //> legit  : Option[Double] = Some(2.5)
val illegit = divide(3, 0)                        //> illegit  : Option[Double] = None

"""
The return type is the Option with a Double type parameter, ensuring that valid
results will retain the correct type.
This will return a Some wrapper because the dividend will be a nonnull value.
With a valid divisor, our dividend comes wrapped in a Some .
With an invalid divisor we get None , the absence of a value.

The Option collection provides a safe mechanism and operations for storing and trans‐
forming values that may or may not be present. You shouldn’t be surprised that it also
provides safe operations to extract its potential value.
For the curious, there is also an unsafe extraction operation, the get() method. If you
call this for an Option that is actually a Some instance, you will successfully receive the
value it contains. However, if you call get() on an instance of None , a “no such element”
error will be triggered.
We will focus on the safe operations for extracting Option values. The core strategy of
these operations is to provide a framework for handling missing values, such as a re‐
placement (aka “default”) value to use instead of the missing one, or a function that can
either generate a replacement or raise an error condition.

fold - nextOption.fold(-1)(x => x) - Returns the value from the given function for Some
(in this case, based on the embedded value) or else the
starting value. The foldLeft , foldRight , and
reduceXXX methods are also available for reducing
an Option down to its embedded value or else a
computed value.

getOrElse - nextOption getOrElse 5 or nextOption getOrElse {
println("error!"); -1 } - Returns the value for Some or else the result of a by-
name parameter for None.

orElse nextOption orElse nextOption Doesn’t actually extract the value, but attempts to fill
in a value for None. Returns this Option if it is
nonempty, otherwise returns an Option from the
given by-name parameter.

Match expressions nextOption match { case Some(x)
=> x; case None => -1 }
Use a match expression to handle the value if present.
The Some(x) expression extracts its data into the
named value “x”, which can be used as the return
value of the match expression or reused for further
transformation.
"""                                               //> res47: String("\nThe return type is the Option with a Double type paramete
                                                  //| r, ensuring that valid\nresults will retain the correct type.\nThis will r
                                                  //| eturn a Some wrapper because the dividend will be a nonnull value.\nWith a
                                                  //|  valid divisor, our dividend comes wrapped in a Some .\nWith an invalid di
                                                  //| visor we get None , the absence of a value.\n\nThe Option collection provi
                                                  //| des a safe mechanism and operations for storing and trans‐\nforming valu
                                                  //| es that may or may not be present. You shouldn’t be surprised that it al
                                                  //| so\nprovides safe operations to extract its potential value.\nFor the curi
                                                  //| ous, there is also an unsafe extraction operation, the get() method. If yo
                                                  //| u\ncall this for an Option that is actually a Some instance, you will succ
                                                  //| essfully receive the\nvalue it contains. However, if you call get() on an 
                                                  //| instance of None , a “no such element”\nerror will be triggered.\nWe w
                                                  //| ill focus on the safe operatio
                                                  //| Output exceeds cutoff limit.


}