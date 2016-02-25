/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 *
 *
 * Language interface for /Functions/ language
 * cf. Fig. 5. - A /Functions/ language
 * Section 3.7 - A /Functions/ language
 */

package polyemb.standard.functions

trait Functions {
  // abstract domain types
  type Rep[X]

  // abstract domain operations
  def fun[S, T](f : Rep[S] => Rep[T]) : Rep[S=>T]
  def app[S, T](f : Rep[S=>T], v : Rep[S]) : Rep[T]	
}
