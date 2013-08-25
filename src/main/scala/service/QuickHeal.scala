package service

import db.{DB}
import dao.{CsmDiscount}

class QuickHeal {
	// val url = "jdbc:oracle:thin:@172.16.49.14:1521:TEST01"
	val url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl"
	val user = "amdapp22"
	val pass = "amdapp22"
	val db = DB(url,user,pass)


	def findCsmDiscount(jobName : String , discountCode : String , keyword : String , pp : String , soc : String , propo : String) : List[CsmDiscount] = { 
		var list = CsmDiscount.findCsmDiscount(db.connect(),jobName,discountCode,keyword,pp,soc,propo)
		return list
	}

	def findCsmDiscount(jobName : String , ucrNo : String , discountCode : String , businessOwner : String , devName : String) : CsmDiscount = {
		var csmDiscount = CsmDiscount.findCsmDiscount(db.connect(),jobName,ucrNo,discountCode,businessOwner,devName)
		return csmDiscount
	}
	def findJobListByDiscountCode(discountCode : String) : List[CsmDiscount] = {
		var list = CsmDiscount.findWithDiscount(db.connect(),discountCode)
		
		return list
	}

	def findJobListByJobName(jobName : String) : List[CsmDiscount] = {
		var list = CsmDiscount.findWithJobName(db.connect(),jobName)
		
		return list
	}

	def insertToCsmDiscount(csmDiscount : CsmDiscount) : Boolean = {
		return CsmDiscount.insertToCsmDiscount(db.connect(),csmDiscount)
	}

	def deleteFromCsmDiscount(jobName : String , discountCode : String) : Boolean = {
		return CsmDiscount.deleteByJobNameAndDiscountCode( db.connect(), jobName, discountCode)
	}

	def findPropositionCode(subscriberNo : String , ban : Int ) : List[String] = {
		return List[String]()
	}

	def findSoc(subscriberNo : String , ban : Int ) : List[String] = {
		return List[String]()
	} 

	def findPricePlan(subscriberNo : String , ban : Int ) : List[String] = {
		return List[String]()
	}


}
