package form

import scala.swing._
import scala.swing.BorderPanel.Position._
import service._
import dao._
import scala.collection.mutable.ListBuffer
class JobInfo (csmDiscount : CsmDiscount)  extends BoxPanel(Orientation.Vertical) {

 val pName : List[String] =   List[String](
  "JOB_NAME",  "DISCOUNT_CODE",  "UCR_NO",    "KEYWORD ",  "PP ",  "SOC",  "PROPO ",  "ACTV_CODE ",  "ACTV_RSN_CODE",  "ACC_TYPE",
  "ACC_CATE",  "BENEFIT",  "ADVANCE_PAYMENT",  "BUSINESS_OWNER",  "DEV_NAME",  "PROJECT_START_DATE",
  "PROJECT_END_DATE",  "REMARK",  "SYS_CREATION_DATE",  "SYS_UPDATE_DATE")

  println("job_name: %s ; ucr_no: %s; discount_code: %s; business_owner: %s; dev_name: %s".format (csmDiscount.jobName,csmDiscount.ucrNo,csmDiscount.discountCode,csmDiscount.businessOwner,csmDiscount.devName))
  //contents = makeInfoPanel(null)
  contents += makeInfoPanel(csmDiscount)


  def makeInfoPanel (csmDiscount : CsmDiscount) : BoxPanel = {
    val infoPanel = new BoxPanel(Orientation.Vertical) {
      border = Swing.EmptyBorder(5,5,5,5)
      peer.setPreferredSize(new Dimension(400, 480))

      val insideInfoPanel = new GridPanel(pName.size , 2) {
        border = Swing.EmptyBorder(5,5,5,5)
        hGap = 2
        vGap = 2
        // Job Name
        contents += new Label(pName(0) , null , Alignment.Left)
        contents += new TextField(csmDiscount.jobName , 10 ) {
          editable = false
        }
        // Discount Code
        contents += new Label(pName(1) , null , Alignment.Left)
        contents += new TextField(csmDiscount.discountCode  , 10 ) {
          editable = false
        }
        // UCR No
        contents += new Label(pName(2) , null , Alignment.Left)
        contents += new TextField(csmDiscount.ucrNo ,  10 ) {
          editable = false
        }
        // Keyword
        contents += new Label(pName(3) , null , Alignment.Left)
        var keywordList = new ListBuffer[String]()
        var keyword = csmDiscount.keyword
        if(keyword != null) {
          keyword.split(",").foreach( keywordList += _)  
        }
        
        contents += new ListView(keywordList)
        // PP
        contents += new Label(pName(4) , null , Alignment.Left)
        var ppList = new ListBuffer[String]()
        var pp = csmDiscount.pp
        if(pp != null) {
          pp.split(",").foreach( ppList += _)  
        }
        contents += new ListView(ppList)
        // SOC

        contents += new Label(pName(5) , null , Alignment.Left)
        var socList = new ListBuffer[String]()
        var soc = csmDiscount.soc
        if(soc != null) {
          soc.split(",").foreach( socList += _)  
        }
        contents += new ListView(socList)
        // Proposition
        contents += new Label(pName(6) , null , Alignment.Left)
        var propoList = new ListBuffer[String]()
        var propo = csmDiscount.propo
        if(propo != null) {
          propo.split(",").foreach( propoList += _)  
        }
        contents += new ListView(propoList)
        // Actv Code
        contents += new Label(pName(7) , null , Alignment.Left)
        contents += new TextField(csmDiscount.actvCode  , 10 ) {
          editable = false
        }
        // Actv Rsn Code
        contents += new Label(pName(8) , null , Alignment.Left)
        contents += new TextField(csmDiscount.actvRsnCode  , 10 ) {
          editable = false
        }
        // Acc Type
        contents += new Label(pName(9) , null , Alignment.Left)
        contents += new TextField(csmDiscount.accType  , 10 ) {
          editable = false
        }
        // Acc Cate
        contents += new Label(pName(10) , null , Alignment.Left)
        contents += new TextField(csmDiscount.accCate , 10 ) {
          editable = false
        }
        // Benefit
        contents += new Label(pName(11) , null , Alignment.Left)
        contents += new TextField(csmDiscount.benefit  , 10 ) {
          editable = false
        }
        // Advance Payment
        contents += new Label(pName(12) , null , Alignment.Left)
        contents += new TextField(csmDiscount.advancePayment  , 10 ) {
          editable = false
        }
        // Business Owner
        contents += new Label(pName(13) , null , Alignment.Left)
        contents += new TextField(csmDiscount.businessOwner  , 10 ) {
          editable = false
        }
        // Dev Name
        contents += new Label(pName(14) , null , Alignment.Left)
        contents += new TextField(csmDiscount.devName  , 10 ) {
          editable = false
        }
        // Project Start 
        contents += new Label(pName(15) , null , Alignment.Left)
        if(csmDiscount.projectStartDate != null) {
            contents += new TextField(csmDiscount.projectStartDate.toString , 10 ) {
            editable = false
          }
        }
        else 
        {
          contents += new TextField("" , 10 ) {
            editable = false
          }
        }
        // Project End
        contents += new Label(pName(16) , null , Alignment.Left)
        if(csmDiscount.projectEndDate != null) {
            contents += new TextField(csmDiscount.projectEndDate.toString  , 10 ) {
            editable = false
          }
        }
        else 
        {
          contents += new TextField("" , 10 ) {
            editable = false
          }
        }
        // Remark
        contents += new Label(pName(17) , null , Alignment.Left)
        contents += new TextField(csmDiscount.remark  , 10 ) {
          editable = false
        }
        // Sys Creation Date
        contents += new Label(pName(18) , null , Alignment.Left)
        if(csmDiscount.sysCreationDate != null) {
            contents += new TextField(csmDiscount.sysCreationDate.toString  , 10 ) {
            editable = false
          }
        }
        else 
        {
          contents += new TextField("" , 10 ) {
            editable = false
          }
        }
        // Sys update Date
        contents += new Label(pName(19) , null , Alignment.Left)
        if(csmDiscount.sysUpdateDate != null) {
          contents += new TextField(csmDiscount.sysUpdateDate.toString  , 10 ) {
            editable = false
          }
        }
        else 
        {
          contents += new TextField("" ,  10 ) {
            editable = false
          }
        }
       
      }
      contents += insideInfoPanel
    }
    

    return infoPanel
  }
  
}