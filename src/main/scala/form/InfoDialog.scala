package form

import scala.swing._
import dao._

class InfoDialog (jobName : String , ucrNo : String , discountCode : String , businessOwner : String , devName : String)  extends Dialog {
	title = "Detail"
	modal = true
	println("job_name: %s ; ucr_no: %s; discount_code: %s; business_owner: %s; dev_name: %s".format (jobName,ucrNo,discountCode,businessOwner,devName))

	// creat detail panel
	contents = makeInfoDialog

	centerOnScreen()
	open()

	def makeInfoDialog : BoxPanel = {
		import form._
		val infoPanel = new BoxPanel(Orientation.Vertical) {
			var info = new JobInfo(jobName ,ucrNo,discountCode,businessOwner,devName)
			contents += info
		}
		return infoPanel
	}
}