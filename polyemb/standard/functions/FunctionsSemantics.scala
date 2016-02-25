/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 *
 *
 * Two semantics for the /Functions/ language
 * cf. Fig. 6 - /Functions/ language semantics
 * Sec. 3.8 - /Functions/ semantics
 */

package polyemb.standard.functions

trait FunEval extends Functions {
  type Rep[T] = T
  def fun[S,T](f : S => T) = f
  def app[S,T](f : S=>T, v : S) : T = f(v)
}
trait FunPrinting extends Functions {
  type Rep[X] = String
  
  object variables {
    var count = 0
    def next = {
      count = count + 1
      "x" + count
    }
  }

  def fun[S,T](f : String => String) : String = {
    val v = variables.next
    "fun(" + v + " => " + f(v) + ")"
  }
  def app[S,T](f : String, v : String) : String =
    "app(" + f + ", " + v + ")"
}
