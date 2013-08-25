package form

import scala.swing._
import dao._

class JobSelect extends BoxPanel(Orientation.Vertical) {
	peer.setPreferredSize(new Dimension(640, 580))
	val ui = new BoxPanel(Orientation.Vertical){
		val findPanel = new FlowPanel {
			contents += new Label("Fill detail for test : ")
			contents += new Label("")
			contents += new Label("Ban:", null , Alignment.Left)
			val banTextField = new TextField("",15)
			contents += banTextField
			contents += new Label("Subscriber No :", null , Alignment.Left)
			val subTextField = new TextField("",10)
			contents += subTextField
			contents += new Button(Action("Find") {
				println("@@ do find!")
			});
		}
		
	}

	contents += ui
}