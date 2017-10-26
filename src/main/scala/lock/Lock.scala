package lock

trait Lock {
  def lock(): Unit

  def unlock(): Unit
}
