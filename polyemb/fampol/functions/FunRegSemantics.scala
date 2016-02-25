/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */ 

package polyemb.fampol.functions;

import polyemb.fampol.regions._

object FunRegEval extends FunReg with Evaluation with FunEval {
  implicit def fromRegion(r:Region):Rep[Region] = r
  implicit def toRegion(r:Rep[Region]):Region = r
}

object FunRegPrinting extends FunReg with Printing with FunPrinting {
  implicit def fromRegion(r:Region):Rep[Region] = r.toString
  implicit def toRegion(r:Rep[Region]):Region = Region(r)
}
