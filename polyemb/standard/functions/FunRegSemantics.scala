/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 *
 *
 * Peer language composition of /Regions/ and /Functions/ language
 * Semantics composition
 * cf. Fig. 10 - Integrating functions and regions
 * Sec. 3.5 - /Functions/ semantics
 */

package polyemb.standard.functions

import polyemb.standard.regions._

object FunRegEval extends FunReg with Evaluation with FunEval {
  implicit def fromRegion(r:Region):Rep[Region] = r
  implicit def toRegion(r:Rep[Region]):Region = r
}
object FunRegPrinting extends FunReg with Printing with FunPrinting {
  implicit def fromRegion(r:Region):Rep[Region] = r
  implicit def toRegion(r:Rep[Region]):Region = r
}
