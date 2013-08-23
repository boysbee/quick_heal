import org.scalatest._
import org.scalatest.matchers._
import dao._

class CsmDiscountSpec extends FreeSpec with GivenWhenThen with ShouldMatchers {
	import db._
	def fixture =
		new {
			val url = "jdbc:oracle:thin:@172.16.49.14:1521:TEST01"
			// val url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl"
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

		"It should insert to CSM_DISCOUNT" in {
			given("a CsmDiscount model")
			var jobName = "Test"
			var discountCode = "Test"
			var devName = "Test"
			var businessOwner = "Test"
			var ucrNo = "Test"
			when("call method insert")
			import dao.{CsmDiscount}
			var data: CsmDiscount = new CsmDiscount(jobName,discountCode,ucrNo,businessOwner,"",devName ,"")
			var success = CsmDiscount.insertToCsmDiscount(fixture.conn,data)
			then("insert success")
			assert(true == success)
		}

		"It should delete from CSM_DISCOUNT with job name and discount code" in {
			given("a job_name and discount code")
			var jobName = "Test"
			var discountCode = "Test"
			var devName = "Test"
			var businessOwner = "Test"
			var ucrNo = "Test"
			when("call method delete by jobname and discount code")
			var success = CsmDiscount.deleteByJobNameAndDiscountCode(fixture.conn,jobName,discountCode)
			then("delete success")
			assert(true == success)	
		}

	}

}