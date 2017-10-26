package lock

import java.util.concurrent.atomic.AtomicBoolean

class LockImpl extends Lock {
  private var mutex: AtomicBoolean = new AtomicBoolean(false)

  override def lock(): Unit = {
    while(!mutex.compareAndSet(false, true)) {
      //println(s"in lock ${mutex.get}")
    }
    //println(s"after lock ${mutex.get}")
  }

  override def unlock(): Unit = {
    while (!mutex.compareAndSet(true, false)) {
      //println(s"in unlock ${mutex.get}")
    }
    //println(s"after unlock ${mutex.get}")
  }
}
