package form
import scala.swing._
import scala.swing.event.{TableRowsSelected, TableEvent, TableColumnsSelected, ButtonClicked,SelectionChanged,WindowClosing}
import db.{DB}
import service.{QuickHeal}
import dao.{CsmDiscount}

class InsertForm extends BoxPanel(Orientation.Vertical) {

	contents += makeInsertPanel

	// make insert field panel
	def makeInsertPanel : BorderPanel = {
		// job name text field
		val jobNameTextField = new TextField {
			columns = 10
		}
		// discound code text field
		val discountCodeTextField = new TextField {
			columns = 10
		}
		// ucr no text field
		val ucrNoTextField = new TextField {
			columns = 10
		}
		// business owner text field
		val businessOwnerTextField = new TextField {
			columns = 10
		}
		// dev name text field
		val devNameTextField = new TextField {
			columns = 10
		}
		var insertPanel = new GridPanel(6,2) {
			contents += new Label("Job Name :")
			contents += jobNameTextField
			contents += new Label("Discount Code :")
			contents += discountCodeTextField
			contents += new Label("UCR NO# :")
			contents += ucrNoTextField
			contents += new Label("Business Owner :")
			contents += businessOwnerTextField
			contents += new Label("Deveoper :")
			contents += devNameTextField

			contents += new Button(Action ("Insert"){
				
				println("@@ do insert data.")
				var jobName = jobNameTextField.text
				var discountCode = discountCodeTextField.text
				var devName = devNameTextField.text
				var businessOwner = businessOwnerTextField.text
				var ucrNo = ucrNoTextField.text
				val quickHeal = new QuickHeal

				var data: CsmDiscount = CsmDiscount(jobName,discountCode,ucrNo,businessOwner,"",devName ,"")
				var success = quickHeal.insertToCsmDiscount(data);
				println("@@ save success : " + success)
			})
		}
		var mainPanel = new BorderPanel {
			add(insertPanel,  BorderPanel.Position.Center)
		}

		return mainPanel
	}
}