/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */ 

package polyemb.fampol.functions;

import polyemb.fampol.regions._

trait FunReg extends Regions with Functions {

  implicit def fromRegion(r: Region): Rep[Region]
  implicit def toRegion(r : Rep[Region]) : Region
  
}

object Main extends Application {
  def program(semantics : FunReg) : semantics.Rep[semantics.Region] = {
    import semantics._
    fun((x : Rep[Region]) => (x).scale((5,2)))(empty)
  }
  
  // prints "app(fun(x1 => scale((5,2), x1)), empty)"
  println(program(FunRegPrinting))
  // prints "false"
  println(program(FunRegEval)(2,3))
}