import scala.swing._

object QuickHealDesktop extends SimpleGUIApplication {
	def top = new MainFrame {
		title = "Quick Heal"
		val button = new Button {
			text = "Find"
		}
		val label = new Label {
			text = "No button clicks registered"
		}	
		contents = new BoxPanel(Orientation.Vertical) {
			contents += button
			contents += label
			border = Swing.EmptyBorder(30, 30, 10, 30)
		}
	}	
}