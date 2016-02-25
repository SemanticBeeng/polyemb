/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 *
 *
 * Peer language composition of /Regions/ and /Functions/ language
 * Interface composition
 * cf. Fig.  9 - Integrating functions and regions
 *   & Fig. 10 - Integrating the semantics
 * Sec. 3.10 - Peer language composition
 */

package polyemb.standard.functions

import polyemb.standard.regions._

trait FunReg extends Regions with Functions {
  implicit def fromRegion(r: Region): Rep[Region]
  implicit def toRegion(r : Rep[Region]) : Region
}
object Main extends Application {
  def program(semantics : FunReg) : semantics.Rep[semantics.Region] = {
    import semantics._
    app(fun((x : Rep[Region]) => scale((5,2),x)),empty)
  }

  // prints "app(fun(x1 => scale((5,2), x1)), empty)"
  println(program(FunRegPrinting))
  // prints "false"
  println(program(FunRegEval)(2,3))
}
