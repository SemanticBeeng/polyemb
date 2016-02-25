/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 *
 *
 * A simple /Regions/ program using the language interface
 * cf. Fig. 2 - /Regions/ Language Interface in Scala
 * &   Fig. 3 - Concrete semantics
 * &   Fig. 4 - Optimization
 * Section 3.4 - Programs and Oblivious Clients
 * Section 3.5 - Semantics
 * Section 3.6 - Modular Semantics
 *
 * Makes use of dependent method types
 * Requires -Xexperimental to compile
 */

package polyemb.standard.regions

object Main extends Application {
  def program(semantics : Regions) : semantics.Region = {  
    import semantics._
    val ellipse24 = scale((2, 4), circle)
    union(univ, ellipse24) // the returned expression
  }

  // prints "union(univ, scale((2.0,4.0), circle)"
  println(program(Print))

  // prints "true"
  println(program(Eval)((1, 2)))

  // Using the optimization
  // prints "(univ, true)"
  println(program(OptimizePrint))
  
  // import the Compile semantics
  import intermediate.Compile
  // import the auto-conversion from double to FloatR
  import intermediate.Compile.base.float
  // prints: "OrExp(BoolLit(true),
  //    			LeExp(AddExp(MulExp(DivExp(RealLit(2.0),RealLit(2.0)),DivExp(RealLit(2.0),RealLit(2.0))),
  //          			       MulExp(DivExp(RealLit(3.0),RealLit(4.0)),DivExp(RealLit(3.0),RealLit(4.0)))),
  //          			RealLit(1.0)))"
  println(program(Compile)((2.0,3.0)))
  
  import intermediate.OptimizeCompile
  // prints: "BoolLit(true)"
  println(program(OptimizeCompile)._1((2.0,3.0)))
}
