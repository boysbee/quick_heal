package dao

case class CsmDiscount(jobName:String,discountCode:String,ucrNo : String , 
	businessOwner:String,keyword:String,devName :String,remark:String ,
	pp : String, propo :String , soc : String , actvCode : String , 
	actvRsnCode : String , accType : String , accCate : String, 
	benefit : String , advancePayment : String , projectStartDate : java.util.Date , 
	projectEndDate : java.util.Date , sysCreationDate : java.util.Date , sysUpdateDate : java.util.Date)

object CsmDiscount {
	import db.Control._
	import java.sql._


	def apply(jobName:String,discountCode:String,ucrNo : String , businessOwner:String,
		keyword:String,devName :String,remark:String) = new CsmDiscount(jobName,
		discountCode,ucrNo,businessOwner,keyword , devName,remark ,
		"","","","",
		"","","",
		"","",null,
		null,null,null)
	def apply(jobName:String,discountCode:String,ucrNo : String , businessOwner:String,
		keyword:String,devName :String,remark:String,
		pp : String, propo :String , soc : String , actvCode : String , 
		actvRsnCode : String , accType : String , accCate : String, 
		benefit : String , advancePayment : String) = new CsmDiscount(jobName,
		discountCode,ucrNo,businessOwner,keyword , devName,remark ,
		pp,propo,soc,actvCode,
		actvRsnCode,accType,accCate,
		benefit,advancePayment,null,
		null,new java.util.Date(),new java.util.Date())


	def apply(jobName:String,discountCode:String,ucrNo : String , businessOwner:String,
		keyword:String,devName :String,remark:String,
		pp : String, propo :String , soc : String , actvCode : String , 
		actvRsnCode : String , accType : String , accCate : String, 
		benefit : String , advancePayment : String,
		projectStartDate : java.util.Date , 
		projectEndDate : java.util.Date) = new CsmDiscount(jobName,
		discountCode,ucrNo,businessOwner,keyword , devName,remark ,
		pp,propo,soc,actvCode,
		actvRsnCode,accType,accCate,
		benefit,advancePayment,projectStartDate,
		projectEndDate,new java.util.Date(),new java.util.Date())


	def findCsmDiscount(conn : Connection , jobName : String , ucrNo : String , discountCode : String , businessOwner : String , devName : String) : CsmDiscount = {
		var sql = "select * from csm_discount where 1=1 "
		if(jobName != null && !"".equals(jobName)) { 
			sql += " and job_name like '%" + jobName + "%'"
		}
		if( discountCode != null && !"".equals(discountCode)) { 
			sql += " and discount_code like '%" + discountCode + "%'"
		}
		if(ucrNo != null && !"".equals(ucrNo)) { 
			sql += " and ucr_no like '%" + ucrNo + "%'"
		}
		if(businessOwner != null && !"".equals(businessOwner)) { 
			sql += "and business_owner like '%" + businessOwner + "%'"
		}
		if(devName != null && !"".equals(devName)) { 
			sql += " and dev_name like '%" + devName + "%'"
		}

		println("@@ query -> %s".format(sql))
		var list = using(conn.createStatement) { st=>
			using(st.executeQuery(sql)) { rs =>
				bmap(rs.next){
					transform(rs)
				}
			}
		}

		return if (list != null && list.size > 0 ) list(0) else null
	}

	def findCsmDiscount(conn : Connection , jobName : String , discountCode : String , keyword : String , pp : String , soc : String , propo : String) : List[CsmDiscount] = {

		var sql = "select * from csm_discount where 1=1 "
		if(jobName != null && !"".equals(jobName)) { 
			sql += " and job_name like '%" + jobName + "%'"
		}
		if( discountCode != null && !"".equals(discountCode)) { 
			sql += " and discount_code like '%" + discountCode + "%'"
		}
		if(keyword != null && !"".equals(keyword)) { 
			sql += " and keyword like '%" + keyword + "%'"
		}
		if(pp != null && !"".equals(pp)) { 
			sql += " and pp like '%" + pp + "%'"
		}
		if(soc != null && !"".equals(soc)) { 
			sql += " and soc like '%" + soc + "%'"
		}
		if(propo != null && !"".equals(propo)) { 
			sql += " and propo like '%" + propo + "%'"
		}

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

	def findWithDiscount(conn: Connection,discountCode : String) : List[CsmDiscount] = {
		val sql = "select * from csm_discount where discount_code = '" + discountCode + "'"
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

	
	def insertToCsmDiscount(conn: Connection, item : CsmDiscount ) : Boolean = {
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
			case e : Throwable => e.printStackTrace
		}
		finally {
			if( null != conn ) {
				try {
					conn.close()
				}
				catch {
					case e : Throwable => None
				}
				
			}
		}
		


		return check
	}

	def deleteByJobNameAndDiscountCode(conn: Connection , jobName : String , discountCode : String) : Boolean = {
		var check  = false
		val sql = "delete from csm_discount where job_name = '%s' and discount_code = '%s'".format(jobName,discountCode)
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
			case e : Throwable => e.printStackTrace
		}
		finally {
			if( null != conn ) {
				try {
					conn.close()
				}
				catch {
					case e : Throwable => None
				}
				
			}
		}
		return check
	}

	def transform(rs : java.sql.ResultSet) : CsmDiscount = CsmDiscount(rs.getString("job_name"),
		rs.getString("discount_code"),rs.getString("ucr_no"),rs.getString("business_owner"),
		rs.getString("keyword"),rs.getString("dev_name"),rs.getString("remark"),
		rs.getString("pp"),rs.getString("propo"),rs.getString("soc"),
		rs.getString("actv_code"),rs.getString("actv_rsn_code"),
		rs.getString("acc_type"),rs.getString("acc_cate"),rs.getString("benefit"),
		rs.getString("advance_payment"),rs.getDate("project_start_date"),
		rs.getDate("project_end_date"),rs.getDate("sys_creation_date"),rs.getDate("sys_update_date"))


}
