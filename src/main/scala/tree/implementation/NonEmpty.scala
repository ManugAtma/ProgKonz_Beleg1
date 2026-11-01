package tree.implementation

import tree.traits.IntTree

case class NonEmpty(val elem: Int, left: IntTree, right: IntTree) extends BinaryTree :

  override def isEmpty = false

  override def root:Int= elem

  override infix def contains(i: Int): Boolean = ???

  override def insert(i:Int):IntTree=
    if (i < elem) NonEmpty(elem, left insert i, right)
    else if (i > elem) NonEmpty(elem, left, right insert i)
    else this
  
  override def size: Int = ???

  override def height:Int= ???
