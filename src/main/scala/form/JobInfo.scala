package form

import scala.swing._
import scala.swing.BorderPanel.Position._
import service._
import dao._
class JobInfo (jobName : String , ucrNo : String , discountCode : String , businessOwner : String , devName : String)  extends Dialog {

 val pName : List[String] =   List[String](
  "JOB_NAME",
  "DISCOUNT_CODE",    
  "UCR_NO",  
  "KEYWORD ",
  "PP ",
  "SOC",
  "PROPO ",
  "ACTV_CODE ",
  "ACTV_RSN_CODE",
  "ACC_TYPE",
  "ACC_CATE",
  "BENEFIT",
  "ADVANCE_PAYMENT",
  "BUSINESS_OWNER",
  "DEV_NAME",
  "PROJECT_START_DATE",
  "PROJECT_END_DATE",
  "REMARK",
  "SYS_CREATION_DATE",
  "SYS_UPDATE_DATE")

  title = "Detail"
  modal = true
  println("job_name: %s ; ucr_no: %s; discount_code: %s; business_owner: %s; dev_name: %s".format (jobName,ucrNo,discountCode,businessOwner,devName))
  var csmDiscount = findCsmDiscount(jobName,ucrNo,discountCode,businessOwner,devName)
  println("@@ -> " + csmDiscount.toString)
  //contents = makeInfoPanel(null)
  contents = makeInfoPanel(csmDiscount)

  centerOnScreen()
  open()


  def makeInfoPanel (csmDiscount : CsmDiscount) : BoxPanel = {
    val infoPanel = new BoxPanel(Orientation.Vertical) {
      border = Swing.EmptyBorder(5,5,5,5)
      peer.setPreferredSize(new Dimension(400, 480))

      val insideInfoPanel = new GridPanel(pName.size , 2) {
        border = Swing.EmptyBorder(5,5,5,5)
        hGap = 2
        vGap = 2
        contents += new Label(pName(0) , null , Alignment.Left)
        contents += new Label(csmDiscount.jobName , null , Alignment.Left)
        contents += new Label(pName(1) , null , Alignment.Left)
        contents += new Label(csmDiscount.discountCode , null , Alignment.Left)
        contents += new Label(pName(2) , null , Alignment.Left)
        contents += new Label(csmDiscount.ucrNo , null , Alignment.Left)
        contents += new Label(pName(3) , null , Alignment.Left)
        contents += new Label(csmDiscount.keyword , null , Alignment.Left)
        contents += new Label(pName(4) , null , Alignment.Left)
        contents += new Label(csmDiscount.pp , null , Alignment.Left)
        contents += new Label(pName(5) , null , Alignment.Left)
        contents += new Label(csmDiscount.soc , null , Alignment.Left)
        contents += new Label(pName(6) , null , Alignment.Left)
        contents += new Label(csmDiscount.propo , null , Alignment.Left)
        contents += new Label(pName(7) , null , Alignment.Left)
        contents += new Label(csmDiscount.actvCode , null , Alignment.Left)
        contents += new Label(pName(8) , null , Alignment.Left)
        contents += new Label(csmDiscount.actvRsnCode , null , Alignment.Left)
        contents += new Label(pName(9) , null , Alignment.Left)
        contents += new Label(csmDiscount.accType , null , Alignment.Left)
        contents += new Label(pName(10) , null , Alignment.Left)
        contents += new Label(csmDiscount.accCate , null , Alignment.Left)
        contents += new Label(pName(11) , null , Alignment.Left)
        contents += new Label(csmDiscount.benefit , null , Alignment.Left)
        contents += new Label(pName(12) , null , Alignment.Left)
        contents += new Label(csmDiscount.advancePayment , null , Alignment.Left)
        contents += new Label(pName(13) , null , Alignment.Left)
        contents += new Label(csmDiscount.businessOwner , null , Alignment.Left)
        contents += new Label(pName(14) , null , Alignment.Left)
        contents += new Label(csmDiscount.devName , null , Alignment.Left)
        contents += new Label(pName(15) , null , Alignment.Left)
        if(csmDiscount.projectStartDate != null) {
         contents += new Label(csmDiscount.projectStartDate.toString , null , Alignment.Left)
        }
        else 
        {
          contents += new Label("" , null , Alignment.Left)
        }
       
        contents += new Label(pName(16) , null , Alignment.Left)
        if(csmDiscount.projectEndDate != null) {
         contents += new Label(csmDiscount.projectEndDate.toString , null , Alignment.Left)
        }
        else 
        {
          contents += new Label("" , null , Alignment.Left)
        }
       
        contents += new Label(pName(17) , null , Alignment.Left)
        contents += new Label(csmDiscount.remark , null , Alignment.Left)
        contents += new Label(pName(18) , null , Alignment.Left)
        if(csmDiscount.sysCreationDate != null) {
         contents += new Label(csmDiscount.sysCreationDate.toString , null , Alignment.Left)  
        }
        else 
        {
          contents += new Label("" , null , Alignment.Left)
        }
             
        contents += new Label(pName(19) , null , Alignment.Left)
        if(csmDiscount.sysUpdateDate != null) {
         contents += new Label(csmDiscount.sysUpdateDate.toString , null , Alignment.Left)
        }
        else 
        {
          contents += new Label("" , null , Alignment.Left)
        }
       
      }
      contents += insideInfoPanel
    }
    

    return infoPanel
  }
  def findCsmDiscount(jobName : String , ucrNo : String , discountCode : String , businessOwner : String , devName : String) : CsmDiscount = {
    val quickHeal = new QuickHeal()
    var csmDiscount = quickHeal.findCsmDiscount(jobName,ucrNo,discountCode,businessOwner,devName)

    return csmDiscount
  }
}