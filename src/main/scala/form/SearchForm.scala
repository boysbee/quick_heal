package form
import scala.swing._
import scala.swing.event.{TableRowsSelected, TableEvent, TableColumnsSelected, ButtonClicked,SelectionChanged,WindowClosing}
import db.{DB}
import service.{QuickHeal}
import dao.{CsmDiscount}


case class TableColumnHeaderSelected(override val source:Table, column: Int) extends TableEvent(source)
class SearchForm extends BoxPanel(Orientation.Vertical) {
	val headers = Seq("Job-Name","UCR #","Discount","Business Owner","Developer")
	
	
	val table  = new Table {
		override lazy val model = super.model.asInstanceOf[javax.swing.table.DefaultTableModel]
		selection.elementMode = Table.ElementMode.Cell
		for( header <- headers) {
			model.addColumn(header)
		}
	}

	contents += makeSearchPanel
	contents += makeResultPanel

	// -- add listener
	listenTo(table.selection)

    reactions += {
    	
		case TableRowsSelected(source, range, false) =>
		outputSelection(source, "Rows selected, changes: %s" format range)
		// case TableColumnsSelected(source, range, false) =>
		// outputSelection(source, "Columns selected, changes: %s" format range)
		case TableColumnHeaderSelected(source, column) =>
		outputSelection(source, "Column header %s selected" format column)
		// case ButtonClicked(`searchButton`) => findResult()

    }

	def makeSearchPanel() : BoxPanel = {
		
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

	def makeResultPanel() : BoxPanel = {


		// result panel
		var resultPanel = new BoxPanel(Orientation.Vertical) {		
			contents += new ScrollPane(table)
		}
		return resultPanel
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

	def outputSelection(table: Table, msg: String) {
		val rowId = table.selection.rows.leadIndex
		val colId = table.selection.columns.leadIndex
		val rows = table.selection.rows.mkString(", ")
		val cols = table.selection.columns.mkString(", ")
		val value = table.model.getValueAt(rowId,colId)
		println("%s\n  Lead: %s, %s; Rows: %s; Columns: %s ; Value: %s\n" format (msg, rowId, colId, rows, cols,value))
		// "Job-Name","UCR #","Discount","Business Owner","Developer"
		var jobName : String = table.model.getValueAt(rowId,0).toString
		var ucrNo : String = table.model.getValueAt(rowId,1).toString
		var discountCode : String  = table.model.getValueAt(rowId,2).toString
		var businessOwner : String = table.model.getValueAt(rowId,3).toString
		var devName : String = table.model.getValueAt(rowId,4).toString
		new JobInfo(jobName ,ucrNo,discountCode,businessOwner,devName)
		//output.append("%s\n  Lead: %s, %s; Rows: %s; Columns: %s\n" format (msg, rowId, colId, rows, cols))
	}
}