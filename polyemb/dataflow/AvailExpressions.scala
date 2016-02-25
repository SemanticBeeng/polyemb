/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */ 

package polyemb.dataflow;

import scala.collection.immutable.HashSet
import Set.empty

// Simple "available expressions" dataflow analysis 
// as described in Sec. 2.1.1 of the book "Principles of Program Analysis"
// in our DSL design
trait WhileLang {
  type StmtRep
  type AExpRep
  type BExpRep
  def assign(x: String, a: AExpRep) : StmtRep
  def skip : StmtRep
  def seq(s1 : StmtRep, s2 : StmtRep) : StmtRep
  def if_(b : BExpRep, t: StmtRep, e: StmtRep) : StmtRep
  def while_(b: BExpRep, t: StmtRep) : StmtRep
  def lt(a1 : AExpRep, a2: AExpRep) : BExpRep
  def var_(x: String) : AExpRep
  def int_(i: int) : AExpRep
  def plus(x: AExpRep, y: AExpRep) : AExpRep
  def mult(x: AExpRep, y: AExpRep) : AExpRep
}

// AST classes to represent arithmetic expressions
abstract class AExp {
  def freevars : Set[String] 
}
case class Var(x: String) extends AExp {
  def freevars = HashSet(x)
  override def toString() = x
}
case class Plus(a: AExp, b: AExp) extends AExp {
  def freevars = a.freevars ++ b.freevars
  override def toString() = a.toString() + "+" + b.toString()
}
case class Mult(a: AExp, b: AExp) extends AExp {
  def freevars = a.freevars ++ b.freevars
  override def toString() = a.toString() + "*" + b.toString()
}
case class Int(i: int) extends AExp {
  def freevars = empty
  override def toString() = i.toString()
}

object AvailableExpressions extends WhileLang {
  type AExpRep = (Set[AExp], AExp)
  type BExpRep = Set[AExp]
  // model kill/gen sets as function that transforms the set of available expressions
  // (from what's available at entry of this statement to what's available at exit)
  type StmtRep = Set[AExp] => Set[AExp]
  
  def lt(a1 : AExpRep, a2: AExpRep) = a1._1 ++ a2._1
  def var_(x: String) = (empty, Var(x))
  def int_(i: int) =(empty, Int(i))
  def plus(x: AExpRep, y: AExpRep) = {
    val z =  Plus(x._2,y._2)
    (x._1 ++ y._1 + z, z)
  }
  def mult(x: AExpRep, y: AExpRep) = {
    val z =  Mult(x._2,y._2)
    (x._1 ++ y._1 +z, z)
  }
  
  def assign(x: String, a: AExpRep) = { ae =>
    (ae++a._1).filter( ! _.freevars.contains(x) )
  }
  
  def skip  = {ae => ae}
  
  def seq(s1 : StmtRep, s2 : StmtRep) =  {ae => s2(s1(ae))}
  
  def if_(b : BExpRep, t: StmtRep, e: StmtRep) = { ae => 
    b ++ (t(ae) intersect (e(ae)))
  }
    
  def while_(b: BExpRep, t: StmtRep) : StmtRep = { ae => 
    fix(b ++ ae){x => x intersect t(x)}
  }
  
  // compute fixed point
  def fix[T](init: T)(iter: T => T): T = {
    var x = init
    while(x != iter(x)) x = iter(x)
    x
  }
}

object Main extends Application {
  def test1(w: WhileLang) : w.StmtRep = {
    import w._
    
    seq(assign("a",plus(var_("a"),int_(1))),
        assign("x",plus(var_("a"),var_("b"))))
  }
  def test2(w: WhileLang) : w.StmtRep = {   // Example 2.5 from POPA
  	import w._
    seq(assign("x", plus(var_("a"),var_("b"))),
        seq(assign("y", mult(var_("a"),var_("b"))),
            while_(lt(var_("y"),plus(var_("a"),var_("b"))),
                   seq(assign("a",plus(var_("a"),int_(1))),
                       assign("x",plus(var_("a"),var_("b")))))))
  }
  println(test1(AvailableExpressions)(empty).mkString(","))
  println(test2(AvailableExpressions)(empty).mkString(","))
}