An implementation of a Lock (more of a semaphore) using java.util.concurrent classes aside
from java.util.concurrent.lock in scala.

Usage:
% sbt test
[info] Loading global plugins from /home/hkatz/.sbt/0.13/plugins
[info] Loading project definition from /home/hkatz/devel/src/Scala/locktest/project
[info] Set current project to locktest (in build file:/home/hkatz/devel/src/Scala/locktest/)
[info] Compiling 1 Scala source to /home/hkatz/devel/src/Scala/locktest/target/scala-2.12/test-classes...
true final 100000
false final 99993
[info] LockTest:
[info] A Safe Lock
[info] - should yield full count output
[info] An Unsafe Locks
[info] - should not yield same count output
[info] Run completed in 16 seconds, 207 milliseconds.
[info] Total number of tests run: 2
[info] Suites: completed 1, aborted 0
[info] Tests: succeeded 2, failed 0, canceled 0, ignored 0, pending 0
[info] All tests passed.
[success] Total time: 22 s, completed Oct 25, 2017 11:14:09 PM
