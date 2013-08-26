package dao

case class SubscriberContract(subscriberNo : String , ban : Int , propositionCode : String)

object SubscriberContract {

	import db.Control._
	import java.sql._

	// def apply(subscriberNo : String , ban : Int , propositionCode : String) = new SubscriberContract(subscriberNo,ban,propositionCode)


	def findProposition(conn : Connection , subscriberNo : String , ban : Int) : List[SubscriberContract] = {
		var sql = "select * from subscriber_contract where 1 = 1"
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

	def transform(rs : java.sql.ResultSet) : SubscriberContract = new SubscriberContract(rs.getString("subscriber_no"),rs.getInt("ban"),rs.getString("proposition"))
}