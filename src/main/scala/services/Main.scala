package services

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout
import models.{Biller, UserAccount}
import services.SalaryDepositService.SalaryDepositActor
import services.UserAccountService.{BillLinkActor, User, UserAccountGenerator}

import scala.collection.mutable
import scala.concurrent.duration.DurationDouble







object Main extends App {
  val system = ActorSystem("test")
  val props = Props[UserAccountGenerator]
  val ref = system.actorOf(props)
  implicit val timeout = Timeout(1000 seconds)
  val list = List(UserAccount("", "", "a", 50)
    , UserAccount("", "", "a", 2)
    , UserAccount("", "", "b", 60)
  )
  val res = list.map(x => ref ? x)
  res.foreach(println)
  val ref1 = system.actorOf(Props[SalaryDepositActor])
  ref1 ! List(("a",1,100),("b",2,200))
  val ref2 = system.actorOf(Props[BillLinkActor])
  ref2 ! Biller("food","a",1,"1223",20,12,1,0)
  ref2 ! Biller("food","b",1,"1223",20,12,1,100)
  ref2 ! Biller("internet","b",1,"1223",20,12,1,100)
  println(User.userListMap)
  println(User.billerListMap)
}
