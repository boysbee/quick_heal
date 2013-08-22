case class CsmDiscount(jobName:String,discountCode:String,ucrNo : String , businessOwner:String,keyword:String,devName :String,remark:String)

object CsmDiscount {
	import db.Control._
	import java.sql._

<<<<<<< HEAD
	def findWithDiscount(conn: Connection,discountCode : String) : List[CsmDiscount] = {
		val sql = "select * from csm_discount where discount_code = '" + discountCode + "'"
		println("@@ query -> %s".format(sql))
		var list = using(conn.createStatement) { st=>
			using(st.executeQuery(sql)) { rs =>
				bmap(rs.next){
					transform(rs)
				}
=======
	def findWithDiscount(conn: Connection) : List[CsmDiscount] =
	using(conn.createStatement) { st=>
		using(st.executeQuery("select * from csm_discount")) { rs =>
			bmap(rs.next){
				new CsmDiscount(rs.getString("job_name"))
>>>>>>> d9e812e5c3b2c02ba80e76e9e80fcb3e5fd8ba40
			}
		}
		return list	
	}
<<<<<<< HEAD

	def findWithJobName(conn: Connection,jobName : String) : List[CsmDiscount] = {
		val sql = "select * from csm_discount where job_name like '%" + jobName + "%'"
		println("@@ query -> %s".format(sql))
		var list = using(conn.createStatement) { st=>
			using(st.executeQuery(sql)) { rs =>
				bmap(rs.next){
					transform(rs)
				}
			}
		}
		return list	
	}
	
	def insert(conn: Connection, item : CsmDiscount ) : Boolean = {
		var check  = false
		val sql = "insert into csm_discount (job_name,discount_code,ucr_no,business_owner,dev_name,sys_creation_date, sys_update_date) values('%s','%s','%s','%s','%s',sysdate,sysdate)".format(item.jobName,item.discountCode,item.ucrNo,item.businessOwner,item.devName)
		println("@@ query -> %s".format(sql))
		
		try {
		
			val stmt:java.sql.Statement = conn.createStatement()
			println("@@ query with sql ->  %s".format(sql))
			var returnCount = stmt.executeUpdate(sql);
			if( returnCount > 0) {
				check = true
			}
			else 
			{
				check = false
			}
		} 
		catch {
			case e => e.printStackTrace
		}
		finally {
			if( null != conn ) {
				try {
					conn.close()
				}
				catch {
					case e => None
				}
				
			}
		}
		


		return check
	}
	def transform(rs : java.sql.ResultSet) : CsmDiscount = new CsmDiscount(rs.getString("job_name"),rs.getString("discount_code"),rs.getString("ucr_no"),rs.getString("business_owner"),rs.getString("keyword"),rs.getString("dev_name"),rs.getString("remark"))
=======
>>>>>>> d9e812e5c3b2c02ba80e76e9e80fcb3e5fd8ba40
}
