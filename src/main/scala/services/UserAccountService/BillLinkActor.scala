package services.UserAccountService

import akka.actor.Actor
import models.Biller

import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 23/3/17.
  */

class BillLinkActor extends Actor {
  override def receive: PartialFunction[Any, Unit] = {
    case biller: Biller => {
      if (User.billerListMap.toList != Nil) {

        val list = User.userListMap.values.toList
        for (ls <- list) {

          if (biller.billerName == ls.userName) {
               println("hello  !")
            if (User.billerListMap.contains(biller.billerName)) {
              println("hello  2")
              val billerBuffer = User.billerListMap.get(ls.userName)
              println(billerBuffer)
              for (i <- billerBuffer) {
                println(i)
                for (j <- i) {
                  println(j)
                  if (j.billerName == biller.billerName&& j.billerCategory == biller.billerCategory) {
                    println(j)
                    val newBiller = j.copy(transactionDate = biller.transactionDate, amount = biller.amount, paidAmount = j.paidAmount + j.amount, executedIterations = j.executedIterations + 1)
                    i -= j
                    i.append(newBiller)
                  }
                  else {
                    i.append(biller)
                  }
                }
              }
            }
            else{
              println("esle")
              User.billerListMap +=(biller.billerName->ListBuffer(biller))
            }

          }
        }
      }
      else {
        User.billerListMap +=(biller.billerName ->ListBuffer(biller) )
      }
    }
    case _ => println("Incorrect Input")
  }
}
