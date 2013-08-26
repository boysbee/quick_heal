import org.scalatest._
import org.scalatest.matchers._
import db._
class DBConfigSpec extends FreeSpec with GivenWhenThen with ShouldMatchers {
	
	"A DBConfig" - {
		"Have attribute" in {	
			
			DBConfig.user should be ("amdapp22")
			DBConfig.pass should be ("amdapp22")
			DBConfig.url should be ("jdbc:oracle:thin:@172.16.49.14:1521:TEST01")

		}
	}
}