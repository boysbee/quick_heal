import org.scalatest._
import org.scalatest.matchers._
class CsmDiscountSpec extends FreeSpec with GivenWhenThen with ShouldMatchers {
	import db._
	def fixture =
		new {
			val url = "jdbc:oracle:thin:@172.16.49.14:1521:TEST01"
			val user = "amdapp22"
			val pass = "amdapp22"

			val db = DB(url,user,pass)
			val conn = db.connect()
	}

	"A CsmDiscount" - {
		"It should find with discount code" in {

			Given("a discount code")
			val discountCode = "DIR040"
			When("findWithDiscount")
			var list = CsmDiscount.findWithDiscount(fixture.conn,discountCode)
			Then("return list not null")
			assert(null != list)

		}

		"It should find with job name" in {
			Given("a job name")
			val jobName = "CSDCMNPO2R"

			When("findWithJobName")
			val list = CsmDiscount.findWithJobName(fixture.conn,jobName)

			Then("return list not null")
			assert(null != list)

			for(i <- list) {
				println("@@ -> : %s".format( i.toString) )
			}

		}

		"It should insert new record" in {
			Given("a new data detail")
			var jobName = "CSDCISMRT699"
			var discountCode = "DIR041"
			var devName = "Nattaporn Chatmalairut"
			var businessOwner = "Kanyarat Pornchaikulwattana"
			var ucrNo = "KPOA-96FF2K"
			var data: CsmDiscount = new CsmDiscount(jobName,discountCode,ucrNo,businessOwner,"",devName ,"")
			

			When("insert")
			
			CsmDiscount.insert(fixture.conn,data)

			Then("fond in database")
			val list = CsmDiscount.findWithJobName(fixture.conn,data.jobName)
			assert(null != list)

		}

	}

}