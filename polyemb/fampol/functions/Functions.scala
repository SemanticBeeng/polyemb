/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */ 

package polyemb.fampol.functions

trait Functions {
  // abstract domain types
  type Rep[X]
	type Fun[S,T] <: FunIfc[S,T]
  
  // abstract domain operations
  def fun[S, T](f : Rep[S] => Rep[T]) : Fun[S,T]
  
  trait FunIfc[S,T] extends (Rep[S] => Rep[T]) {
    def apply(v : Rep[S]) : Rep[T]
  }
}
