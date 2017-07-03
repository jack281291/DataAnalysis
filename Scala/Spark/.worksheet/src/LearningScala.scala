///////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////// SCALA FOUNDAMENTALS ///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////




object LearningScala {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(453); 
  // VALUES are immutable constants. You can't change them once defined.
 val hello: String = "Hello!";System.out.println("""hello  : String = """ + $show(hello ));$skip(16); 
 println(hello);$skip(170); 

 // Notice how Scala defines things backwards from other languages - you declare the
 // name, then the type.

 // VARIABLES are mutable
 var helloThere: String = hello;System.out.println("""helloThere  : String = """ + $show(helloThere ));$skip(32); 
 helloThere = hello + " There!";$skip(21); 
 println(helloThere);$skip(294); 


 // One key objective of functional programming is to use immutable objects as often as possible.
 // Try to use operations that transform immutable objects into a new immutable object.
 // For example, we could have done the same thing like this:
 val immutableHelloThere = hello + "There!";System.out.println("""immutableHelloThere  : String = """ + $show(immutableHelloThere ));$skip(30); 
 println(immutableHelloThere);$skip(47); 

 // Some other types
 val numberOne : Int = 1;System.out.println("""numberOne  : Int = """ + $show(numberOne ));$skip(28); 
 val truth : Boolean = true;System.out.println("""truth  : Boolean = """ + $show(truth ));$skip(26); 
 val letterA : Char = 'a';System.out.println("""letterA  : Char = """ + $show(letterA ));$skip(30); 
 val pi : Double = 3.14159265;System.out.println("""pi  : Double = """ + $show(pi ));$skip(45); 
 val piSinglePrecision : Float = 3.14159265f;System.out.println("""piSinglePrecision  : Float = """ + $show(piSinglePrecision ));$skip(36); 
 val bigNumber : Long = 1234567890l;System.out.println("""bigNumber  : Long = """ + $show(bigNumber ));$skip(30); 
 val smallNumber : Byte = 127;System.out.println("""smallNumber  : Byte = """ + $show(smallNumber ));$skip(136); 

 // String printing tricks
 // Concatenating stuff with +:
 println("Here is a mess: " + numberOne + truth + letterA + pi + bigNumber);$skip(68); 


 // printf style:
 println(f"Pi is about $piSinglePrecision%.3f");$skip(54); 
 println(f"Zero padding on the left: $numberOne%05d");$skip(118); 


 // Substituting in variables:
 println(s"I can use the s prefix to use variables like $numberOne $truth $letterA");$skip(149); 

 // Substituting expressions (with curly brackets):
 println(s"The s prefix isn't limited to variables; I can include any expression. Like ${1+2}");$skip(113); 


 // Using regular expressions:
 val theUltimateAnswer: String = "To life, the universe, and everything is 42.";System.out.println("""theUltimateAnswer  : String = """ + $show(theUltimateAnswer ));$skip(38); 


 val pattern = """.* ([\d]+).*""".r;System.out.println("""pattern  : scala.util.matching.Regex = """ + $show(pattern ));$skip(47); 
 val pattern(answerString) = theUltimateAnswer;System.out.println("""answerString  : String = """ + $show(answerString ));$skip(33); 
 val answer = answerString.toInt;System.out.println("""answer  : Int = """ + $show(answer ));$skip(17); 
 println(answer);$skip(50); 

 // Dealing with booleans
 val isGreater = 1 > 2;System.out.println("""isGreater  : Boolean = """ + $show(isGreater ));$skip(22); 
 val isLesser = 1 < 2;System.out.println("""isLesser  : Boolean = """ + $show(isLesser ));$skip(39); 
 val impossible = isGreater & isLesser;System.out.println("""impossible  : Boolean = """ + $show(impossible ));$skip(40); 
 val anotherWay = isGreater && isLesser;System.out.println("""anotherWay  : Boolean = """ + $show(anotherWay ));$skip(32); 

 val picard: String = "Picard";System.out.println("""picard  : String = """ + $show(picard ));$skip(36); 
 val bestCaptain: String = "Picard";System.out.println("""bestCaptain  : String = """ + $show(bestCaptain ));$skip(45); 
 val isBest: Boolean = picard == bestCaptain;System.out.println("""isBest  : Boolean = """ + $show(isBest ));$skip(195); 

 // EXERCISE
 // Write some code that takes the value of pi, doubles it, and then prints it within a string with
 // three decimal places of precision to the right.
 val doublePi: Double = 2*pi;System.out.println("""doublePi  : Double = """ + $show(doublePi ));$skip(45); 
 println(f"This is doublePi: $doublePi%.3f");$skip(357); 
 
 ///////////////////////////////////////////////////////////////////////////////////
 //////////////////////////////////// FLOW CONTROL /////////////////////////////////
 ///////////////////////////////////////////////////////////////////////////////////
  
  // If / else syntax
  if (1 > 3) println("Impossible!") else println("The world makes sense.");$skip(32); 
  val max = if (3 > 4) 3 else 4;System.out.println("""max  : Int = """ + $show(max ));$skip(93); 
  if (1 > 3) {
  	println("Impossible!")
  } else {
  	println("The world makes sense.")
  };$skip(68); 
  
  // Matching - like switch in other languages:
  val number = 3;System.out.println("""number  : Int = """ + $show(number ));$skip(146); 
  number match {
  	case 1 => println("One")
  	case 2 => println("Two")
  	case 3 => println("Three")
  	case _ => println("Something else")
 	};$skip(87); 
 	
 	// For loops
 	for (x <- 1 to 4) {
 		val squared = x * x
 		println(squared)
 	};$skip(81); 
                                                  
  // While loops
  var x = 10;System.out.println("""x  : Int = """ + $show(x ));$skip(47); 
  while (x >= 0) {
  	println(x)
  	x -= 1
  };$skip(59); 
                                                  
  x = 0;$skip(42); 
  do { println(x); x+=1 } while (x <= 10);$skip(152); val res$0 = 
                                                  
   // Expressions
   // "Returns" the final value in a block automatically
   
	{val x = 10; x + 20};System.out.println("""res0: Int = """ + $show(res$0));$skip(80); 
                                                
	println({val x = 10; x + 20});$skip(265); 
	 
	 // EXERCISE
	 // Write some code that prints out the first 10 values of the Fibonacci sequence.
	 // This is the sequence where every number is the sum of the two numbers before it.
	 // So, the result should be 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
	var a: Int = 0;System.out.println("""a  : Int = """ + $show(a ));$skip(16); 
	var b: Int = 1;System.out.println("""b  : Int = """ + $show(b ));$skip(76); 
	for (i <- 1 to 10) {
		var c: Int = a + b
		a = b
		b = c
		println(c)
		};$skip(448); 
	/////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////// FUNCTIONS //////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////
  
  // Format is def <function name>(parameter name: type...) : return type = { expression }
  // Don't forget the = before the expression!
  def squareIt(x: Int) : Int = {
  	x * x
  };System.out.println("""squareIt: (x: Int)Int""");$skip(43); 
  
  def cubeIt(x: Int): Int = {x * x * x};System.out.println("""cubeIt: (x: Int)Int""");$skip(26); 
  
  println(squareIt(2));$skip(24); 
  
  println(cubeIt(2));$skip(124); 
  
  // Functions can take other functions as parameters
  
  def transformInt(x: Int, f: Int => Int) : Int = {
  	f(x)
  };System.out.println("""transformInt: (x: Int, f: Int => Int)Int""");$skip(42); 
  
  val result = transformInt(2, cubeIt);System.out.println("""result  : Int = """ + $show(result ));$skip(19); 
  println (result);$skip(209); val res$1 = 
  
  // "Lambda functions", "anonymous functions", "function literals"
  // You can declare functions inline without even giving them a name
  // This happens a lot in Spark.
  transformInt(3, x => x * x * x);System.out.println("""res1: Int = """ + $show(res$1));$skip(34); val res$2 = 
  
  transformInt(10, x => x / 2);System.out.println("""res2: Int = """ + $show(res$2));$skip(49); val res$3 = 
  
	transformInt(2, x => {val y = x * 2; y * y});System.out.println("""res3: Int = """ + $show(res$3));$skip(419); 
  
  // This is really important!
  
  // EXERCISE
  // Strings have a built-in .toUpperCase method. For example, "foo".toUpperCase gives you back FOO.
  // Write a function that converts a string to upper-case, and use that function of a few test strings.
  // Then, do the same thing using a function literal instead of a separate, named function.
  def convertToUpperCase(x: String): String = {
  	x.toUpperCase
  };System.out.println("""convertToUpperCase: (x: String)String""");$skip(28); val res$4 = 
  convertToUpperCase("Lol");System.out.println("""res4: String = """ + $show(res$4));$skip(84); 
  def applyFunctionToString(x: String, f: String => String): String = {
  	f(x)
  };System.out.println("""applyFunctionToString: (x: String, f: String => String)String""");$skip(51); val res$5 = 
  applyFunctionToString("Lol", x => x.toUpperCase);System.out.println("""res5: String = """ + $show(res$5));$skip(512); 
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////// DATA STRUCTURES ///////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////
  
  // Tuples (Also really common with Spark!!)
  // Immutable lists
  // Often thought of as database fields, or columns.
  // Useful for passing around entire rows of data.
  
  val captainStuff = ("Picard", "Enterprise-D", "NCC-1701-D");System.out.println("""captainStuff  : (String, String, String) = """ + $show(captainStuff ));$skip(24); 
  println(captainStuff);$skip(94); 
  
  // You refer to individual fields with their ONE-BASED index:
  println(captainStuff._1);$skip(27); 
  println(captainStuff._2);$skip(27); 
  println(captainStuff._3);$skip(92); 
 
 // You can create a key/value pair with ->
 val picardsShip = "Picard" -> "Enterprise-D";System.out.println("""picardsShip  : (String, String) = """ + $show(picardsShip ));$skip(25); 
 println(picardsShip._2);$skip(87); 
 
 // You can mix different types in a tuple
 val aBunchOfStuff = ("Kirk", 1964, true);System.out.println("""aBunchOfStuff  : (String, Int, Boolean) = """ + $show(aBunchOfStuff ));$skip(275); 
 
 
 
 // Lists
 // Like a tuple, but it's an actual Collection object that has more functionality.
 // Also, it cannot hold items of different types.
 // It's a singly-linked list under the hood.
 
 val shipList = List("Enterprise", "Defiant", "Voyager", "Deep Space Nine");System.out.println("""shipList  : List[String] = """ + $show(shipList ));$skip(35); 
 val empty: List[Nothing] = List();System.out.println("""empty  : List[Nothing] = """ + $show(empty ));$skip(443); 

// The list type is covariant, this means that for each pair of types S and T, if S is a subtype of T then List[S] is a subtype of List[T].
// Note that the empty list has type List[Nothing], but Nothing is at the bottom of the Scala hierarchy, so List[Nothing] is a subtype of List[T] for every T.
// So the empty list object can also be seen as an object of every other list type of form List[T], for example:
var xs: List[String] = List();System.out.println("""xs  : List[String] = """ + $show(xs ));$skip(103); 


 
 // Access individual members using () with ZERO-BASED index (confused yet?)
 println(shipList(1));$skip(93); 
 
 // head and tail give you the first item, and the remaining ones.
 println(shipList.head);$skip(24); 
 println(shipList.tail);$skip(72); 
 
 
 // Iterating though a list
 for (ship <- shipList) {println(ship)};$skip(187); 
 
 // Let's apply a function literal to a list! map() can be used to apply any function to every item in a collection.
val backwardShips = shipList.map( (ship: String) => {ship.reverse});System.out.println("""backwardShips  : List[String] = """ + $show(backwardShips ));$skip(45); 
 for (ship <- backwardShips) {println(ship)};$skip(183); 
                                                  
// reduce() can be used to combine together all the items in a collection using some function.
val numberList = List(1, 2, 3, 4, 5);System.out.println("""numberList  : List[Int] = """ + $show(numberList ));$skip(56); 
val sum = numberList.reduce( (x: Int, y: Int) => x + y);System.out.println("""sum  : Int = """ + $show(sum ));$skip(13); 
println(sum);$skip(158); 

// filter() can remove stuff you don't want. Here we'll introduce wildcard syntax while we're at it.
val iHateFives = numberList.filter( (x: Int) => x != 5);System.out.println("""iHateFives  : List[Int] = """ + $show(iHateFives ));$skip(44); 
val iHateThrees = numberList.filter(_ != 3);System.out.println("""iHateThrees  : List[Int] = """ + $show(iHateThrees ));$skip(229); 

// Note that Spark has its own map, reduce, and filter functions that can distribute these operations. But they work the same way!
// Also, you understand MapReduce now :)

// Concatenating lists
val moreNumbers = List(6, 7, 8);System.out.println("""moreNumbers  : List[Int] = """ + $show(moreNumbers ));$skip(46); 
val lotsOfNumbers = numberList ++ moreNumbers;System.out.println("""lotsOfNumbers  : List[Int] = """ + $show(lotsOfNumbers ));$skip(52); 

// More list fun
val reversed = numberList.reverse;System.out.println("""reversed  : List[Int] = """ + $show(reversed ));$skip(29); 
val sorted = reversed.sorted;System.out.println("""sorted  : List[Int] = """ + $show(sorted ));$skip(48); 
val lotsOfDuplicates = numberList ++ numberList;System.out.println("""lotsOfDuplicates  : List[Int] = """ + $show(lotsOfDuplicates ));$skip(47); 
val distinctValues = lotsOfDuplicates.distinct;System.out.println("""distinctValues  : List[Int] = """ + $show(distinctValues ));$skip(30); 
val maxValue = numberList.max;System.out.println("""maxValue  : Int = """ + $show(maxValue ));$skip(27); 
val total = numberList.sum;System.out.println("""total  : Int = """ + $show(total ));$skip(39); 
val hasThree = iHateThrees.contains(3);System.out.println("""hasThree  : Boolean = """ + $show(hasThree ));$skip(45); 

// Nil is the null list:
val nullList = Nil;System.out.println("""nullList  : scala.collection.immutable.Nil.type = """ + $show(nullList ));$skip(23); 
val OneList = 1 :: Nil;System.out.println("""OneList  : List[Int] = """ + $show(OneList ));$skip(17); val res$6 = 
nullList.isEmpty;System.out.println("""res6: Boolean = """ + $show(res$6));$skip(16); val res$7 = 
OneList.isEmpty;System.out.println("""res7: Boolean = """ + $show(res$7));$skip(33); 
val RealTotal = total :: OneList;System.out.println("""RealTotal  : List[Int] = """ + $show(RealTotal ));$skip(72); 

// Zipping List
val zipped = List("a","b","c","d","e") zip List(1,2,3);System.out.println("""zipped  : List[(String, Int)] = """ + $show(zipped ));$skip(39); val res$8 = 
List("a","b","c","d","e").zipWithIndex;System.out.println("""res8: List[(String, Int)] = """ + $show(res$8));$skip(13); val res$9 = 
zipped.unzip;System.out.println("""res9: (List[String], List[Int]) = """ + $show(res$9));$skip(59); val res$10 = 

//Higher order methods on class List
List(1,2,3) map(_+1);System.out.println("""res10: List[Int] = """ + $show(res$10));$skip(21); val res$11 = 
List(1,2,3).map(_+1);System.out.println("""res11: List[Int] = """ + $show(res$11));$skip(68); val res$12 = 
List.range(1, 5).flatMap (
	i => List.range(1,i).map(j => (i,j))
	);System.out.println("""res12: List[(Int, Int)] = """ + $show(res$12));$skip(29); 
List(1,2,3).foreach(println);$skip(24); val res$13 = 
List(1,23).filter(_!=1);System.out.println("""res13: List[Int] = """ + $show(res$13));$skip(131); val res$14 = 

// The find method is similar to filter but it returns the first element satisfying the given condition.
List(1,5,10).find(_ > 3);System.out.println("""res14: Option[Int] = """ + $show(res$14));$skip(87); 
// If there's returns Some(element) otherwise None
val range_1_10 = List.range(1,10,3);System.out.println("""range_1_10  : List[Int] = """ + $show(range_1_10 ));$skip(39); 
val tab = List.tabulate(5)(x => x * x);System.out.println("""tab  : List[Int] = """ + $show(tab ));$skip(83); val res$15 = 

// Processing multiple lists together
(List(1,3), List(2,5,10)).zipped.map(_ + _);System.out.println("""res15: List[Int] = """ + $show(res$15));$skip(224); 


// Maps
// Useful for key/value lookups on distinct keys
// Like dictionaries in other languages

val shipMap = Map("Kirk" -> "Enterprise", "Picard" -> "Enterprise-D", "Sisko" -> "Deep Space Nine", "Janeway" -> "Voyager");System.out.println("""shipMap  : scala.collection.immutable.Map[String,String] = """ + $show(shipMap ));$skip(28); 
println(shipMap("Janeway"));$skip(66); 

// Dealing with missing keys
println(shipMap.contains("Archer"));$skip(67); 

val archersShip = util.Try(shipMap("Archer")) getOrElse "Unknown";System.out.println("""archersShip  : String = """ + $show(archersShip ));$skip(21); 
println(archersShip);$skip(74); 

// Set, for default it is immutable
var jetSet = Set("Boeing", "Airbus");System.out.println("""jetSet  : scala.collection.immutable.Set[String] = """ + $show(jetSet ));$skip(17); 
jetSet += "Lear";$skip(35); 
println(jetSet.contains("Cessna"));$skip(16); 
println(jetSet)
// The Set is immutable the command += simply create a new jetSet object with the three items, if we want the mutable one we have to make an import:
import scala.collection.mutable.Set;$skip(228); 
val movieSet = Set("Hitch", "Poltergeist");System.out.println("""movieSet  : scala.collection.mutable.Set[String] = """ + $show(movieSet ));$skip(20); val res$16 = 
movieSet += "Shrek";System.out.println("""res16: LearningScala.movieSet.type = """ + $show(res$16));$skip(18); 
println(movieSet)


// Class
class Accumulator {
	private val b:Int = 5
	val a = 10
	def add(x: Int): Int = {a + x*b}
};$skip(185); 
// Private val cannot be accessed from outside the class
var acc = new Accumulator;System.out.println("""acc  : LearningScala.Accumulator = """ + $show(acc ));$skip(6); val res$17 = 
acc.a;System.out.println("""res17: Int = """ + $show(res$17));$skip(20); val res$18 = 
//acc.b
acc.add(10)

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
	};System.out.println("""res18: Int = """ + $show(res$18));$skip(1053); 
val d = new Division(1.0,2.0);System.out.println("""d  : LearningScala.Division = """ + $show(d ));$skip(4); val res$19 = 
d.c;System.out.println("""res19: Double = """ + $show(res$19));$skip(277); 


// Arrays

// Arrays allow you to hold a sequence of elements and efficiently access an element at an arbitrary position, both to get or update the element with a zero-based index.
val FiveInts = new Array[Int](5);System.out.println("""FiveInts  : Array[Int] = """ + $show(FiveInts ));$skip(33);  // arrays that you know the type and the length nothing more
val fiveToOne = Array(5,4,3,2,1);System.out.println("""fiveToOne  : Array[Int] = """ + $show(fiveToOne ));$skip(62); 
FiveInts(0) = fiveToOne(4);$skip(22);  // accessing and updating an array
FiveInts.update(1, 5);$skip(9); val res$20 = 
FiveInts;System.out.println("""res20: Array[Int] = """ + $show(res$20));$skip(455); val res$21 = 

// List buffers

// Class List provides fast access to the head of the list but not the end. Thus when you need to build a list by appending to the end, you should consider building the list backwards and then calling reverse on the list.
List(1,2,3) ::: List(4);System.out.println("""res21: List[Int] = """ + $show(res$21));$skip(27); val res$22 =                           // Note that this operation has a complexity of O(n). If you need this operation frequently, or for long lists, consider using another data type (e.g. a ListBuffer).
(5 :: List(1,2,3)).reverse

// Another alteLIST BUFFERSrnative is to use ListBuffer, that is a mutable object which can help to build lists more efficiently when there's the need to append elements.
import scala.collection.mutable.ListBuffer;System.out.println("""res22: List[Int] = """ + $show(res$22));$skip(245); 
val buf = new ListBuffer[Int];System.out.println("""buf  : scala.collection.mutable.ListBuffer[Int] = """ + $show(buf ));$skip(9); val res$23 = 
buf += 1;System.out.println("""res23: LearningScala.buf.type = """ + $show(res$23));$skip(9); val res$24 = 
buf += 2;System.out.println("""res24: LearningScala.buf.type = """ + $show(res$24));$skip(4); val res$25 = 
buf;System.out.println("""res25: scala.collection.mutable.ListBuffer[Int] = """ + $show(res$25));$skip(10); val res$26 = 
3 +=: buf;System.out.println("""res26: LearningScala.buf.type = """ + $show(res$26));$skip(11); val res$27 = 
buf.toList

// Array buffers

// An ArrayBuffer is like an Array, except that you can additionally add and remove elements from the beginning and the ending of the sequence. All Array operations are available but they are slower.

import scala.collection.mutable.ArrayBuffer;System.out.println("""res27: List[Int] = """ + $show(res$27));$skip(298); 
val abuf = new ArrayBuffer[Int]();System.out.println("""abuf  : scala.collection.mutable.ArrayBuffer[Int] = """ + $show(abuf ));$skip(11); val res$28 = 
abuf += 12;System.out.println("""res28: LearningScala.abuf.type = """ + $show(res$28));$skip(12); val res$29 = 
abuf.length;System.out.println("""res29: Int = """ + $show(res$29));$skip(11); val res$30 = 
3 +=: abuf

// Sets and Maps

// For default sets and maps are immutable, if you want the mutable variant you have to explicit import it:
import scala.collection.mutable;System.out.println("""res30: LearningScala.abuf.type = """ + $show(res$30));$skip(192); 
val mutaSet = mutable.Set(1,2,3);System.out.println("""mutaSet  : scala.collection.mutable.Set[Int] = """ + $show(mutaSet ));$skip(48); 
val mutableMap = mutable.Map.empty[String, Int];System.out.println("""mutableMap  : scala.collection.mutable.Map[String,Int] = """ + $show(mutableMap ));$skip(24); 
mutableMap("hello") = 1;$skip(22); 
mutableMap("lol") = 2

// Sorted sets and maps

// On occasion you may need a set or a map whose iterator returns elements in a particular order. So there are SortedSet and SortedMap.These traits are implemented by classes TreeSet and TreeMap which use a red-black tree to keep elements (Set) or kerys (Map) in order.
import scala.collection.immutable.TreeSet;$skip(367); 
val ts = TreeSet(9,2,4,5,67);System.out.println("""ts  : scala.collection.immutable.TreeSet[Int] = """ + $show(ts ));$skip(39); 
val cs = TreeSet("A","ZZ", "BB", "AA")
import scala.collection.immutable.TreeMap;System.out.println("""cs  : scala.collection.immutable.TreeSet[String] = """ + $show(cs ));$skip(86); 
var tm = TreeMap(3 -> "three", 4 -> "four");System.out.println("""tm  : scala.collection.immutable.TreeMap[Int,String] = """ + $show(tm ));$skip(19); 
tm += (2 -> "two");$skip(3); val res$31 = 
tm;System.out.println("""res31: scala.collection.immutable.TreeMap[Int,String] = """ + $show(res$31));$skip(206); 

// In most cases you can simply leave to the compiler the task to identify the type of collection, but if you want to add a different type you have to say it specifically:
val stuff = mutable.Set[Any](42);System.out.println("""stuff  : scala.collection.mutable.Set[Any] = """ + $show(stuff ));$skip(22); val res$32 = 
stuff += "abacadabra";System.out.println("""res32: LearningScala.stuff.type = """ + $show(res$32));$skip(122); val res$33 = 

// Converting a collection to an array o to a list
// Just use toList or toArray for every other collection
stuff.toList;System.out.println("""res33: List[Any] = """ + $show(res$33));$skip(14); val res$34 = 
stuff.toArray;System.out.println("""res34: Array[Any] = """ + $show(res$34));$skip(564); val res$35 = 

// Different converting
"""
mkString List(24, 99, 104).mkString(", ") Renders a collection to a Set using the given delimiters.

toBuffer List('f', 't').toBuffer Converts an immutable collection to a mutable one.

toList Map("a" -> 1, "b" -> 2).toList Converts a collection to a List .

toMap Set(1 -> true, 3 -> true).toMap Converts a collection of 2-arity (length) tuples to a Map .

toSet List(2, 5, 5, 3, 2).toSet Converts a collection to a Set .

toString List(2, 5, 5, 3, 2).toString

Renders a collection to a String , including the collection’s type.
""";System.out.println("""res35: String("\nmkString List(24, 99, 104).mkString(\", \") Renders a collection to a Set using the given delimiters.\n\ntoBuffer List(\'f\', \'t\').toBuffer Converts an immutable collection to a mutable one.\n\ntoList Map(\"a\" -> 1, \"b\" -> 2).toList Converts a collection to a List .\n\ntoMap Set(1 -> true, 3 -> true).toMap Converts a collection of 2-arity (length) tuples to a Map .\n\ntoSet List(2, 5, 5, 3, 2).toSet Converts a collection to a Set .\n\ntoString List(2, 5, 5, 3, 2).toString\n\nRenders a collection to a String , including the collection’s type.\n") = """ + $show(res$35));$skip(612); val res$36 = 
// Arrays
"""
An Array is a fixed-size, mutable, indexed collection. It’s not officially a collection, be‐
cause it isn’t in the “scala.collections” package and doesn’t extend from the root Itera
ble type (although it has all of the Iterable operations like map and filter ). The Array
type is actually just a wrapper around Java’s array type with an advanced feature called
an implicit class allowing it to be used like a sequence. Scala provides the Array type for
compatibility with JVM libraries and Java code and as a backing store for indexed col‐
lections, which really require an array to be useful.
""";System.out.println("""res36: String("\nAn Array is a fixed-size, mutable, indexed collection. It’s not officially a collection, be‐\ncause it isn’t in the “scala.collections” package and doesn’t extend from the root Itera\nble type (although it has all of the Iterable operations like map and filter ). The Array\ntype is actually just a wrapper around Java’s array type with an advanced feature called\nan implicit class allowing it to be used like a sequence. Scala provides the Array type for\ncompatibility with JVM libraries and Java code and as a backing store for indexed col‐\nlections, which really require an array to be useful.\n") = """ + $show(res$36));$skip(44); 

val colors = Array("red", "green", "blue");System.out.println("""colors  : Array[String] = """ + $show(colors ));$skip(21); 
colors(0) = "purple";$skip(34); 
println("very purple: " + colors);$skip(185); val res$37 = 

"""
Use a zero-based index to replace any item in an Array .
The Scala REPL knows how to print an Array ...
... but not println() , which can only call a type’s toString() method.
""";System.out.println("""res37: String("\nUse a zero-based index to replace any item in an Array .\nThe Scala REPL knows how to print an Array ...\n... but not println() , which can only call a type’s toString() method.\n") = """ + $show(res$37));$skip(405); val res$38 = 

// Seq and Sequences
"""
Seq is the root type of all sequences, including linked lists like List and indexed (direct-
access) lists like Vector . The Array type, if it were a collection, could be considered an
indexed sequence because its elements are directly accessible without traversal. As a
root type, Seq itself cannot be instantiated, but you can invoke it as a shortcut for creating
a List :
""";System.out.println("""res38: String("\nSeq is the root type of all sequences, including linked lists like List and indexed (direct-\naccess) lists like Vector . The Array type, if it were a collection, could be considered an\nindexed sequence because its elements are directly accessible without traversal. As a\nroot type, Seq itself cannot be instantiated, but you can invoke it as a shortcut for creating\na List :\n") = """ + $show(res$38));$skip(32); 
val inks = Seq('C','M','Y','K');System.out.println("""inks  : Seq[Char] = """ + $show(inks ));$skip(701); val res$39 = 

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
""";System.out.println("""res39: String("\nSeq - The root of all sequences. Shortcut for List() .\n\nIndexedSeq - The root of indexed sequences. Shortcut for Vector() .\n\nVector - A list backed by an Array instance for indexed access.\n\nRange - A range of integers. Generates its data on-the-fly.\n\nLinearSeq - The root of linear (linked-list) sequences.\n\nList - A singly linked list of elements.\n\nQueue - A first-in-last-out (FIFO) list.\n\nStack - A last-in-first-out (LIFO) list.\n\nStream - A lazy list. Elements are added as they are accessed.\n\nString - A collection of characters.\n") = """ + $show(res$39));$skip(980); val res$40 = 

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
""";System.out.println("""res40: String("\nThe Stream type is a lazy collection, generated from one or more starting elements and\na recursive function. Elements are added to the collection only when they are accessed\nfor the first time, in constrast to other immutable collections that receive 100% of their\ncontents at instantiation time. The elements that a stream generates are cached for later\nretrieval, ensuring that each element is only generated once. Streams can be unbounded,\ntheoretically infinite collections where elements are only realized upon access. They can\nalso be terminated with Stream.Empty , a counterpart to List.Nil .\nStreams, like lists, are recursive data structures consisting of a head (the current element)\nand a tail (the rest of the collection). They can be built with a function that returns a\nnew stream containing the head element and a recursive invocation of that function to\nbuild the tail. You can use Stream.cons to construct a new stream with the head\nand tail.\n") = """ + $show(res$40));$skip(56); 
def inc(i: Int): Stream[Int] = Stream.cons(i, inc(i+1));System.out.println("""inc: (i: Int)Stream[Int]""");$skip(15); 
val s = inc(1);System.out.println("""s  : Stream[Int] = """ + $show(s ));$skip(216); val res$41 = 
"""
We have our stream but it only contains our starting value (1) and a promise of future
values (?). Let’s force it to build out the next four elements by “taking” them and re‐
trieving the contents as a list:
""";System.out.println("""res41: String("\nWe have our stream but it only contains our starting value (1) and a promise of future\nvalues (?). Let’s force it to build out the next four elements by “taking” them and re‐\ntrieving the contents as a list:\n") = """ + $show(res$41));$skip(25); 
val l = s.take(5).toList;System.out.println("""l  : List[Int] = """ + $show(l ));$skip(2); val res$42 = 
s;System.out.println("""res42: Stream[Int] = """ + $show(res$42));$skip(394); val res$43 = 
"""
We took the first five elements and retrieved them as a plain old list. Printing out the
original stream instance shows that it now contains five elements and is ready to generate
more. We could follow this up by taking 20, or 200, or 2,000 elements. The stream
contains a recursive function call (specifically, a function value) that it can use to gen‐
erate new elements without end.
""";System.out.println("""res43: String("\nWe took the first five elements and retrieved them as a plain old list. Printing out the\noriginal stream instance shows that it now contains five elements and is ready to generate\nmore. We could follow this up by taking 20, or 200, or 2,000 elements. The stream\ncontains a recursive function call (specifically, a function value) that it can use to gen‐\nerate new elements without end.\n") = """ + $show(res$43));$skip(352); val res$44 = 


// MODANIC COLLECTIONS
"""
The last set of collections we’ll discuss are the monadic ones, which
support transformative operations like the ones in Iterable but can contain no more
than one element. The term “monadic” applies in its Greek origins to mean a single
unit, and in the category theory sense of a single link in a chain of operations.
""";System.out.println("""res44: String("\nThe last set of collections we’ll discuss are the monadic ones, which\nsupport transformative operations like the ones in Iterable but can contain no more\nthan one element. The term “monadic” applies in its Greek origins to mean a single\nunit, and in the category theory sense of a single link in a chain of operations.\n") = """ + $show(res$44));$skip(1050); val res$45 = 

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
""";System.out.println("""res45: String("\nAs a collection whose size will never be larger than one, the Option type represents the\npresence or absence of a single value. This potentially missing value (e.g., it was never\ninitialized, or could not be calculated) can thus be wrapped in an Option collection and\nhave its potential absence clearly advertised.\nSome developers see Option as a safe replacement for null values, notifying users that\nthe value may be missing and reducing the likelihood that its use will trigger a Null\nPointerException. Others see it as a safer way to build chains of operations, ensuring\nthat only valid values will persist for the duration of the chain.\nThe Option type is itself unimplemented but relies on two subtypes for the implemen‐\ntation: Some, a type-parameterized collection of one element; and None, an empty col‐\nlection. The None type has no type parameters because it never contains contents. You\ncan work with these types directly, or invoke Option() to detect null values and let it\nchoose the appropriate subtype.\n") = """ + $show(res$45));$skip(25); 
var p: String = "Indeed";System.out.println("""p  : String = """ + $show(p ));$skip(18); 
var z = Option(p);System.out.println("""z  : Option[String] = """ + $show(z ));$skip(13); 
var q = null;System.out.println("""q  : Null = """ + $show(q ));$skip(18); 
var r = Option(q);System.out.println("""r  : Option[Null] = """ + $show(r ));$skip(103); val res$46 = 
"""
You can use isDefined and isEmpty to check if a given Option is Some or None , respec‐
tively:
""";System.out.println("""res46: String("\nYou can use isDefined and isEmpty to check if a given Option is Some or None , respec‐\ntively:\n") = """ + $show(res$46));$skip(41); 
println(s"z is defined? ${z.isDefined}");$skip(43); 
println(s"q is not defined? ${r.isEmpty}");$skip(114); 

def divide(amt: Double, divisor: Double): Option[Double] = {
if (divisor == 0) None
else Option(amt / divisor)
};System.out.println("""divide: (amt: Double, divisor: Double)Option[Double]""");$skip(25); 
val legit = divide(5, 2);System.out.println("""legit  : Option[Double] = """ + $show(legit ));$skip(27); 
val illegit = divide(3, 0);System.out.println("""illegit  : Option[Double] = """ + $show(illegit ));$skip(2164); val res$47 = 

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
""";System.out.println("""res47: String("\nThe return type is the Option with a Double type parameter, ensuring that valid\nresults will retain the correct type.\nThis will return a Some wrapper because the dividend will be a nonnull value.\nWith a valid divisor, our dividend comes wrapped in a Some .\nWith an invalid divisor we get None , the absence of a value.\n\nThe Option collection provides a safe mechanism and operations for storing and trans‐\nforming values that may or may not be present. You shouldn’t be surprised that it also\nprovides safe operations to extract its potential value.\nFor the curious, there is also an unsafe extraction operation, the get() method. If you\ncall this for an Option that is actually a Some instance, you will successfully receive the\nvalue it contains. However, if you call get() on an instance of None , a “no such element”\nerror will be triggered.\nWe will focus on the safe operations for extracting Option values. The core strategy of\nthese operations is to provide a framework for handling missing values, such as a re‐\nplacement (aka “default”) value to use instead of the missing one, or a function that can\neither generate a replacement or raise an error condition.\n\nfold - nextOption.fold(-1)(x => x) - Returns the value from the given function for Some\n(in this case, based on the embedded value) or else the\nstarting value. The foldLeft , foldRight , and\nreduceXXX methods are also available for reducing\nan Option down to its embedded value or else a\ncomputed value.\n\ngetOrElse - nextOption getOrElse 5 or nextOption getOrElse {\nprintln(\"error!\"); -1 } - Returns the value for Some or else the result of a by-\nname parameter for None.\n\norElse nextOption orElse nextOption Doesn’t actually extract the value, but attempts to fill\nin a value for None. Returns this Option if it is\nnonempty, otherwise returns an Option from the\ngiven by-name parameter.\n\nMatch expressions nextOption match { case Some(x)\n=> x; case None => -1 }\nUse a match expression to handle the value if present.\nThe Some(x) expression extracts its data into the\nnamed value “x”, which can be used as the return\nvalue of the match expression or reused for further\ntransformation.\n") = """ + $show(res$47))}


}
