/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */ 

package polyemb.fampol.functions

trait FunEval extends Functions {
  
  type Rep[T] = T
  type Fun[S,T] = FunctionEval[S,T]
  
  def fun[S,T](f : S => T) = FunctionEval(f)
  
  case class FunctionEval[S,T](f : S => T) extends FunIfc[S,T] {
    def apply(v : Rep[S]) : Rep[T] = f(v)  
  }
}

trait FunPrinting extends Functions {
  type Rep[T] = String
  type Fun[S,T] = FunctionPrint[S,T]
  
  object variables {
    var count = 0
    def next = {
      count = count + 1
      "x" + count
    }
  }

  def fun[S,T](f : String => String) = FunctionPrint(f)
  
  case class FunctionPrint[S,T](f : String => String) extends FunIfc[S,T] {
    def apply(v : String) : String = "app(" + f + ", " + v + ")"

    override def toString = {
      val v = variables.next
      "fun(" + v + " => " + f(v) + ")"
    }
  }
}
