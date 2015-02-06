sealed trait Option[A] {
	def map[B](f: A=> B):Option[B]
	def flatMap[B](f: A => Option[B]):Option[B]
}

case class Some[A](a:A) extends Option[A]{

	def map[B](f: A => B):Option[B] = Some(f(a))

	def flatMap[B](f: A => Option[B]):Option[B] = f(a)

}

case class None[A]() extends Option[A] {

	def map[B](f: A => B):Option[B] = None()

	def flatMap[B](f: A => Option[B]):Option[B] = None()

}

class Foo {
	def bar:Option[Bar] = Some(new Bar)
}

class Bar{

	def baz:Option[Baz] = Some(new Baz)
}

class Baz{def compute:Int = {1}}


def computeBar(bar:Bar):Option[Int]  = {
	bar.baz.map {computeBaz}
}

def computeFoo(foo:Foo):Option[Int] = {
	foo.bar.flatMap {computeBar}
}

def computeBaz(baz:Baz):Int = {
	baz.compute
}

def compute(foo:Option[Foo]):Option[Int] = foo flatMap {computeFoo }

	

println(compute(new Foo))