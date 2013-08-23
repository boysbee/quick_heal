package main

import scala.swing._
import scala.swing.event.{TableRowsSelected, TableEvent, TableColumnsSelected, ButtonClicked,SelectionChanged,WindowClosing}
import db.{DB}
import service.{QuickHeal}
import dao.{CsmDiscount}

case class TableColumnHeaderSelected(override val source:Table, column: Int) extends TableEvent(source)


object QuickHealDesktop extends SimpleSwingApplication {
	setSystemLookAndFeel()


	val headers = Seq("Job-Name","UCR #","Discount","Business Owner","Developer")
	
	
	val table  = new Table {
		override lazy val model = super.model.asInstanceOf[javax.swing.table.DefaultTableModel]
		selection.elementMode = Table.ElementMode.Cell
		for( header <- headers) {
			model.addColumn(header)
		}
	}
 

	

	// -- add listener
	listenTo(table.selection)
	


    reactions += {
    	case WindowClosing(e) => System.exit(0)
		case TableRowsSelected(source, range, false) =>
		outputSelection(source, "Rows selected, changes: %s" format range)
		case TableColumnsSelected(source, range, false) =>
		outputSelection(source, "Columns selected, changes: %s" format range)
		case TableColumnHeaderSelected(source, column) =>
		outputSelection(source, "Column header %s selected" format column)
		// case ButtonClicked(`searchButton`) => findResult()

    }


	

	def top = new MainFrame {
		title = "Quick Heal"
		size = new Dimension(640,480)
		location  = new java.awt.Point((640/2), (480/2))
	    menuBar = new MenuBar {
			contents += new Menu("Action") {
				contents += new MenuItem(Action("Search") {
					println("Action 'Search' invoked")
					callSearchForm
				})
				contents += new MenuItem(Action("Insert Job"){
					println("Action 'Insert' invoked")	
					callInsertForm
				})
				contents += new MenuItem(Action("Test Criteria") {
					println("Action 'Test' invoked")
					callTestForm
				})
				
			}
			contents += new Menu("Help")
		}		
		

		def callInsertForm(){
			contents = new BoxPanel(Orientation.Vertical) {
				peer.setPreferredSize(new Dimension(640, 480))
				contents += makeInsertPanel
				border = Swing.EmptyBorder(0, 0, 0, 0)
			}
		}

		def callSearchForm(){
			contents = new BoxPanel(Orientation.Vertical) {
				peer.setPreferredSize(new Dimension(640, 480))
				var searchPanel = makeSearchPanel()

				var resultPanel = makeResultPanel()

				contents += searchPanel
				contents += resultPanel
				border = Swing.EmptyBorder(0, 0, 0, 0)
			}
		}

		def callTestForm(){
			contents = new BoxPanel(Orientation.Vertical) {
				peer.setPreferredSize(new Dimension(640, 480));
				border = Swing.EmptyBorder(0, 0, 0, 0)
			}
		}


	}

	
	def setSystemLookAndFeel() {
		import javax.swing.UIManager
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
	}

	def makeSearchPanel() : BoxPanel = {
		//peer.setPreferredSize(new Dimension(200, 150))
			// search panel select
		val discountLabel = new Label {
			text = "Discount : "
		}
		// search text field
		var discountTextField = new TextField {
			columns = 10
		}
		var jobNameLabel = new Label {
			text = "Job Name : "
		}
		val jobNameTextField = new TextField {
			columns = 10
		}

		val keywordLabel = new Label {
			text = "Keyword :"
		}
		val keywordTextField = new TextField {
			columns = 10
		}

		val ppLabel = new Label {
			text = "PP :"
		}
		val ppTextField = new TextField {
			columns = 10
		}

		val socLabel = new Label {
			text = "Soc :"
		}
		val socTextField = new TextField {
			columns = 10
		}

		val propoLabel = new Label {
			text = "Proposition :"
		}
		val propoTextField = new TextField {
			columns = 10
		}

		// find button
		val searchButton = new Button(Action("Find"){
			println("@@ do insert data.")
			var jobName = jobNameTextField.text
			var discountCode = discountTextField.text
			var keyword = keywordTextField.text
			var pp = ppTextField.text
			var soc = socTextField.text
			var propo = propoTextField.text
			val quickHeal = new QuickHeal
			var result = List[CsmDiscount]()

			result = quickHeal.findCsmDiscount(jobName,discountCode,keyword,pp,soc,propo)
		
			update(result)

		})

		var searchPanel = new GridPanel(4,4) {
			
			contents += discountLabel
			contents += discountTextField
			contents += jobNameLabel
			contents += jobNameTextField
			contents += keywordLabel 
			contents += keywordTextField
			contents += ppLabel
			contents += ppTextField
			contents += socLabel
			contents += socTextField
			contents += propoLabel
			contents += propoTextField
			contents += searchButton
			contents += new Label("")
			
		}
		
		var searcMainPanel = new BoxPanel(Orientation.Vertical) {
			contents += searchPanel
		}
		
		return searcMainPanel;
	}
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

	def makeTestPanel : FlowPanel = {
		var testPanel = new FlowPanel {

		}
		return testPanel
	}

	
	def makeResultPanel() : BoxPanel = {


		// result panel
		var resultPanel = new BoxPanel(Orientation.Vertical) {		
			contents += new ScrollPane(table)
		}
		return resultPanel
	}

	def outputSelection(table: Table, msg: String) {
		val rowId = table.selection.rows.leadIndex
		val colId = table.selection.columns.leadIndex
		val rows = table.selection.rows.mkString(", ")
		val cols = table.selection.columns.mkString(", ")
		println("%s\n  Lead: %s, %s; Rows: %s; Columns: %s\n" format (msg, rowId, colId, rows, cols))
		//output.append("%s\n  Lead: %s, %s; Rows: %s; Columns: %s\n" format (msg, rowId, colId, rows, cols))
	}



	def update(result :List[CsmDiscount]) = {
		var model = table.model
		var rowCount = model.getRowCount
		println("@@ previous result count : %d".format(rowCount))
		model.setNumRows(0)
		for(item <- result) {
			model.addRow(Array[AnyRef](item.jobName,item.ucrNo,item.discountCode,item.businessOwner,item.devName))
		}
		
		
	}
}
