import scala.swing._
import scala.swing.event.{TableRowsSelected, TableEvent, TableColumnsSelected, ButtonClicked}
 
case class TableColumnHeaderSelected(override val source:Table, column: Int) extends TableEvent(source)

object QuickHealDesktop extends SimpleSwingApplication {
	setSystemLookAndFeel()

	var searchPanel = makeSearchPanel()

	var resultPanel = makeResultPanel()


 
    reactions += {
		case TableRowsSelected(source, range, false) =>
		outputSelection(source, "Rows selected, changes: %s" format range)
		case TableColumnsSelected(source, range, false) =>
		outputSelection(source, "Columns selected, changes: %s" format range)
		case TableColumnHeaderSelected(source, column) =>
		outputSelection(source, "Column header %s selected" format column)
		case e => println("%s => %s" format(e.getClass.getSimpleName, e.toString))
    }

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
		val headers = Array.tabulate(10) {"Col-" + _}.toSeq
		val rowData = Array.tabulate[Any](10, 10) {"" + _ + ":" + _}
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

		listenTo(table.selection)
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
}