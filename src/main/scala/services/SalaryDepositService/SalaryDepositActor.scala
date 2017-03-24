package services.SalaryDepositService

import akka.actor.Actor
import services.UserAccountService.User

/**
  * Created by knoldus on 23/3/17.
  * */
class SalaryDepositActor extends Actor {
  override def receive: PartialFunction[Any, Unit] = {
    case salaryList: List[(String, Int, Int)] => {
      for (ls <- salaryList) {
        val res = User.userListMap.values.toList
        for (user <- res) {
          if (user.userName == ls._1 && user.accountNumber == ls._2) {
            val newUserAccount = user.copy(initialAmount = user.initialAmount+ls._3)
            User.userListMap -= user.userName
            User.userListMap += (user.userName -> newUserAccount)
            println("SalaryDeposited")
          }
        }
      }
    }
    case _=> println("Incorrect Input")
  }
}
