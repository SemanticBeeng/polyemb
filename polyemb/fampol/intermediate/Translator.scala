/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */ 

package polyemb.fampol.intermediate;

import polyemb.fampol.regions._

/*
 * A translator to an intermediate language of arithmetics
 * Corresponds to the "Evaluation" trait in Fig. 8
 * Cf. Sec. 3.9
 */
trait Translator extends Regions {
  val base: Base
  import base._
  
  type Region = RegionTrans
  
  def Region(pred : ((Real, Real)) => Bool) = RegionTrans(pred)

  case class RegionTrans(pred : ((Real, Real)) => Bool) extends (((Real, Real)) => Bool) with RegionIfc {
    // operations
    def scale(v : Vector) : Region = Region(p => pred((p._1/v._1,p._2/v._2)))
    def ||(other : Region) : Region = Region(p => this(p) || other(p))
    def apply(v : ((Real, Real))) : Bool = pred(v)
    override def toString = pred.toString
  }

  def univ : Region = Region(p => true)    
  def empty : Region = Region(p => false)    
  def circle : Region = Region(p => p._1 * p._1 + p._2 * p._2 <= 1.0)   
  def scale(v : Vector, x : Region) : Region = Region(p => x((p._1 / v._1, p._2 / v._2)))
  def union(x : Region, y : Region) : Region = Region(p => x(p) || y(p))
}

object Compile extends Translator {
  val base = new AstBase {}
}

// Combining the compiler with the optimization component
object OptimizeCompile extends Optimization {
  val semantics = Compile
}