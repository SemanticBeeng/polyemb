/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */ 

package polyemb.fampol.regions;

trait Regions {
  //ordinary type synonyms
  type Vector = (double, double)

  // abstract domain types
  type Region <: RegionIfc
  
  // infix operations on regions 
  trait RegionIfc {
    // operations
    def scale(v : Vector) : Region
    def ||(other : Region) : Region
  }

  // region constructors
  def empty : Region
  def univ : Region
  def circle : Region
}
