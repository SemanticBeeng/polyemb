/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */ 

package polyemb.fampol.regions;

trait Optimization extends Regions {
  val semantics : Regions
  
  // domain types
  type Region = RegionOpt
  
  // constructors
  def univ : Region = (semantics.univ, true)
  def empty : Region = (semantics.empty, false)
  def circle : Region = (semantics.circle, false)
  
  implicit def Region(reg : (semantics.Region, boolean)) = RegionOpt(reg._1, reg._2)

  case class RegionOpt(r : semantics.Region, isUniv : boolean) extends RegionIfc {
    // operations
    def scale(v : Vector) : Region = {
      if (isUniv) (semantics.univ, true)
      else (r.scale(v), false)
    }
    def ||(other : Region) : Region = {
      if (isUniv || other.isUniv) (semantics.univ, true)
      else (r || other.r, false)
    }
    override def toString = (r, isUniv).toString
  }
}

object OptimizePrint extends Optimization {
  val semantics = Print
}
