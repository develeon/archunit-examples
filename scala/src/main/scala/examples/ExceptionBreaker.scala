package examples

class ExceptionBreaker {
  def notSafe = throw new Exception("Unsafe exception")
}
