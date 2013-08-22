import scala.swing._
import scala.swing.event.{TableRowsSelected, TableEvent, TableColumnsSelected, ButtonClicked,SelectionChanged,WindowClosing}
 
case class TableColumnHeaderSelected(override val source:Table, column: Int) extends TableEvent(source)


object QuickHealDesktop extends SimpleSwingApplication {
	setSystemLookAndFeel()


	val headers = Seq("Job-Name","UCR #","Discount","Business Owner","Developer")
	// val rowData = Array[Array[Any]](List("CSADDCSEGMENT","TTHP-966BGE","DIR040","Thitaree Thongnamsap","Nattaporn Chatmalairut").toArray,
	// 	List("CSDCMNPO2R","SNGK-94U6HP", "DIR040","Surisara Ngamtragoonsuk","Nattaporn Chatmalairut").toArray,
	// 	List("CSDCMNPO2R","SNGK-94U6HP","DIR042","Surisara Ngamtragoonsuk","Nattaporn Chatmalairut").toArray)
	
	
	val table  = new Table {
		override lazy val model = super.model.asInstanceOf[javax.swing.table.DefaultTableModel]
		selection.elementMode = Table.ElementMode.Cell
		for( header <- headers) {
			model.addColumn(header)
		}
		//model.addRow(Array[AnyRef]("CSADDCSEGMENT","TTHP-966BGE","DIR040","Thitaree Thongnamsap","Nattaporn Chatmalairut"))
		// val header = {
		// import java.awt.event.{MouseEvent, MouseAdapter}

		// val makeHeaderEvent = TableColumnHeaderSelected(this, _:Int)
		// val tableHeader = peer.getTableHeader
		// tableHeader.addMouseListener(new MouseAdapter() {
		// 	override def mouseClicked(e: MouseEvent) {
		// 		selection.publish(makeHeaderEvent(tableHeader.columnAtPoint(e.getPoint)))
		// 	}
		// })
		// tableHeader
		// }
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


	

	def top = new MainFrame {
		title = "Quick Heal"
		size = new Dimension(800,600)
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
				peer.setPreferredSize(new Dimension(640, 480));
				contents += makeInsertPanel
				border = Swing.EmptyBorder(0, 0, 0, 0)
			}
		}

		def callSearchForm(){
			contents = new BoxPanel(Orientation.Vertical) {
				peer.setPreferredSize(new Dimension(640, 480));
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
				import db.DB
				println("@@ do insert data.")
				var jobName = jobNameTextField.text
				var discountCode = discountCodeTextField.text
				var devName = devNameTextField.text
				var businessOwner = businessOwnerTextField.text
				var ucrNo = ucrNoTextField.text
				// val url = "jdbc:oracle:thin:@172.16.49.14:1521:TEST01"
				val url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl"
				val user = "amdapp22"
				val pass = "amdapp22"

				val database = DB(url,user,pass)
				val conn = database.connect()
				var data: CsmDiscount = new CsmDiscount(jobName,discountCode,ucrNo,businessOwner,"",devName ,"")
				var success = CsmDiscount.insert(conn,data)
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

	def findResult(){
		val searchText = searchTextField.text
		if( "" equals searchType) {
			searchType = "Discount Code"
		}
		println("search : %s , search type : %s" format ( searchText , searchType))
		val quickHeal = new QuickHeal
		var result = List[CsmDiscount]()
		searchType match {
			case "Discount Code" => result =  quickHeal.findJobListByDiscountCode(searchText)
			case "Job Name" => result = quickHeal.findJobListByJobName(searchText)
		}
		
		update(result)
	}


	def update(result :List[CsmDiscount]) = {
		var model = table.model
		for(item <- result) {
			model.addRow(Array[AnyRef](item.jobName,item.ucrNo,item.discountCode,item.businessOwner,item.devName))
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
