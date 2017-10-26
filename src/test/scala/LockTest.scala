import lock.LockImpl
import org.scalatest.FlatSpec

import scalaz.Scalaz._

class LockTest extends FlatSpec {

  var counter = 0
  val finalNum = 100000
  val lock = new LockImpl()

  def critical(): Unit = counter = counter + 1

  def threadProto(flag: Boolean) = new Thread(() => {
    if (flag) {
      lock.lock()
      critical()
      lock.unlock()
    } else {
      critical()
    }
  })

  def ThreadRun(flag: Boolean): Unit = (1 |-> finalNum).foreach(x => threadProto(flag).start() )

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
