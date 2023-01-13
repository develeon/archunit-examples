package examples

import examples.models.Command

class OrderHandler extends CommandHandler {
  override def handle[TCommand <: Command](command: TCommand): Int = 42
}
