import org.scalatest._
import db.DB

class DBSpec extends FlatSpec with GivenWhenThen {
	
	def fixture =
	    new {
		    // val url = "jdbc:oracle:thin:@172.16.49.14:1521:TEST01"
		    val url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl"
			val user = "amdapp22"
			val pass = "amdapp22"

			val db = DB(url,user,pass)
    }

	it should "create connection " in {
		val f = fixture
		given ("url,user,pass")

		val db = DB(f.url,f.user,f.pass)
		when("user connect method")

		var connection = db.connect()

		then("connection object return null")
		assert(null != connection)
	}

	it should "use connection for create statment" in {
		val f = fixture
		given (" give connection")

		val connnection = f.db.connect()
		

		when ("connection create statment")
		import java.sql._
		val stmt:Statement = connnection.createStatement()

		then(" statement not null")

		assert(null != stmt)

	}

	it should "use statment for query" in {
		val f = fixture

		given("sql statment")
		import java.sql._
		val connnection = f.db.connect()
		val stmt:Statement = connnection.createStatement()
		var sql = "select * from csm_discount"
		when("use sql")

		var result:ResultSet = stmt.executeQuery(sql);

		then("result set not null")

		assert(null != result)
	}

	it should "query by sql statement" in {
		val f = fixture

		given("sql statment")

		var sql = "select * from csm_discount" 

		when ("use sql")
		import java.sql._
		val result:ResultSet = f.db.query(sql)

		then("result not null")

		assert(null != result)
	}

	// it should "insert " in {
	// 	val f = fixture

	// 	given ("sql insert ")

	// 	val sql = "insert into csm_discount (job_name,ucr_no,discount_code,business_owner,dev_name,sys_creation_date,sys_update_date) values ('CSDCMNPO2R','SNGK-94U6HP', 'DIR040','Surisara Ngamtragoonsuk','Nattaporn Chatmalairut',sysdate,sysdate)"

	// 	when("execute")

	// 	val result = f.db.execute(sql)

	// 	then ("result is true")

	// 	assert( true == result)

	// }
	it should "retreieve by id" in {
		val f = fixture
		given ("sql select")
		var docTypeName: String = ""
		val sql = "select * from csm_discount where discount_code like '%DIR040%'"

		when("executeQuery") 

		import java.sql._
		val result: ResultSet = f.db.query(sql)

		then("result not null and found data") 

		assert(null != result)
	}
}