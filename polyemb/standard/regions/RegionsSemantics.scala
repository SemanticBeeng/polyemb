/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 *
 *
 * Two semantics for the /Regions/ language
 * cf. Fig. 3 - Concrete semantics
 * Sec. 3.5 - Semantics
 */

package polyemb.standard.regions

// A simple evaluator
// Defined as a trait for extensibility and reuse
trait Evaluation extends Regions {
  type Region = Vector => boolean

  def univ : Region = p => true    
  def empty : Region = p => false    
  def circle : Region = 
    p => p._1 * p._1 + p._2 * p._2 < 1    
  def scale(v : Vector, x : Region) : Region = 
    p => x(p._1 / v._1, p._2 / v._2)    
  def union(x : Region, y : Region) : Region = 
    p => x(p) || y(p)
}
// Using the evaluator as a concrete object
object Eval extends Evaluation

// A simple pretty printer
trait Printing extends Regions {
  type Region = String
  
  def univ : Region = "univ"
  def empty : Region = "empty"
  def circle : Region = "circle"
  def scale(v : Vector, x : Region) : Region 
    = "scale(" + v + ", " + x + ")"
  def union(x : Region, y : Region) : Region 
    = "union(" + x + ", " + y + ")"
}
// Using the pretty printer as a concrete object
object Print extends Printing
