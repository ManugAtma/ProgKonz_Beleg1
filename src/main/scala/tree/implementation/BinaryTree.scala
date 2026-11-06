package tree.implementation

import list.traits.IntList
import tree.traits.IntTree

/**
 * A companion object for the singly linked list.
 * This enables creating lists list this: val list = SinglyLinkedIntList(1,2,3)
 * which results in Cons(1,Cons(2,Cons(3,Empty))))
 */
object BinaryTree:

  /** The apply function is a special function in scala.
   * It can be invoked with SinglyLinkedIntList.apply(args) or simply SinglyLinkedIntList(args).
   * This particular implementation of it is also a variadic function, i.e.
   * a function which accepts one or more arguments of the same type (integers) as parameters.
   */
  //inside this method xs is of type Seq[int]
  def apply(xs: Int*): BinaryTree =

    def treeIntialization(tree: BinaryTree, xs: Int*): BinaryTree = xs match

      case Seq() => tree
      //: _* results in the sequence being passed as multiple parameters - (1,2,3) instead of Seq[Int]{1,2,3}
      case _ => treeIntialization(tree.insert(xs.head).asInstanceOf[BinaryTree], xs.tail *)

    treeIntialization(Empty, xs *)

abstract class BinaryTree extends IntTree:

  /* Helper function used by the delete-operation
  *  Function finds the node with the next higher value
  *  compared to the root node of the binary tree (see tests)
  * */
  def findSuccessor: BinaryTree = ???


  override def delete(i: Int): IntTree = ???

  override def map(mapFun: Int => Int): IntTree =
    def helper(tree: IntTree): IntTree = tree match
      case Empty => Empty
      case NonEmpty(root, left, right) => NonEmpty(mapFun(root), helper(left), helper(right))

    helper(this)


  override def filter(filterFun: Int => Boolean): IntTree =

    def insertAll(target: IntTree, toBeInserted: IntTree): IntTree = toBeInserted match
      case Empty => target
      case NonEmpty(root, left, right) =>
        val rootInserted = target.insert(root)
        val leftInserted = insertAll(rootInserted, left)
        insertAll(leftInserted, right)

    this match
      case Empty => Empty
      case NonEmpty(elem, left, right) =>
        val filteredLeft = left.filter(filterFun)
        val filteredRight = right.filter(filterFun)

        if filterFun(elem) then
          NonEmpty(elem, filteredLeft, filteredRight)
        else
          insertAll(filteredLeft, filteredRight)


  override def tree2List: List[Int] =
    def helper(list: List[Int], tree: IntTree): List[Int] = tree match
      case Empty => list
      case NonEmpty(root, left, right) =>
        val rootInserted = root::list
        val leftInserted = helper(rootInserted, left)
        helper(leftInserted, right)
    helper(List(), this).sorted

  override def isBinaryTree: Boolean = this match
    case Empty => true
    case NonEmpty(root, left, right) if (left.isEmpty || left.root < root) && (right.isEmpty || right.root > root) => left.isBinaryTree && right.isBinaryTree
    case _ => false
