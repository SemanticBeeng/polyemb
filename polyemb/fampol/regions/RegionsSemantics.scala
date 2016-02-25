/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */ 

package polyemb.fampol.regions;

//A simple evaluator
//Defined as a trait for extensibility and reuse
trait Evaluation extends Regions {
 
  // domain types
  type Region = RegionEval
  
  // constructors
  val empty : Region = Region(p => false)
  val univ : Region = Region(p => true)
  val circle : Region = Region(p => p._1 * p._1 + p._2 * p._2 <= 1.0)
  
  def Region(pred : Vector => boolean) = RegionEval(pred)

  case class RegionEval(pred : Vector => boolean) extends (Vector => boolean) with RegionIfc {
    // operations
    def scale(v : Vector) : Region = Region(p => pred((p._1/v._1,p._2/v._2)))
    def ||(other : Region) : Region = Region(p => this(p) || other(p))
    def apply(v : Vector) : boolean = pred(v)
    override def toString = pred.toString
  }
}
//Using the evaluator as a concrete object
object Eval extends Evaluation

//A simple pretty printer
trait Printing extends Regions {
  type Region = RegionPrint

  val univ : Region = "univ"
  val empty : Region = "empty"
  val circle : Region = "circle"
  
  implicit def Region(s : String) = RegionPrint(s)
    
  case class RegionPrint(s : String) extends RegionIfc {
    def scale(v : Vector) : Region = "scale(" + v + ", " + this + ")"
    def ||(other : Region) : Region = "union(" + this + ", " + other + ")"
    override def toString = s
  }
}
//Using the pretty printer as a concrete object
object Print extends Printing
