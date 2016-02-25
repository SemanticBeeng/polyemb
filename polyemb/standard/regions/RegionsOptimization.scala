/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */
 
package polyemb.standard.regions

trait Optimization extends Regions {
  val semantics : Regions
  
  type Region = (semantics.Region, boolean)

  def univ : Region = (semantics.univ, true)
  def empty : Region = (semantics.empty, false)
  def circle : Region = (semantics.circle, false)
  
  def scale(v : Vector, x : Region) : Region = 
    if (x._2) (semantics.univ, true) 
    else (semantics.scale(v, x._1), false)
        
  def union(x : Region, y : Region) : Region =
    if (x._2 || y._2) (semantics.univ, true) 
    else (semantics.union(x._1, y._1), false)
}

object OptimizePrint extends Optimization {
  val semantics = Print
}
