/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 */ 

package polyemb.fampol.regions;

object Main extends Application {
  def program(semantics : Regions) : semantics.Region = {  
    import semantics._
    univ || circle.scale((2, 4))
  }

  // prints "true"
  println(program(Eval)((1, 2)))
  
  // prints "union(univ, scale((2.0,4.0), circle)"
  println(program(Print))
  
  // Using the optimization
  // prints "(univ, true)"
  println(program(OptimizePrint))
  
  // import the Compile semantics
  import intermediate.Compile
  // import the auto-conversion from double to real
  import intermediate.Compile.base.real
  // prints: "OrExp(BoolLit(true),
  //          LeExp(AddExp(MulExp(DivExp(RealLit(2.0),RealLit(2.0)),DivExp(RealLit(2.0),RealLit(2.0))),
  //                       MulExp(DivExp(RealLit(3.0),RealLit(4.0)),DivExp(RealLit(3.0),RealLit(4.0)))),
  //                RealLit(1.0)))"
  println(program(Compile)((2.0,3.0)))
  
  import intermediate.OptimizeCompile
  // prints: "BoolLit(true)"
  println(program(OptimizeCompile).r((2.0,3.0)))
}
