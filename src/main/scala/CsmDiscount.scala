case class CsmDiscount(jobName:String)

object CsmDiscount {
	import db.Control._
	import java.sql._

	def findWithDiscount(conn: Connection) : List[CsmDiscount] =
	using(conn.createStatement) { st=>
		using(st.executeQuery("select * from csm_discount")) { rs =>
			bmap(rs.next){
				new CsmDiscount(rs.getString("job_name"))
			}
		}
	}
}
