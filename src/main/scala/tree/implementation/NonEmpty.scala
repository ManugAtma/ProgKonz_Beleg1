package tree.implementation

import tree.traits.IntTree

case class NonEmpty(val elem: Int, left: IntTree, right: IntTree) extends BinaryTree:

  override def isEmpty = false

  override def root: Int = elem

  override infix def contains(i: Int): Boolean =
    if (root == i) true
    else left.contains(i) || right.contains(i)

  override def insert(i: Int): IntTree =
    if (i < elem) NonEmpty(elem, left insert i, right)
    else if (i > elem) NonEmpty(elem, left, right insert i)
    else this

  override def size: Int = 1 + left.size + right.size

  override def height: Int =
    def helper(tree: IntTree): Int = tree match
      case Empty => 0
      case NonEmpty(_,left,right) => Math.max(1 + helper(left), 1 + helper(right))
    helper(this) - 1
