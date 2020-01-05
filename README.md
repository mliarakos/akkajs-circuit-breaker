### Reproduce the Issue

1. Clone the issue repo:

   ```sh
   git clone https://github.com/mliarakos/akkajs-circuit-breaker.git
   ```
1. Run the test:

   ```sh
   cd akkajs-circuit-breaker
   sbt test
   ```
1. The test should fail with this stack trace:

   ```
   java.lang.IllegalStateException: exception in sameThreadExecutionContext
     at $c_jl_IllegalStateException.$c_jl_Throwable.fillInStackTrace__jl_Throwable(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:43697:14)
     at $c_jl_IllegalStateException.$c_jl_Throwable.init___T__jl_Throwable__Z__Z(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:43805:10)
     at java.lang.IllegalStateException.<init>(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:70673:58)
     at akka.dispatch.ExecutionContexts$sameThreadExecutionContext$.reportFailure(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:57643:43)
     at scala.concurrent.impl.CallbackRunnable.executeWithValue(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:55834:27)
     at scala.concurrent.impl.Promise$KeptPromise$Kept.onComplete(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:62151:104)
     at scala.concurrent.impl.Promise$KeptPromise$Failed.onComplete(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:88820:3)
     at akka.pattern.CircuitBreaker$State.callThrough(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:4522:133)
     at akka.pattern.CircuitBreaker$Closed$.invoke(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:78842:10)
     at com.github.mliarakos.CircuitBreakerSpec.failedCall(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:120301:159)
   Caused by: java.lang.IllegalStateException: exception in sameThreadExecutionContext
     at $c_jl_IllegalStateException.$c_jl_Throwable.fillInStackTrace__jl_Throwable(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:43697:14)
     at $c_jl_IllegalStateException.$c_jl_Throwable.init___T__jl_Throwable__Z__Z(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:43805:10)
     at java.lang.IllegalStateException.<init>(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:70673:58)
     at akka.dispatch.ExecutionContexts$sameThreadExecutionContext$.reportFailure(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:57643:43)
     at scala.concurrent.impl.CallbackRunnable.run(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:55806:27)
     at $c_Lakka_dispatch_BatchingExecutor$Batch.$c_Lakka_dispatch_BatchingExecutor$AbstractBatch.processBatch__Lakka_dispatch_BatchingExecutor$AbstractBatch__V(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:109512:46)
     at akka.dispatch.BatchingExecutor$Batch.run(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:111476:10)
     at akka.dispatch.BatchingExecutor.execute(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:8003:16)
     at akka.dispatch.ExecutionContexts$sameThreadExecutionContext$.execute(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:57646:3)
     at scala.concurrent.impl.CallbackRunnable.executeWithValue(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:55826:21)
   Caused by: scala.scalajs.js.JavaScriptException: TypeError: this.currentStateCallMeDirectly$und$eq__O__ is not a function
     at akka.pattern.CircuitBreaker.transition(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:36256:23)
     at akka.pattern.CircuitBreaker$Closed$.callFails(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:78850:12)
     at {anonymous}()(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:4504:18)
     at scala.scalajs.runtime.AnonFunction1.apply(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:56903:23)
     at scala.concurrent.impl.CallbackRunnable.run(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:55798:23)
     at $c_Lakka_dispatch_BatchingExecutor$Batch.$c_Lakka_dispatch_BatchingExecutor$AbstractBatch.processBatch__Lakka_dispatch_BatchingExecutor$AbstractBatch__V(akkajs-circuit-breaker/target/scala-2.12/akkajs-circuit-breaker-test-fastopt.js:109512:46)
     ... 4 more
   ```
