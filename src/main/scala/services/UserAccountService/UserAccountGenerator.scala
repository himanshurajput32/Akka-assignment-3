package services.UserAccountService

import akka.actor.Actor
import models.UserAccount

/**
  * Created by knoldus on 23/3/17.
  */
class UserAccountGenerator extends Actor {
  var accountNumberCounter=0
  def checkAvailability(username: String): Boolean = {
    if (User.userListMap.contains(username))
      true

    else
      false
  }

  override def receive: PartialFunction[Any, Unit] = {
    case userAccount:UserAccount=> {

      if (checkAvailability(userAccount.userName)) {
        sender ! "User already exists"
      }
      else  {
        accountNumberCounter +=1
        val user=userAccount.copy(accountNumber=accountNumberCounter)
        User.userListMap += (userAccount.userName -> user)
        sender ! "Account Created Successfully"
      }

    }
  }
}
