package form

import scala.swing._

class TestJob extends BoxPanel(Orientation.Vertical){
	peer.setPreferredSize(new Dimension(640, 480))
	val ui = new BoxPanel(Orientation.Vertical) {
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
		contents += findPanel
		contents += new FlowPanel {
			contents += new Label("Result : ", null , Alignment.Left)
		}

		contents += new BoxPanel(Orientation.Vertical) {
			contents += new Label("Proposition", null , Alignment.Left)
			val propositionTable = new Table {
				preferredSize = new Dimension(100, 100)
				override lazy val model = super.model.asInstanceOf[javax.swing.table.DefaultTableModel]
				selection.elementMode = Table.ElementMode.Cell
				
				model.addColumn("Proposition Code")
				model.addColumn("Result")
			}
			// contents += propositionTable
			contents += new ScrollPane(propositionTable)
		}

		contents += new BoxPanel(Orientation.Vertical) {
			contents += new Label("Soc", null , Alignment.Left)
			val socTable = new Table {
				preferredSize = new Dimension(100, 100)
				override lazy val model = super.model.asInstanceOf[javax.swing.table.DefaultTableModel]
				selection.elementMode = Table.ElementMode.Cell
				
				model.addColumn("Soc Code")
				model.addColumn("Result")
			}
			// contents += socTable
			contents += new ScrollPane(socTable)
		}

		contents += new BoxPanel(Orientation.Vertical) {
			contents += new Label("Price Plan", null , Alignment.Left)
			val pricePlanTable = new Table {
				preferredSize = new Dimension(100, 100)
				override lazy val model = super.model.asInstanceOf[javax.swing.table.DefaultTableModel]
				selection.elementMode = Table.ElementMode.Cell
				
				model.addColumn("Price Plan")
				model.addColumn("Result")
			}
			// contents += pricePlanTable
			contents += new ScrollPane(pricePlanTable)
		}
	}

	contents += ui
}