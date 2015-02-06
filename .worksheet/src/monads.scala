object monads {

  sealed trait Option[A] {
    def map[B](f: A => B): Option[B]
    def flatMap[B](f: A => Option[B]): Option[B]
  }

  case class Some[A](a: A) extends Option[A] {

    def map[B](f: A => B): Option[B] = Some(f(a))

    def flatMap[B](f: A => Option[B]): Option[B] = f(a)

  }

  case class None[A]() extends Option[A] {

    def map[B](f: A => B): Option[B] = None()

    def flatMap[B](f: A => Option[B]): Option[B] = None()

  }

  class Foo {
    def bar: Option[Bar] = Some(new Bar)
  }

  class Bar {

    def baz: Option[Baz] = Some(new Baz)
  }

  class Baz { def compute: Int = { 1 } };import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(692); 

  def computeBar(bar: Bar): Option[Int] = {
    bar.baz.map { computeBaz }
  };System.out.println("""computeBar: (bar: monads.Bar)monads.Option[Int]""");$skip(84); 

  def computeFoo(foo: Foo): Option[Int] = {
    foo.bar.flatMap { computeBar }
  };System.out.println("""computeFoo: (foo: monads.Foo)monads.Option[Int]""");$skip(57); 

  def computeBaz(baz: Baz): Int = {
    baz.compute
  };System.out.println("""computeBaz: (baz: monads.Baz)Int""");$skip(133); 

  def compute(foo: Option[Foo]): Option[Int] = {
    foo.flatMap { f => f.bar.flatMap { b => b.baz.map { bz => bz.compute } } }
  };System.out.println("""compute: (foo: monads.Option[monads.Foo])monads.Option[Int]""");$skip(35); 

  println(compute(Some(new Foo)))}
}
