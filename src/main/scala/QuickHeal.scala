import db.{DB}
class QuickHeal {
	// val url = "jdbc:oracle:thin:@172.16.49.14:1521:TEST01"
	val url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl"
	val user = "amdapp22"
	val pass = "amdapp22"
	val db = DB(url,user,pass)

	def findJobListByDiscountCode(discountCode : String) : List[CsmDiscount] = {
		var list = CsmDiscount.findWithDiscount(db.connect(),discountCode)
		
		return list
	}

	def findJobListByJobName(jobName : String) : List[CsmDiscount] = {
		var list = CsmDiscount.findWithJobName(db.connect(),jobName)
		
		return list
	}
}
