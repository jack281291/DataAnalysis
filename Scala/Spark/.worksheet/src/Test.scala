object Test {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(35); 
  val number: Int = 5;System.out.println("""number  : Int = """ + $show(number ));$skip(38); 
  val Stringa: String = "Hello World";System.out.println("""Stringa  : String = """ + $show(Stringa ));$skip(34); 
  val huge: Double = 100.44583738;System.out.println("""huge  : Double = """ + $show(huge ));$skip(28); 
  println(s"This is $huge");$skip(19); 
  println(Stringa);$skip(97); 
  if (number > 10) {
    println("Lol, impossible")
  }
  else {
    println("That's right")
  };$skip(17); 
  var x: Int = 8;System.out.println("""x  : Int = """ + $show(x ));$skip(50); 
  while (x < 10) {
  	println(x)
  	x = x + 1
  };$skip(41); 
  def cube(x: Int): Int = {
  	x*x*x
  };System.out.println("""cube: (x: Int)Int""");$skip(64); 
  def applyFunction(x: Int, f: Int => Int): Int = {
  	f(x)
  };System.out.println("""applyFunction: (x: Int, f: Int => Int)Int""");$skip(34); 
  println(applyFunction(4, cube));$skip(33); 
  val tupleNumberOne = ("a","b");System.out.println("""tupleNumberOne  : (String, String) = """ + $show(tupleNumberOne ));$skip(20); val res$0 = 
  tupleNumberOne._1;System.out.println("""res0: String = """ + $show(res$0))}
  
}
