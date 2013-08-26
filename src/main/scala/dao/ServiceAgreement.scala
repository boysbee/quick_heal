package dao

case class ServiceAgreement(subscriberNo : String , ban : Int , soc : String)

object ServiceAgreement {

	import db.Control._
	import java.sql._

	// def apply(subscriberNo : String , ban : Int , propositionCode : String) = new ServiceAgreement(subscriberNo,ban,propositionCode)


	def findSoc(conn : Connection , subscriberNo : String , ban : Int) : List[ServiceAgreement] = {
		var sql = "select * from service_agreement where 1 = 1"
		if( subscriberNo != null && !"".equals ( subscriberNo)) {
			sql += " and subscriber_no = '" + subscriberNo + "'"
		}
		if( ban != null) {
			sql += " and ban = " + ban
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

	def findPricePlan(conn : Connection , subscriberNo : String , ban : Int) : List[ServiceAgreement] = {
		var sql = "select * from service_agreement where 1 = 1 and service_type = 'P' "
		if( subscriberNo != null && !"".equals ( subscriberNo)) {
			sql += " and subscriber_no = '" + subscriberNo + "'"
		}
		if( ban != null) {
			sql += " and ban = " + ban
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

	def transform(rs : java.sql.ResultSet) : ServiceAgreement = new ServiceAgreement(rs.getString("subscriber_no"),rs.getInt("ban"),rs.getString("soc"))
}