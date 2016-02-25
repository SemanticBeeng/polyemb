/* This is part of the accompanying source code to the paper:
 *
 * Christian Hofer, Klaus Ostermann, Tillmann Rendel, Adriaan Moors,
 * Polymorphic Embedding of DSLs
 * submitted to GPCE 2008
 *
 *
 * An alternative to the simple /Regions/ program
 * doing without dependent method types
 * cf. Fig. 3 - Concrete semantics
 * &   Fig. 4 - Optimization
 * Section 3.4 - Programs and Oblivious Clients
 * Section 3.5 - Semantics
 * Section 3.6 - Modular Semantics
 */

package polyemb.standard.regions

/*
 *  "Emulation" of dependent method types
 * Method definitions on the call side that do not fix the semantics
 * have to declare the type parameter as well.
 */
case class program[R](val semantics: Regions{type Region = R}) {
  def apply : R = {
    import semantics._    
    val ellipse24 : R = scale((2, 4), circle)
    union(univ, ellipse24)
  }
} 

/*
 * The calling code looks the same
 * as Scala's type inference infers the type parameter
 */
object UnExperimental extends Application {

  // prints "union(univ, scale((2.0,4.0), circle)"
  println(program(Print))

  // prints "true"
  println(program(Eval).apply((1, 2)))

  // Using the optimization
  // prints "union(univ, scale((2.0,4.0), circle)"
  println(program(Print))

}
