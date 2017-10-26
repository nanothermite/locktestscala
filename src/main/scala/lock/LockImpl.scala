package lock

import java.util.concurrent.atomic.AtomicBoolean

class LockImpl extends Lock {
  private val mutex: AtomicBoolean = new AtomicBoolean(false)

  override def lock(): Unit = while(!mutex.compareAndSet(false, true)) { }

  override def unlock(): Unit = while (!mutex.compareAndSet(true, false)) { }
}
