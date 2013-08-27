package form
import scala.swing._
import scala.swing.event.{TableRowsSelected, TableEvent, TableColumnsSelected, ButtonClicked,SelectionChanged,WindowClosing}
import db.{DB}
import service.{QuickHeal}
import dao.{CsmDiscount}
import util.{DateUtil}

class InsertForm extends BoxPanel(Orientation.Vertical) {
	preferredSize = new Dimension(640, 480)

	contents += makeInsertPanel

	// make insert field panel
	def makeInsertPanel : BorderPanel = {
		preferredSize = new Dimension(400, 300)
		// job name text field
		val jobNameTextField = new TextField ("",10)
		// discound code text field
		val discountCodeTextField = new TextField ("",10)
		// ucr no text field
		val ucrNoTextField = new TextField ("",10)
		// business owner text field
		val businessOwnerTextField = new TextField ("",10)
		// dev name text field
		val devNameTextField = new TextField ("",10)
		val keywordTextField = new TextField ("",10)
		var pricePlanTextField = new TextField ("",10)
		var socTextField = new TextField ("",10)
		var propositionCodeTextField = new TextField ("",10)
		var actvCodeTextField = new TextField ("",10)
		var actvRsnCodeTextField = new TextField ("",10)
		var benefitTextField = new TextField ("",10)
		var advancePaymentTextField = new TextField ("",10)
		var accCateTextField = new TextField ("",10)
		var accTypeTextField = new TextField ("",10)
		var remarkTextField = new TextField ("",10)
		var projectStartDateTextField = new TextField ("",10)
		var projectEndDateTextField = new TextField ("",10)
		var insertPanel = new GridPanel(11,4) {
			hGap = 3
			vGap = 3
			contents += new Label("Job Name :")
			contents += jobNameTextField
			contents += new Label("Discount Code :")
			contents += discountCodeTextField
			contents += new Label("UCR NO# :")
			contents += ucrNoTextField
			contents += new Label("Business Owner :")
			contents += businessOwnerTextField
			contents += new Label("Keyword : ")
			contents += keywordTextField
			contents += new Label("Deveoper :")
			contents += devNameTextField
			contents += new Label("Price Plan : ")
			contents += pricePlanTextField
			contents += new Label("Soc : ")
			contents += socTextField
			contents += new Label("Proposition Code : ")
			contents += propositionCodeTextField
			contents += new Label("ACTV_CODE : ")
			contents += actvCodeTextField
			contents += new Label("ACTV_RSN_CODE : ")
			contents += actvRsnCodeTextField
			contents += new Label("Account Category : " )
			contents += accCateTextField
			contents += new Label("Account Type : " )
			contents += accTypeTextField
			contents += new Label("Benefit : ")
			contents += benefitTextField
			contents += new Label("Advance Payment : ")
			contents += advancePaymentTextField
			contents += new Label("Remark : ")
			contents += remarkTextField
			contents += new Label("Project Start Date : ")
			contents += projectStartDateTextField
			contents += new Label("Project End Date : ")
			contents += projectEndDateTextField
			contents += new Label("")
			contents += new Button(Action ("Insert"){
				
				println("@@ do insert data.")
				var jobName = jobNameTextField.text
				var discountCode = discountCodeTextField.text
				var devName = devNameTextField.text
				var businessOwner = businessOwnerTextField.text
				var ucrNo = ucrNoTextField.text
				var keyword = keywordTextField.text
				var pp = pricePlanTextField.text
				var soc = socTextField.text
				var propo = propositionCodeTextField.text
				var actvCode = actvCodeTextField.text
				var actvRsnCode = actvRsnCodeTextField.text
				var accCate = accCateTextField.text
				var accType = accTypeTextField.text
				var benefit = benefitTextField.text
				var advancePayment = advancePaymentTextField.text
				var remark = remarkTextField.text

				var projectStartDateStr = projectStartDateTextField.text
				var projectEndDateStr = projectEndDateTextField.text
				var projectStartDateComplete : java.util.Date = DateUtil.str2Date(projectStartDateStr,"dd/MM/yyyy")
				var projectEndDateComplete : java.util.Date = DateUtil.str2Date(projectEndDateStr,"dd/MM/yyyy")
				val quickHeal = new QuickHeal

				var data: CsmDiscount = CsmDiscount(jobName,discountCode,ucrNo,businessOwner,
					keyword,devName ,remark,pp,propo  , soc  , actvCode , actvRsnCode , accType  ,
					accCate , benefit  , advancePayment  ,projectStartDateComplete,projectEndDateComplete)

				var success = quickHeal.insertToCsmDiscount(data);
				if( success ) {
					Dialog.showMessage(this, "Insert to CSM_DISCOUNT success", "Insert Success", Dialog.Message.Info)
				}
				else 
				{
					Dialog.showMessage(this, "Insert to CSM_DISCOUNT not successfully!", "Insert Error", Dialog.Message.Error)
				}
				println("@@ save success : " + success)
			})
		}
		var mainPanel = new BorderPanel {
			add(insertPanel,  BorderPanel.Position.Center)
		}

		return mainPanel
	}
}