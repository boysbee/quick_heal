package main

import scala.swing._
import scala.swing.event.{TableRowsSelected, TableEvent, TableColumnsSelected, ButtonClicked,SelectionChanged,WindowClosing}
import db.{DB}
import service.{QuickHeal}
import dao.{CsmDiscount}

case class TableColumnHeaderSelected(override val source:Table, column: Int) extends TableEvent(source)


object QuickHealDesktop extends SimpleSwingApplication {
	setSystemLookAndFeel()



    reactions += {
    	case WindowClosing(e) => System.exit(0)
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
			contents = new form.InsertForm
		}

		
		def callSearchForm() {
			contents = new form.SearchForm
		}

		def callTestForm(){
			// contents = new form.TestJob
		}


	}

	
	def setSystemLookAndFeel() {
		import javax.swing.UIManager
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
	}

}
