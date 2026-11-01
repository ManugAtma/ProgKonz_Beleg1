package tree.traits

/**
  * Specifies an immutable list of integers.
  */
trait IntTree :

  /**
    * Indicates if the tree has no elements.
    *
    * @return true if the tree contains no elements
    */
  def isEmpty: Boolean

  /**
    * Returns the root element of the tree.
    *
    * @return the root element
    */
  def root: Int

  /**
    * Returns the number of elements in the tree.
    *
    * @return the number of elements in the tree
    **/
  def size: Int

  /**
    * Returns the height of the tree.
    *
    * @return the height
    */
  def height: Int

  /**
    * Indicates if the given element is in the tree.
    *
    * @param elem the element to check
    * @return true if the element is in the tree, false otherwise
    */
  def contains(elem: Int): Boolean

  /**
    * Inserts an elem into the tree
   *
    * @param elem the element to insert
    * @return the tree containing the inserted element
 */
  infix def insert(elem:Int): IntTree

  /**
   * Returns a new tree without the given element.
   *
   * @param elem the element
   * @return a new tree without the element
   * @throws Error if the tree does not contain the element
   */
  def delete(elem: Int): IntTree

  /**
   * Applies a unary function to all elements of the tree
   * and returns a new tree containing the new values.
   *
   * @param mapFunc the function to apply to all elements
   * @return a new tree containing the new values
   */
  def map(mapFun: Int=>Int): IntTree

  /**
   * Returns a new tree that only contains the elements for which predicateFun returns true.
   *
   * @param predicateFunc the predicate function
   * @return a new tree containing only the elements, which fulfill the predicate
   */
  def filter(filterFun: Int=>Boolean): IntTree

  /**
   * Returns a list which contains all the tree-elements in a sorted manner.
   *
   * @return a sorted list of all elements of the tree
   */
  def tree2List: List[Int]

  /**
   * Returns true if the given tree fulfill the properties of a binary tree.
   *
   * @return true if the tree is a Binary Tree else false
   */
  def isBinaryTree:Boolean
