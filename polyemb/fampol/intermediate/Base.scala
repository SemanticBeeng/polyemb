/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */ 

package polyemb.fampol.intermediate

trait Base {
   
   // domain types
   type Real <: RealIfc
   type Bool <: BoolIfc
   
   // constructors
   implicit def bool(b : Boolean) : Bool
   implicit def real(r : Double) : Real
  
   trait RealIfc {
     def +(other : Real) : Real
     def *(other : Real) : Real
     def /(other : Real) : Real
     def <=(other : Real) : Bool
   }
   trait BoolIfc {
     def ||(other : Bool) : Bool
   }
}

// Creates a simple AST for the arithmetics language
// Does not do any optimizations at all
trait AstBase extends Base {
  
  type Real = RealExp
  type Bool = BoolExp

  // constructors
  implicit def bool(b : boolean) : Bool = BoolLit(b)
  implicit def real(r : double) : Real = RealLit(r)
   
  trait RealExp extends RealIfc {
    def +(other : Real) : Real = AddExp(this,other)
    def *(other : Real) : Real = MulExp(this,other)
    def /(other : Real) : Real = DivExp(this,other)
    def <=(other : Real) : Bool = LeExp(this,other)
  }
  trait BoolExp extends BoolIfc {
    def ||(other : Bool) : Bool = OrExp(this,other)
  }
  
  case class BoolLit(b : boolean) extends BoolExp
  case class OrExp(a : BoolExp, b : BoolExp) extends BoolExp
  case class LeExp(a : RealExp, b : RealExp) extends BoolExp
  case class RealLit(d : double) extends RealExp
  case class AddExp(x : RealExp, y : RealExp) extends RealExp
  case class MulExp(x : RealExp, y : RealExp) extends RealExp
  case class DivExp(x : RealExp, y : RealExp) extends RealExp
}

