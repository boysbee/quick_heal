import org.scalatest._
import org.scalatest.matchers._

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
	}
}