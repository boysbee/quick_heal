package form

import scala.swing._
import scala.swing.BorderPanel.Position._
import service._
import dao._
class JobInfo (jobName : String , ucrNo : String , discountCode : String , businessOwner : String , devName : String)  extends Dialog {

 

  title = "Detail"
  modal = true
  println("job_name: %s ; ucr_no: %s; discount_code: %s; business_owner: %s; dev_name: %s".format (jobName,ucrNo,discountCode,businessOwner,devName))
  var csmDiscount = findCsmDiscount(jobName,ucrNo,discountCode,businessOwner,devName)
  centerOnScreen()
  open()

  def findCsmDiscount(jobName : String , ucrNo : String , discountCode : String , businessOwner : String , devName : String) : CsmDiscount = {
    val quickHeal = new QuickHeal()
    var csmDiscount = quickHeal.findCsmDiscount(jobName,ucrNo,discountCode,businessOwner,devName)

    return csmDiscount
  }
}