package form

import scala.swing._
import dao._
import service._
class InfoDialog (jobName : String , ucrNo : String , discountCode : String , businessOwner : String , devName : String)  extends Dialog {

  val quickHeal = new QuickHeal()

  var csmDiscount = quickHeal.findCsmDiscount(jobName,ucrNo,discountCode,businessOwner,devName)
  
	val ui = new BoxPanel(Orientation.Vertical) {
      val tabs = new TabbedPane {
        import TabbedPane._
        
        pages += new Page("Job Info",new BoxPanel(Orientation.Vertical) {
         
        	contents += new JobInfo(csmDiscount)
      	} )
        pages += new Page("Test",new BoxPanel(Orientation.Vertical) {
        	contents +=	new TestJob(csmDiscount)
       	})
        //pages += new Page("Text Editor", TextEditor.ui)
      }
      contents += tabs
	}

	title = "Detail"
	modal = true
	println("job_name: %s ; ucr_no: %s; discount_code: %s; business_owner: %s; dev_name: %s".format (jobName,ucrNo,discountCode,businessOwner,devName))

	// creat detail panel
	contents = ui

	centerOnScreen()
	open()


}