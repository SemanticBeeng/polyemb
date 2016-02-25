/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 *
 *
 * Language interface for /Regions/ language
 * cf. Fig. 2 - /Regions/ Language Interface in Scala
 * Section 3.3 - The /Regions/ Language
 */

package polyemb.standard.regions

trait Regions {
  // ordinary type synonyms
  type Vector = (double, double)

  // abstract domain types
  type Region
  
  // abstract domain operations
  def univ : Region
  def empty : Region
  def circle : Region
  def scale(v : Vector, x : Region) : Region
  def union(x : Region, y : Region) : Region
}

