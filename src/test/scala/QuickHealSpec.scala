import org.scalatest._
import org.scalatest.matchers._

class QuickHealSpec extends FreeSpec with GivenWhenThen with ShouldMatchers {

	val fixure = 
		new {
			val quickHeal = new QuickHeal()
		}

	"A QuickHeal " - {

		"Should return result list of job name" in {
			val quickHeal = fixure.quickHeal
			given("discount code")
			var discountCode = "DIR040"
			when("use findJobListByDiscountCode")
			var result = quickHeal.findJobListByDiscountCode(discountCode)
			then("result list job")
			assert(null != result)
			result.length should be (3)
		}
	}
}