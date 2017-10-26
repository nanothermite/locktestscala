import lock.LockImpl
import org.scalatest.FlatSpec

import scalaz.Scalaz._

class LockTest extends FlatSpec {

  var counter = 0
  val finalNum = 100000

  /**
    * feed input value to test lock
    * @return
    */
  def critical(`type`: String): Unit = {
    //val tid = currentThread().getId()
    //println(s"thread${`type`} $tid saw $counter")
    counter = counter + 1
  }

  val lock = new LockImpl()

  def threadProto(flag: Boolean) = new Thread(() => {
    if (flag) {
      lock.lock()
      critical(s"$flag")
      lock.unlock()
    } else {
      critical(s"$flag")
    }
  })

  def ThreadRun(flag: Boolean): Unit =
    (1 |-> finalNum).map ( x =>
      threadProto(flag).start
    )

  "A Safe Lock" should "yield full count output" in {
    ThreadRun(true)
    println(s"true final $counter")
    assert(counter == finalNum)
  }

  "An Unsafe Locks" should "not yield same count output" in {
    counter = 0
    ThreadRun(false)
    println(s"false final $counter")
    assert(counter != finalNum)
  }
}
