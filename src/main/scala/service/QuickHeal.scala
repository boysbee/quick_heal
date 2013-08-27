package service

import db.{DB,DBConfig}
import dao._

class QuickHeal {
	// val url = "jdbc:oracle:thin:@172.16.49.14:1521:TEST01"
	val url = DBConfig.url
	val user = DBConfig.user
	val pass = DBConfig.pass
	val db = DB(url,user,pass)


	def findListCsmDiscount(jobName : String , discountCode : String , keyword : String , pp : String , soc : String , propo : String) : List[CsmDiscount] = { 
		var list = CsmDiscount.findListCsmDiscount(db.connect(),jobName,discountCode,keyword,pp,soc,propo)
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

	def findPropositionCode(subscriberNo : String , ban : Int ) : List[SubscriberContract] = {
		return SubscriberContract.findProposition(db.connect(),subscriberNo,ban)
	}

	def findSoc(subscriberNo : String , ban : Int ) : List[ServiceAgreement] = {
		return ServiceAgreement.findSoc(db.connect(),subscriberNo,ban)
	} 

	def findPricePlan(subscriberNo : String , ban : Int ) : List[ServiceAgreement] = {
		return ServiceAgreement.findPricePlan(db.connect(),subscriberNo,ban)
	}


}
