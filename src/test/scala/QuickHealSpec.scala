import org.scalatest._
import org.scalatest.matchers._
import service.{QuickHeal}

class QuickHealSpec extends FreeSpec with GivenWhenThen with ShouldMatchers {

	val fixure = 
		new {
			val quickHeal = new QuickHeal()
		}

	"A QuickHeal " - {

		"It should find with discount code" in {
			val quickHeal = fixure.quickHeal
			given("a discount code")
			var discountCode = "DIR040"
			when("use findJobListByDiscountCode")
			var result = quickHeal.findJobListByDiscountCode(discountCode)
			then("result array")
			assert(null != result)

		}

		"It should find with job name" in {
			val quickHeal = fixure.quickHeal
			given("a job name")
			var jobName = "CSDCMNPO2R"
			when("use findJobListByJobName")
			var result = quickHeal.findJobListByJobName(jobName)
			then("result array")
			assert(null != result)
			
		}

		"It should insert to CSM_DISCOUNT" in {
			val quickHeal = fixure.quickHeal
			given("a CsmDiscount model")
			var jobName = "Test"
			var discountCode = "Test"
			var devName = "Test"
			var businessOwner = "Test"
			var ucrNo = "Test"
			when("call method insert")
			import dao.{CsmDiscount}
			var data: CsmDiscount = new CsmDiscount(jobName,discountCode,ucrNo,businessOwner,"",devName ,"")
			var success = quickHeal.insertToCsmDiscount(data)
			then("insert success")
			assert(true == success)
		}
		"It should delete from CSM_DISCOUNT by job name and discount code" in {
			val quickHeal = fixure.quickHeal
			given("a job_name and discount code")
			var jobName = "Test"
			var discountCode = "Test"
			var devName = "Test"
			var businessOwner = "Test"
			var ucrNo = "Test"
			when("call method delete by jobname and discount code")
			var success = quickHeal.deleteFromCsmDiscount(jobName,discountCode)
			then("delete success")
			assert(true == success)	
		}
	}
}