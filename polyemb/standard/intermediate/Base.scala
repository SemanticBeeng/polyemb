/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 *
 *
 * An intermediate language of arithmetics
 * Corresponds to the "Evaluation" in Fig. 8
 * Cf. Sec. 3.9
 */ 

package polyemb.standard.intermediate

trait Base {
   
   // domain types
   type FloatR
   type BooleanR
   
   // constructors
   implicit def bool(b : Boolean) : BooleanR
   implicit def float(r : Double) : FloatR
  
   // operations
   def or(a : BooleanR, b : BooleanR) : BooleanR
   def add(a : FloatR, b : FloatR) : FloatR
   def mul(a : FloatR, b : FloatR) : FloatR
   def div(a : FloatR, b : FloatR) : FloatR
   def le(a : FloatR, b : FloatR) : BooleanR
}

// Creates a simple AST for the arithmetics language
// Does not do any optimizations at all
trait AstBase extends Base {
  
  type FloatR = RealExp
  type BooleanR = BoolExp

  trait BoolExp
  case class BoolLit(b : boolean) extends BoolExp
  case class OrExp(a : BoolExp, b : BoolExp) extends BoolExp
  case class LeExp(a : RealExp, b : RealExp) extends BoolExp
  trait RealExp
  case class RealLit(r : double) extends RealExp
  case class AddExp(x : RealExp, y : RealExp) extends RealExp
  case class MulExp(x : RealExp, y : RealExp) extends RealExp
  case class DivExp(x : RealExp, y : RealExp) extends RealExp

  // constructors
  implicit def bool(b : Boolean) : BooleanR = BoolLit(b)
  implicit def float(r : Double) : FloatR = RealLit(r)

  // operations
  def or(a : BooleanR, b : BooleanR) : BooleanR = OrExp(a, b)
  def add(a : FloatR, b : FloatR) : FloatR = AddExp(a,b)
  def mul(a : FloatR, b : FloatR) : FloatR = MulExp(a,b)
  def div(a : FloatR, b : FloatR) : FloatR = DivExp(a,b)
  def le(a : FloatR, b : FloatR) : BooleanR = LeExp(a,b)
}

