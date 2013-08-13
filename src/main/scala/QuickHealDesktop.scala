import scala.swing._
import scala.swing.event.{TableRowsSelected, TableEvent, TableColumnsSelected, ButtonClicked,SelectionChanged,WindowClosing}
 
case class TableColumnHeaderSelected(override val source:Table, column: Int) extends TableEvent(source)


object QuickHealDesktop extends SimpleSwingApplication {
	setSystemLookAndFeel()

	val headers = Seq("Job-Name","UCR #","Discount","Business Owner","Developer")
	val rowData = Array[Array[Any]](List("CSADDCSEGMENT","TTHP-966BGE","DIR040","Thitaree Thongnamsap","Nattaporn Chatmalairut").toArray,
		List("CSDCMNPO2R","SNGK-94U6HP", "DIR040","Surisara Ngamtragoonsuk","Nattaporn Chatmalairut").toArray,
		List("CSDCMNPO2R","SNGK-94U6HP","DIR042","Surisara Ngamtragoonsuk","Nattaporn Chatmalairut").toArray)
	
	
	val table  = new Table(rowData, headers){
		selection.elementMode = Table.ElementMode.Cell

	val header = {
		import java.awt.event.{MouseEvent, MouseAdapter}

		val makeHeaderEvent = TableColumnHeaderSelected(this, _:Int)
		val tableHeader = peer.getTableHeader
		tableHeader.addMouseListener(new MouseAdapter() {
			override def mouseClicked(e: MouseEvent) {
				selection.publish(makeHeaderEvent(tableHeader.columnAtPoint(e.getPoint)))
			}
		})
		tableHeader
		}
	}
 

	// search panel select
	val searchLabel = new Label {
		text = "Select a search criteria :"
	}
	// search text field
	var searchTextField = new TextField {
		columns = 10
	}
	// search criteria combo box
	val searchSelect = new ComboBox(List("Discount Code","Job Name"))
	// find button
	val searchButton = new Button {
		text = "Find"
	}

	// -- add listener
	listenTo(table.selection)
	listenTo(searchButton)
	listenTo(searchSelect.selection)


    reactions += {
    	case WindowClosing(e) => System.exit(0)
		case TableRowsSelected(source, range, false) =>
		outputSelection(source, "Rows selected, changes: %s" format range)
		case TableColumnsSelected(source, range, false) =>
		outputSelection(source, "Columns selected, changes: %s" format range)
		case TableColumnHeaderSelected(source, column) =>
		outputSelection(source, "Column header %s selected" format column)
		case ButtonClicked(`searchButton`) => findResult()
		case SelectionChanged(`searchSelect`) => searchSelectType()
		//case e => println("%s => %s" format(e.getClass.getSimpleName, e.toString))

    }


	var searchPanel = makeSearchPanel()

	var resultPanel = makeResultPanel()

	def top = new MainFrame {
		title = "Quick Heal"
			
		contents = new BoxPanel(Orientation.Vertical) {
			contents += searchPanel
			contents += resultPanel
			border = Swing.EmptyBorder(0, 0, 0, 0)
		}
	}

	def setSystemLookAndFeel() {
		import javax.swing.UIManager
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
	}

	def makeSearchPanel() : FlowPanel = {

		// search panel
		var searchPanel = new FlowPanel {
			contents += searchLabel
			contents += searchTextField
			contents += searchSelect
			contents += searchButton

		}
		return searchPanel;
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

	def findResult(){
		val searchText = searchTextField.text
		if( "" equals searchType) {
			searchType = "Discount Code"
		}
		println("search : %s , search type : %s" format ( searchText , searchType))
		val quickHeal = new QuickHeal
		var result = Array[Array[Any]]()
		searchType match {

			case "Discount Code" => result =  quickHeal.findJobListByDiscountCode(searchText)
			case "Job-Name" => result = quickHeal.findJobListByJobName(searchText)
		}
		
	}

	// selected valud from searchSelect
	var searchType = ""
	def searchSelectType() {
		val selected = searchSelect.selection.item 

		if( "" equals selected) {
			searchType = "Discount Code"
		}
		else
		{
			searchType = selected
		}
	}
}