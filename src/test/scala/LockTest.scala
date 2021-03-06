import lock.LockImpl
import org.scalatest.FlatSpec

import scalaz.concurrent.Task

class LockTest extends FlatSpec {

  @volatile
  var counter = 0
  val finalNum = 100000
  val lock = new LockImpl()

  /**
    * build a task with/w/o locking
    * @param flag locking switch
    * @return
    */
  def taskProto(flag: Boolean): Task[Unit] = {
    /**
      * side effect on mutable
      */
    def critical(): Unit = counter = counter + 1

    Task {
      if (flag) {
        lock.lock()
        try {
          critical()
        } finally {
          lock.unlock()
        }
      } else {
        critical()
      }
    }
  }

  /**
    * compile a collection of tasks
    * @param flag locking switch
    * @return
    */
  def TaskBuild(flag: Boolean): List[Task[Unit]] = List.fill(finalNum){ taskProto(flag)}

  "A Lock" should "yield full count summation" in {
    val tasks = TaskBuild(true)
    Task.gatherUnordered(tasks).unsafePerformSync
    println(s"true final $counter")
    assert(counter == finalNum)
  }

  "Absence of Lock" should "yield count collisions" in {
    counter = 0
    val tasks = TaskBuild(false)
    Task.gatherUnordered(tasks).unsafePerformSync
    println(s"false final $counter")
    assert(counter != finalNum)
  }
}
