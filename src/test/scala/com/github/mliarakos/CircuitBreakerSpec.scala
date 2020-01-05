package com.github.mliarakos

import akka.actor.ActorSystem
import akka.pattern.CircuitBreaker
import akka.pattern.CircuitBreakerOpenException
import org.scalatest.AsyncFlatSpec
import org.scalatest.BeforeAndAfterAll
import org.scalatest.Matchers
import org.scalatest.concurrent.Futures

import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.Failure

/*
 * Demonstrate an issue with the CircuitBreaker in Akka.js
 *
 * Derived from the Lagom CircuitBreakersPanelInternalSpec:
 * https://github.com/lagom/lagom/blob/master/service/core/client/src/test/scala/com/lightbend/lagom/internal/client/CircuitBreakersPanelInternalSpec.scala
 */
class CircuitBreakerSpec extends AsyncFlatSpec with Matchers with BeforeAndAfterAll with Futures {
  private val system = ActorSystem("CircuitBreakerSpec")

  override def afterAll(): Unit = {
    system.terminate()
  }

  it should "open the circuit" in {
    // Configure an Akka CircuitBreaker to trip after a single failure
    val maxFailures  = 1
    val callTimeout  = 10.millis
    val resetTimeout = 15.millis
    val breaker      = new CircuitBreaker(system.scheduler, maxFailures, callTimeout, resetTimeout)(system.dispatcher)

    // The circuit breaker should open in the second step when the call throws an exception. The third step should then
    // fail with a CircuitBreakerOpenException. However, it fails with an IllegalStateException instead.
    recoverToSucceededIf[CircuitBreakerOpenException] {
      val openBreaker =
        for {
          _ <- successfulCall(breaker, "a")
          _ <- failedCall(breaker, new Exception("b"))
          x <- successfulCall(breaker, "c")
        } yield x

      // Print exception stack trace to help debug
      openBreaker.onComplete({
        case Failure(exception) => exception.printStackTrace()
        case _                  =>
      })

      openBreaker
    }
  }

  /*
   * Execute a successful circuit breaker call.
   */
  private def successfulCall(breaker: CircuitBreaker, mockedResponse: String): Future[String] = {
    breaker.withCircuitBreaker(Future.successful(mockedResponse))
  }

  /*
   * Fail a circuit break call to open the circuit breaker.
   *
   * The call is recovered to a success so that it can be composed with other calls that test the open circuit breaker.
   */
  private def failedCall(breaker: CircuitBreaker, failure: Exception): Future[String] = {
    breaker
      .withCircuitBreaker(Future.failed(failure))
      .recoverWith {
        case _ => Future.successful("Expected a Failure.")
      }
  }

}
