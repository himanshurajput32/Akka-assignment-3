package services.UserAccountService

import models.{Biller, UserAccount}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by knoldus on 23/3/17.
  */
object User {
  val userListMap: mutable.Map[String, UserAccount] = scala.collection.mutable.Map[String, UserAccount]()
  val billerListMap: mutable.Map[String,ListBuffer[Biller]] = scala.collection.mutable.Map[String, ListBuffer[Biller]]()
//  def reportsGenerate(): Unit ={
//    billerListMap.
//  }
}
