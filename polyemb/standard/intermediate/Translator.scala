/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */
 
package polyemb.standard.intermediate;

import polyemb.standard.regions._

/*
 * A translator to an intermediate language of arithmetics
 * Corresponds to the "Evaluation" trait in Fig. 8
 * Cf. Sec. 3.9
 */
trait Translator extends Regions {
  val base: Base
  import base._
  
  type Region = ((FloatR, FloatR)) => BooleanR

  def univ : Region = p => true    
  def empty : Region = p => false    
  def circle : Region = 
    p => le(add(mul(p._1, p._1), mul(p._2, p._2)), 1.0)   
  def scale(v : Vector, x : Region) : Region = 
    p => x((div(p._1, v._1), div(p._2, v._2)))
  def union(x : Region, y : Region) : Region = 
    p => or(x(p),y(p))
}

object Compile extends Translator {
  val base = new AstBase {}
}

// Combining the compiler with the optimization component
object OptimizeCompile extends Optimization {
  val semantics = Compile
}