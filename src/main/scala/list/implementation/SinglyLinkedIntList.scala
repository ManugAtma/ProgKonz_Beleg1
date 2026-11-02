package list.implementation

import list.traits.IntList

import scala.annotation.tailrec

/**
 * A companion object for the singly linked list.
 * This enables creating lists list this: val list = SinglyLinkedIntList(1,2,3)
 * which results in Cons(1,Cons(2,Cons(3,Empty))))
 */
object SinglyLinkedIntList:

  /** The apply function is a special function in scala.
   * It can be invoked with SinglyLinkedIntList.apply(args) or simply SinglyLinkedIntList(args).
   * This particular implementation of it is also a variadic function, i.e.
   * a function which accepts one or more arguments of the same type (integers) as parameters.
   */
  //inside this method xs is of type Seq[int]
  def apply(xs: Int*): SinglyLinkedIntList = xs match
    case Seq() => Empty
    //: _* results in the sequence being passed as multiple parameters - (1,2,3) instead of Seq[Int]{1,2,3}
    case _ => Cons(xs.head, SinglyLinkedIntList(xs.tail *))


abstract class SinglyLinkedIntList extends IntList:

  override def size: Int =

    @tailrec
    def helper(count: Int, l: IntList): Int = l match
      case Empty => count
      case Cons(_, _) => helper(count + 1, l.tail)

    helper(0, this)


  override def map(mapFunc: Int => Int): IntList = this match
    case Empty => Empty
    case Cons(h, t) => Cons(mapFunc(head), this.tail.map(mapFunc))


  override def filter(filterFunc: Int => Boolean): IntList = this match
    case Empty => Empty
    case Cons(h, t) if (filterFunc(h)) => Cons(head, this.tail.filter(filterFunc))
    case _ => this.tail.filter(filterFunc)

  override def foldRight(initial: Int)(reduceFunc: (Int, Int) => Int): Int =

    def helper(l: IntList, previousHead: Int): Int = l match
      case Empty => reduceFunc(previousHead, initial)
      case Cons(h, t) => reduceFunc(previousHead, helper(t, h))

    this match
      case Empty => initial
      case Cons(h,t) => helper(t, head)


  /* Do not use a fold-operator for the implementation!*/
  override def reduceRight(reduceFunc: (Int, Int) => Int): Int =

    def helper(l: IntList, previousHead: Int): Int = l match
      case Empty => previousHead
      case Cons(h, t) => reduceFunc(previousHead, helper(t, h))

    this match
      case Empty => throw new IllegalArgumentException("Empty List")
      case Cons(h, t) => helper(t, head)

  override def foldLeft(initial: Int)(reduceFunc: (Int, Int) => Int): Int =

    @tailrec
    def helper(x: Int, l: IntList): Int = l match
      case Empty => x
      case Cons(h, t) => helper(reduceFunc(x, h), l.tail)

    helper(initial, this)


  /* Do not use a fold-operator for the implementation!*/
  override def reduceLeft(reduceFunc: (Int, Int) => Int): Int =

    @tailrec
    def helper(previousResult: Int, l: IntList): Int = l match
      case Empty => previousResult
      case Cons(h, t) => helper(reduceFunc(previousResult, h), l.tail)

    this match
      case Empty => throw new IllegalArgumentException("Empty List")
      case _ => helper(head, this.tail)


  override def forAll(predicateFunc: Int => Boolean): Boolean = this match
    case Empty => true
    case Cons(h, t) if (!predicateFunc(h)) => false
    case _ => this.tail.forAll(predicateFunc)


  override def insertSorted(elem: Int): IntList =

    def helper(l: IntList): IntList = l match
      case Empty => Cons(l.head, Empty)
      case Cons(h, t) if (h >= elem) => Cons(elem, l)
      case Cons(h, t) if (t.isEmpty) => Cons(h, Cons(elem, Empty))
      case Cons(h, t) => Cons(h, helper(t))

    helper(this)


  override def insertionSort: IntList =

    @tailrec
    def helper(sorted: IntList, elem: IntList): IntList =
      if (sorted.isEmpty) Empty
      else elem match
        case Empty => sorted
        case Cons(h, t) => helper(sorted.insertSorted(elem.head), elem.tail)

    helper(Cons(head, Empty), this.tail)


  override def prefix(other: IntList): IntList = other match
    case Empty => this
    case Cons(h, t) => Cons(h, prefix(t))


  override def flip: IntList = this match
    case Empty => this
    case Cons(h, t) => Cons(h, Empty).prefix(tail.flip)