import scala.swing._

object QuickHealDesktop extends SimpleSwingApplication {
	setSystemLookAndFeel()
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
	// result panel
	var resultPanel = new BoxPanel(Orientation.Vertical) {
		
		contents += new ScrollPane(new Table())
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
}